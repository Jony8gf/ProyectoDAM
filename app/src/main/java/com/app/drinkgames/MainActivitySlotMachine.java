package com.app.drinkgames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.drinkgames.hilos.WheelSlot;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

import herramientas.Usuario;

public class MainActivitySlotMachine extends AppCompatActivity {

    private ImageView slot1, slot2, slot3;
    private TextView tvTragos;
    private WheelSlot wheel1, wheel2, wheel3;
    private Button btnTirar;
    private String correo;
    private String auxiliar;
    private String ads;
    private int tragos;

    private boolean isStarted;
    Usuario usuario;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    //Creacion de MediaPlayer
    MediaPlayer mpSlotMachine;

    //Creacion de Objeto Adview
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slot_machine);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //Recoger Objeto Usuario
        usuario = new Usuario(1,"Usuario", correo, 0, "S", 0, 4);


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

        //Asignacion de ImageViews
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);

        //Asignacion de TextView
        tvTragos = findViewById(R.id.textViewSlotTragos);

        //Asignacion de Sonido
        mpSlotMachine = MediaPlayer.create(this, R.raw.sonido_slotmachine);

        //Asignación de Button
        btnTirar = findViewById(R.id.btnSlotTirar);
    }


    //Método para mostrar el resultado de los slots de la maquina tragaperras
    public void tiradaSlot(View v){

        //Activar Button
        btnTirar.setEnabled(false);

        //Iniciar musica/sonidos
        mpSlotMachine.start();
        mpSlotMachine.isLooping();

        runWheells();

        tvTragos.setText("");



        if (isStarted) {

            //Contador de 8 segundos (Tiempo de ejecucion de los hilos)
            CountDownTimer ct1 = new CountDownTimer(8000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {

                    stopWheells();
                    mpSlotMachine.pause();
                    //Activar Button
                    btnTirar.setEnabled(true);

                }

            }.start();
        }
    }

    //Metodo para Lanzar un hilos (SLOTS)
    public void runWheells(){


        wheel1 = new WheelSlot(new WheelSlot.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot1.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(0, 200));

        wheel1.start();

        wheel2 = new WheelSlot(new WheelSlot.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot2.setImageResource(img);
                    }
                });
            }
        }, 190, randomLong(150, 400));

        wheel2.start();

        wheel3 = new WheelSlot(new WheelSlot.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot3.setImageResource(img);
                    }
                });
            }
        }, 190, randomLong(150, 400));

        wheel3.start();

        isStarted = true;
    }


    //Método para parar el Hilo (SLOTS)
    public void stopWheells(){

        wheel1.stopWheel();
        wheel2.stopWheel();
        wheel3.stopWheel();



        if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {

            switch (wheel1.currentIndex){

                case  0:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 1");
                    break;
                case  1:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 2");
                    break;
                case  2:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 3");
                    break;
                case  3:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 5");
                    break;
                case  4:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 7");
                    break;
                default:
                    break;
            }

        } else {
            auxiliar = (String) getText(R.string.bebes);
            tvTragos.setText(auxiliar +" 1");
        }

        isStarted = false;

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
            alertbox.setMessage(getString(R.string.ayuda_slotmachine));
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