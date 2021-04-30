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

public class MainActivityRuletaRusa extends AppCompatActivity {

    //Atributos Clase CaraCruz
    private TextView tvTiro, tvTragos;
    private ImageView ivRevolver;
    private Button btDisparar;
    private int suerte = 0;
    private int tragos = 0;
    private int [] cargador = new int[7];
    private int reinicio = 1;
    private int contador =0;
    private int validar=1;
    private String auxiliar;

    private MediaPlayer mpDisparo, mpRecarga, mpCasquillar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ruleta_rusa);

        //Asignacion a TextViews
        tvTiro = findViewById(R.id.textViewTiroRuletaRusa);
        tvTragos = findViewById(R.id.textViewTragosRuletaRusa);

        //Asinacion de boton
        btDisparar =findViewById(R.id.button_disparar);

        //Asignacion de ImageView
        ivRevolver = findViewById(R.id.imageViewRuletaRusa);

        //Asignacion de Sonidos
        mpCasquillar = MediaPlayer.create(this, R.raw.casquillar);
        mpDisparo = MediaPlayer.create(this, R.raw.disparo);
        mpRecarga = MediaPlayer.create(this, R.raw.recargar);

    }


    //Metodo Disparar
    public void disparar(View view){
        auxiliar = (String) getText(R.string.tiro);
        tvTiro.setText(auxiliar);
        tvTragos.setText("");

        if(reinicio == 1){

            //Sonido Recargar
            mpRecarga.start();

            suerte = (int) (Math.random() * 7);
            tragos = (int) (Math.random() * 3+1);
            cargador[suerte] = 1;
            contador=0;
            reinicio = 2;

            auxiliar = (String) getText(R.string.disparar);
            btDisparar.setText(auxiliar);
            auxiliar = (String) getText(R.string.recargar);
            tvTiro.setText(auxiliar);
            auxiliar = (String) getText(R.string.bebes);
            tvTragos.setText(auxiliar +" "+tragos);
        }
        if(reinicio == 2){

            if(validar != cargador[contador]){

                //Sonido Casquillar
                mpCasquillar.start();
                contador++;
                auxiliar = (String) getText(R.string.probar_suerte);
                tvTiro.setText(auxiliar+" "+contador);
                ivRevolver.setImageResource(R.drawable.revolver_fake);

            }
            else{

                //Sonido Disparo
                mpDisparo.start();
                auxiliar = (String) getText(R.string.bebes);
                tvTragos.setText(auxiliar+" "+tragos);
                auxiliar = (String) getText(R.string.alcance);
                tvTiro.setText(auxiliar);
                ivRevolver.setImageResource(R.drawable.revolver_tiro);
                auxiliar = (String) getText(R.string.disparar);
                btDisparar.setText(auxiliar);
                cargador[suerte] = 0;
                reinicio = 1;

            }
        }
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
            alertbox.setMessage(getString(R.string.ayuda_ruletarusa));
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