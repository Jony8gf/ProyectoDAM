package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityCaraCruz extends AppCompatActivity {

    private TextView tvMoneda;
    private TextView tvTragos;
    private ImageView moneda;
    private int suerte = 0;
    private int tragos = 0;
    private String auxiliar;

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

        suerte = (int)(Math.random()*10);
        tragos = (int)(Math.random()*4);

        if(tragos == 0){
            tragos=2;
        }

        if(suerte>5){
            moneda.setImageResource(R.drawable.moneda_cruz);
            tvMoneda.setText(R.string.cruz);
            auxiliar = (String) getText(R.string.bebes);
            tvTragos.setText(auxiliar +" "+tragos);

        }else{
            moneda.setImageResource(R.drawable.moneda_cara);
            tvMoneda.setText(R.string.cara);
            auxiliar = (String) getText(R.string.bebes);
            tvTragos.setText(auxiliar +" "+tragos);
        }
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