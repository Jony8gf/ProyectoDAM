package com.app.myapplication;

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

import java.util.Random;

public class MainActivityCaraCruz extends AppCompatActivity {

    private TextView tvMoneda;
    private TextView tvTragos;
    private ImageView moneda;
    private Button btnLanzarMoneda;

    private int suerte = 0;
    private int tragos = 0;
    private String auxiliar;
    private WheelCoin wc;
    private boolean isStarted;
    public static final Random random = new Random();

    //Creacion de MediaPlayer
    MediaPlayer mpMoneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cara_cruz);

        //Asignacion a TextViews
        tvMoneda = findViewById(R.id.textViewCaraCruzFrase);
        tvTragos = findViewById(R.id.textViewCaraCruzTragos);
        moneda = findViewById(R.id.imageViewCaraCruzMoneda);

        //Asignacion de Sonido
        mpMoneda = MediaPlayer.create(this, R.raw.moneda);

        //Asignacion de Button
        btnLanzarMoneda = findViewById(R.id.buttonLanzarMoneda);
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
            startActivity(intent);
            //Finalizar Activity
            finish();
        }
        if(id == R.id.infoboton){
            //se prepara la alerta creando nueva instancia
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            //seleccionamos la cadena a mostrar
            alertbox.setMessage(getString(R.string.ayuda_caracruz));
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

    public void lanzarMoneda(View view){

        //Desactivar Button
        btnLanzarMoneda.setEnabled(false);

        //Sonido Lanzar Moneda
        //Iniciar musica/sonidos
        mpMoneda.start();

        tragos = (int)(Math.random()*4);

        if(tragos == 0){
            tragos=2;
        }

        runWheel();
        tvTragos.setText("");
        tvMoneda.setText("");

        if (isStarted) {

            CountDownTimer ct1 = new CountDownTimer(7000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {

                    stopWheells();
                    auxiliar = (String) getText(R.string.bebes);
                    tvTragos.setText(auxiliar +" "+tragos);
                    //Activar Button
                    btnLanzarMoneda.setEnabled(false);
                }

            }.start();
        }
    }

    public void runWheel(){

        wc = new WheelCoin(new WheelCoin.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moneda.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(0, 200));

        wc.start();
        isStarted = true;
    }

    public void stopWheells(){
        wc.stopWheel();

        switch (wc.currentIndex){

            case  0:
                tvMoneda.setText(R.string.cruz);
                break;
            case  1:
                tvMoneda.setText(R.string.cara);
                break;
            default:
                break;
            }

        isStarted = false;
    }

    public static long randomLong(long lower, long upper){
        return lower + (long) (random.nextDouble() * (upper - lower));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
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