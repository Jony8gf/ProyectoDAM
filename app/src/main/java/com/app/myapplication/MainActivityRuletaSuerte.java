package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.myapplication.SQLite.ConexionSQLiteHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Random;

import herramientas.Usuario;

public class MainActivityRuletaSuerte extends AppCompatActivity  implements Animation.AnimationListener{

    private String auxContador, auxiliar;
    private String calculo;
    private String ads;
    private TextView tvFrase, tvTragos;
    private ImageView imgRuleta;
    private Button btnGirar;
    int number = 0;
    long lngDegrees = 0;
    private String correo;
    private  boolean blnButtonRotation = true;
    private boolean carga = false;
    private int tragos;
    ArrayList<String> frases = new ArrayList<>();
    private MediaPlayer mpRuletaGirando;
    //Creacion de Objeto Adview
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ruleta_suerte);

        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //Recoger Objeto Usuario
        Usuario usuario = new Usuario(1,"Lucy", "lucy69@yopmail.com", 0, ads, 0, 4);

        if (usuario.getAds().equals("S")){
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Code to be executed when an ad request fails.
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            });
        }

        auxContador = getIntent().getStringExtra("numero");
        Toast.makeText(this, auxContador, Toast.LENGTH_LONG).show();

        //Mediaplayer
        mpRuletaGirando = MediaPlayer.create(this, R.raw.ruleta);

        //TextViews
        tvFrase = findViewById(R.id.textViewRuletaSuerteFrase);
        tvTragos = findViewById(R.id.textViewRuletaSuerteTragos);

        //Button
        btnGirar = findViewById(R.id.buttonGirarRuletaSuerte);

        //ImageView
        imgRuleta = (ImageView) findViewById(R.id.imageViewRuletaSuerte);

        number = Integer.parseInt(auxContador);

        switch (auxContador){

            case "2": imgRuleta.setImageResource(R.drawable.roulette_2);
                break;
            case "3": imgRuleta.setImageResource(R.drawable.roulette_3);
                break;
            case "4": imgRuleta.setImageResource(R.drawable.roulette_4);
                break;
            case "5": imgRuleta.setImageResource(R.drawable.roulette_5);
                break;
            case "6": imgRuleta.setImageResource(R.drawable.roulette_6);
                break;
            case "7": imgRuleta.setImageResource(R.drawable.roulette_7);
                break;
            case "8": imgRuleta.setImageResource(R.drawable.roulette_8);
                break;
            case "9": imgRuleta.setImageResource(R.drawable.roulette_9);
                break;
            case "10": imgRuleta.setImageResource(R.drawable.roulette_10);
                break;
        }


        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        switch (getString(R.string.idioma)){

            case "Español": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'Español' ;", null);
                break;
            case "English": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'English' ;", null);
                break;
            default: fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'English' ;", null);
        }

        if (fila != null) {
            fila.moveToFirst();
            do {
                //Asignamos el arraylist los elementos
                String frase = fila.getString(0);
                frases.add(frase);
                carga = true;
                Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());
        }

    }



    @Override
    public void onAnimationStart(Animation animation)
    {
        this.blnButtonRotation = false;
        btnGirar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        calculo = String.valueOf((int)(((double)this.number) - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.number)))) - 1);
        Toast toast = Toast.makeText(this, " " + calculo + " ", Toast.LENGTH_LONG);
        tvFrase.setText(calculo);
        //toast.setGravity(149,0,0);
        toast.show();
        tvFrase.setText(frases.get(Integer.parseInt(String.valueOf(calculo))));

        //Generador de Tragos NumeroTragos
        tragos = (int)(Math.random()*4);
        if(tragos == 0){
            tragos=2;
        }

        auxiliar = (String)getText(R.string.tragos);
        tvTragos.setText(tragos + " "+ auxiliar);

        this.blnButtonRotation = true;
        btnGirar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void girarRuleta(View v)
    {
        if(this.blnButtonRotation)
        {
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees, (float)
                    (this.lngDegrees + ((long)ran)),1,0.5f,1,0.5f);

            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imgRuleta.setAnimation(rotateAnimation);
            imgRuleta.startAnimation(rotateAnimation);
            mpRuletaGirando.start();
            btnGirar.setVisibility(View.INVISIBLE);

        }

        tvFrase.setText("");
        tvTragos.setText("");
    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ayuda, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == R.id.volver){
            //Pasar de una Activity a otra
            Intent intent = new Intent(this, MainActivity.class);
            int dado = (int)(Math.random()*6+1);
            String dadoAux  = ""+dado;
            intent.putExtra("correo", correo);
            intent.putExtra("dado", dadoAux);
            startActivity(intent);
            finish();
        }
        if(id == R.id.infoboton){
            //se prepara la alerta creando nueva instancia
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            //seleccionamos la cadena a mostrar
            alertbox.setMessage(getString(R.string.ayuda_ruletasuerte));
            //elegimos un positivo SI y creamos un Listener
            alertbox.setPositiveButton(getString(R.string.entendido), new DialogInterface.OnClickListener() {
                //Funcion llamada cuando se pulsa el boton Si
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            //mostramos el alertbox
            alertbox.show();
        }

        return true;
    }

    

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        int dado = (int)(Math.random()*6+1);
        String dadoAux  = ""+dado;
        intent.putExtra("correo", correo);
        intent.putExtra("dado", dadoAux);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
        try {
            mpRuletaGirando.pause();
            mpRuletaGirando.setLooping(false);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // La actividad ya no es visible (ahora está "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // La actividad está a punto de ser destruida.
    }
}