package com.app.drinkgames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.drinkgames.sqlite.ConexionSQLiteHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import herramientas.Usuario;

public class MainActivityPatataCaliente extends AppCompatActivity {

    private TextView tvPalabraCaliente;
    private TextView tvTragos;
    private ImageView ivPatata;
    private Button btnPalabra;
    private int tragos;
    private int frase;
    private AnimationDrawable animation;
    private MediaPlayer mpTictak, mpBocina;
    private long animationDuracion =  48000 ;//Milisegundos
    private int angulo;
    private String auxiliar, correo, ads;
    private  boolean carga = false;
    int numero = 0;
    ArrayList<String> frases = new ArrayList<>();
    //Creacion de Objeto Adview
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patata_caliente);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //Recoger Objeto Usuario
        Usuario usuario = new Usuario(1,"Usuario", correo, 0, "S", 0, 4);

        //Comprobacion para saber si el usuario tiene el premium
        //En caso de si tenerlo ("N") [NO ADS] cargar el Mobile AdMob
        // Y cargar anuncio en el banner
        if (ads.equals("S")){
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

        //Asignacion a TextViews
        tvPalabraCaliente = findViewById(R.id.textViewPatataCaliente);
        tvTragos = findViewById(R.id.textViewTragosPatataCaliente);

        //Asginacion ImageView
        ivPatata = findViewById(R.id.imageViewPatataCaliente);

        //Asingacio de Button
        btnPalabra = findViewById(R.id.buttonPatataCaliente);

        //Asignaci??m de Sonido Reloj & Bocina
        mpTictak = MediaPlayer.create(this, R.raw.tictak);
        mpBocina = MediaPlayer.create(this, R.raw.bocina);

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
            alertbox.setMessage(getString(R.string.ayuda_patatacaliente));
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

    //M??todo para sacar una frase en pantalla
    public void palabraCaliente(View view){

        if(!carga){
            contadorFrases();

            cargarFrases();

            fraseAletoria();
        }else{
            fraseAletoria();
        }
    }

    //Metodo para cargar Frases del SQLite y almacenarlas en un ArrayList
    private void  cargarFrases(){

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        switch (getString(R.string.idioma)){

            case "Espa??ol": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'PC' and idioma = 'Espa??ol' ;", null);
                break;
            case "English": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'PC' and idioma = 'English' ;", null);
                break;
            default: fila = basedatos.rawQuery ("select nombre from frase where tipo = 'PC' and idioma = 'English' ;", null);
        }

        if (fila != null) {
            fila.moveToFirst();
            do {
                //Asignamos el arraylist los elementos
                String frase = fila.getString(0);
                frases.add(frase);
                carga = true;
                //Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());

        }

        //Cerramos el cursor y la conexion con la base de datos
        fila.close();
        basedatos.close();

    }

    //Metodo para saber cuantas frases est??n disponibles desde el SQLite
    private void contadorFrases(){

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        Cursor num = basedatos.rawQuery ("select count() from frase where tipo = 'YN';", null);
        if(num.moveToFirst()){
            String count = num.getString(0);
            numero = Integer.parseInt(count);
            basedatos.close();
        }
    }

    //Metodo para mostrar una frase anteriormente cargada en el arraylist
    private void fraseAletoria(){

        tvTragos.setText("");
        //Animaci??n patata rotar 360 Grados
        angulo = angulo %360;
        RotateAnimation rotar = new RotateAnimation(angulo, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotar.setFillAfter(true);
        rotar.setDuration(50000);
        rotar.setInterpolator(new AccelerateDecelerateInterpolator());
        ivPatata.startAnimation(rotar);

        int fraseRandom = (int)(Math.random()*frases.size());
        tvPalabraCaliente.setText(frases.get(fraseRandom));
        auxiliar = (String) getText(R.string.ejecucion);
        btnPalabra.setText(auxiliar);
        btnPalabra.setEnabled(false);
        ivPatata.setImageResource(R.drawable.patata_feliz);

        //Introducir Sonido
        mpTictak.start();
        mpTictak.setLooping(true);


        //Contador de 35 segundos (Patata Semi Quemada)
        CountDownTimer ct1 = new CountDownTimer(35000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                ivPatata.setImageResource(R.drawable.patata_wtf);

                //Contador de 15 segundos (Patata Quemada)
                CountDownTimer ct2 = new CountDownTimer(15000, 1000) {

                    public void onTick(long millisUntilFinished) {


                    }

                    public void onFinish() {
                        auxiliar = (String) getText(R.string.tragos);
                        tvPalabraCaliente.setText(auxiliar);
                        ivPatata.setImageResource(R.drawable.patata_rip);
                        tragosAletorios();
                        auxiliar = (String) getText(R.string.nueva_palabra);
                        btnPalabra.setText(auxiliar);
                        btnPalabra.setEnabled(true);
                        mpTictak.pause();
                        mpBocina.start();
                    }
                }.start();
            }
        }.start();
    }


    //Metodo para generar tragos de forma aleatoria
    private  void tragosAletorios(){
        int tragos = (int)(Math.random()*3+1);
        auxiliar = (String)getText(R.string.tragos);
        tvTragos.setText(tragos + " "+ auxiliar);
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
        // La actividad est?? a punto de hacerse visible.
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
            mpTictak.pause();
            mpTictak.setLooping(false);
            mpBocina.pause();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        // Enfocarse en otra actividad  (esta actividad est?? a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // La actividad ya no es visible (ahora est?? "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // La actividad est?? a punto de ser destruida.
    }

}