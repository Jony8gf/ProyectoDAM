package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import herramientas.Usuario;

public class MainActivityBotella extends AppCompatActivity {

    private TextView tvTragos, tvBebes;
    private ImageView ivBotella;
    private Button btnGirarBotella;
    private String auxiliar, correo, ads;
    private MediaPlayer mpGirarbotella;
    private int tragos, angulo, angulorandom, opcBebes;
    //Creacion de Objeto Adview
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_botella);

        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //Recoger Objeto Usuario
        Usuario usuario = new Usuario(1,"Usuario", correo, 0, "S", 0, 4);


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


        //Asignacion de TextView
        tvBebes = (TextView)findViewById(R.id.textViewBotella);
        tvTragos = (TextView)findViewById(R.id.textViewTragosBotella);

        //Asignacion de Button
        btnGirarBotella = (Button) findViewById(R.id.buttonGirarbotella);

        //Asignacion de ImageView
        ivBotella = (ImageView) findViewById(R.id.imageViewBotella);

        //Asignacion de Sonidos/Musica
        mpGirarbotella = MediaPlayer.create(this, R.raw.botellagirando);

    }

    public void girarBotella (View view){

        //Denegar boton
        auxiliar = (String) getText(R.string.rodando_botella);
        btnGirarBotella.setText(auxiliar);
        btnGirarBotella.setEnabled(false);

        //Introducir Sonido
        mpGirarbotella.start();
        mpGirarbotella.setLooping(true);

        //Generador de Tragos NumeroTragos
        tragos = (int)(Math.random()*4);
        if(tragos == 0){
            tragos=2;
        }

        //Generador Bebebs-Mandas beber
        opcBebes = (int)(Math.random()*1);

        //Generar AnguloRandom;
        angulorandom = (int)(Math.random()*360);


        //Animación patata rotar 360 Grados
        angulorandom = angulorandom + 720;
        angulo = angulo % angulorandom;
        RotateAnimation rotar = new RotateAnimation(angulo, angulorandom, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotar.setFillAfter(true);
        rotar.setDuration(5000);
        rotar.setInterpolator(new AccelerateDecelerateInterpolator());
        ivBotella.startAnimation(rotar);
        auxiliar = (String)getText(R.string.tragos);
        tvTragos.setText(tragos + " "+ auxiliar);

        if(opcBebes == 0){
            auxiliar = (String)getText(R.string.bebes);
            tvBebes.setText(auxiliar);
        }else{
            auxiliar = (String)getText(R.string.mandas_bebes);
            tvBebes.setText(auxiliar);
        }

        //Finalizar botella + sonido bocina
        mpGirarbotella.stop();
        btnGirarBotella.setEnabled(true);

        //Boton volver a lanzar botella
        auxiliar = (String) getText(R.string.girar_botella);
        btnGirarBotella.setText(auxiliar);
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
            alertbox.setMessage(getString(R.string.ayuda_botella));
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
            mpGirarbotella.pause();
            mpGirarbotella.setLooping(false);
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