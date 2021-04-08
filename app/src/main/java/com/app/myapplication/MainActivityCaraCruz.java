package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivityCaraCruz extends AppCompatActivity {

    private TextView tvMoneda;
    private TextView tvTragos;
    private ImageView moneda;
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


    }

    public void lanzarMoneda(View view){

        //Sonido Lanzar Moneda
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