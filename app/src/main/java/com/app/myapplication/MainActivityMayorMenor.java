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

public class MainActivityMayorMenor extends AppCompatActivity {

    private TextView tvTragos, tvGuia;
    private Button btMenor, btMayor, btIgual;
    private ImageView ivCarta;
    private int tragos, random, auxRandom, id;
    private boolean checkMenor, checkMayor, checkIgual;
    private MediaPlayer mpCorrecto, mpIncorrecto, mpJoker;
    private String auxiliar, correo, ads;
    //Creacion de Objeto Adview
    private AdView mAdView;
    private String cartas[] =  new String[]{
            "carta_joker","carta_as", "carta_dos", "carta_tres", "carta_cuatro", "carta_cinco", "carta_seis", "carta_siete", "carta_ocho", "carta_nueve", "carta_diez", "carta_j","carta_q","carta_k"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mayor_menor);

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

        //Inicializacion de variables y asignacion con parte grafica
        tragos = 1;
        checkIgual = false;
        checkMenor = false;
        checkMayor = false;

        //Textviews
        tvTragos = findViewById(R.id.textViewContadorTragosMayorMenor);
        tvGuia = findViewById(R.id.textViewGuiaMayorMenor);

        //Botones
        btMenor = findViewById(R.id.buttonMenor);
        btMayor = findViewById(R.id.buttonMayor);
        btIgual = findViewById(R.id.buttonIgual);

        //ImageView
        ivCarta = findViewById(R.id.imageViewCartaMayorMenor);


        //Asignacion de Sonido
        mpCorrecto = MediaPlayer.create(this, R.raw.correcto);
        mpIncorrecto = MediaPlayer.create(this, R.raw.incorrecto);
        mpJoker = MediaPlayer.create(this, R.raw.joker);


        //Generar primera carta random
        random = (int)(Math.random()*14);
        if(random == 0){
            random = 6;
        }

        //Cambio de Imagen según numero aleatorio & denegar acceso a botón
        id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
        ivCarta.setImageResource(id);


    }


    //Metodo Recursivo - Mayor-Menor-Igual
    public void ComprobacionCarta(){

        auxRandom = random;

        if(tragos==0){
            //Generar carta random
            random = (int)(Math.random()*12+1);
            id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
            ivCarta.setImageResource(id);
            tragos++;
            auxiliar = (String) getText(R.string.tragos_acumulados);
            tvTragos.setText(auxiliar+" " + tragos);
            auxiliar = (String) getText(R.string.siguiente_carta);
            tvGuia.setText(auxiliar);
            checkMenor=false;
            checkMayor=false;
            checkIgual=false;
        }

        if(checkMayor){
            //Generar carta random
            random = (int)(Math.random()*14);
            //Controlador de carta JOKER = 0
            if(random == 0){
                tragos++;
                id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                ivCarta.setImageResource(id);
                auxiliar = (String) getText(R.string.mandas_bebes);
                tvTragos.setText(auxiliar+""+tragos);
                auxiliar = (String) getText(R.string.joker);
                tvGuia.setText(auxiliar);
                tragos = 0;
            }
            //Controlador MAYOR QUE
            else {
                if (random > auxRandom) {
                    tragos++;
                    auxiliar = (String) getText(R.string.tragos_acumulados);
                    tvTragos.setText(auxiliar+" " + tragos);
                    auxiliar = (String) getText(R.string.acertaste);
                    tvGuia.setText(auxiliar);
                    mpCorrecto.start();
                    id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                    ivCarta.setImageResource(id);
                } else {
                    tragos++;
                    auxiliar = (String) getText(R.string.tragos_acumulados);
                    tvTragos.setText(auxiliar+" " + tragos);
                    auxiliar = (String) getText(R.string.fallaste);
                    tvGuia.setText(auxiliar);
                    mpIncorrecto.start();
                    id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                    ivCarta.setImageResource(id);
                    tragos = 1;
                }
            }
            //Restaurar Check para siguiente carta
            checkMayor = false;
        }
        if(checkMenor){
            //Generar carta random
            random = (int)(Math.random()*14);
            //Controlador de carta JOKER = 0
            if(random == 0){
                tragos++;
                id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                ivCarta.setImageResource(id);
                auxiliar = (String) getText(R.string.mandas_bebes);
                tvTragos.setText(auxiliar+""+tragos);
                auxiliar = (String) getText(R.string.joker);
                tvGuia.setText(auxiliar);
                mpJoker.start();
                tragos = 0;
            }
            //Controlador MENOR QUE
            else {
                if (random < auxRandom) {
                    tragos++;
                    auxiliar = (String) getText(R.string.tragos_acumulados);
                    tvTragos.setText(auxiliar+" " + tragos);
                    auxiliar = (String) getText(R.string.acertaste);
                    tvGuia.setText(auxiliar);
                    mpCorrecto.start();
                    id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                    ivCarta.setImageResource(id);
                } else {
                    tragos++;
                    auxiliar = (String) getText(R.string.tragos_acumulados);
                    tvTragos.setText(auxiliar+" " + tragos);
                    auxiliar = (String) getText(R.string.fallaste);
                    tvGuia.setText(auxiliar);
                    mpIncorrecto.start();
                    id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                    ivCarta.setImageResource(id);
                    tragos = 1;
                }
            }
            //Restaurar Check para siguiente carta
            checkMenor = false;
        }
        if(checkIgual){
            //Generar carta random
            random = (int)(Math.random()*14);
            //Controlador de carta JOKER = 0
            if(random == 0){
                tragos++;
                id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                ivCarta.setImageResource(id);
                auxiliar = (String) getText(R.string.mandas_bebes);
                tvTragos.setText(auxiliar+""+tragos);
                auxiliar = (String) getText(R.string.joker);
                tvGuia.setText(auxiliar);
                mpJoker.start();
                tragos = 0;
            }
            //Controlador IGUAL QUE
            else {
                if (random == auxRandom) {
                    tragos++;
                    auxiliar = (String) getText(R.string.tragos_acumulados);
                    tvTragos.setText(auxiliar+" " + tragos);
                    auxiliar = (String) getText(R.string.acertaste);
                    tvGuia.setText(auxiliar);
                    mpCorrecto.start();
                    id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                    ivCarta.setImageResource(id);
                } else {
                    tragos++;
                    auxiliar = (String) getText(R.string.bebes);
                    tvTragos.setText(auxiliar +" "+tragos);
                    auxiliar = (String) getText(R.string.fallaste);
                    tvGuia.setText(auxiliar);
                    mpIncorrecto.start();
                    id = getResources().getIdentifier(cartas[random], "drawable", getPackageName());
                    ivCarta.setImageResource(id);
                    tragos = 1;
                }
            }
            //Restaurar Check para siguiente carta
            checkIgual = false;
        }
    }


    //BOTÓN MAYOR QUE
    public void mayorQue(View view){
        checkMayor = true;
        ComprobacionCarta();
    }

    //BOTÓN MENOR QUE
    public void menorQue(View view){
        checkMenor = true;
        ComprobacionCarta();
    }
    //BOTÓN IGUAL QUE
    public void igualQue(View view){
        checkIgual = true;
        ComprobacionCarta();
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
            alertbox.setMessage(getString(R.string.ayuda_mayormenor));
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