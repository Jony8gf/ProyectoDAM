package com.app.myapplication;

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
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private String auxiliar;
    private  boolean carga = false;
    int numero = 0;
    ArrayList<String> frases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patata_caliente);

        //Asignacion a TextViews
        tvPalabraCaliente = findViewById(R.id.textViewPatataCaliente);
        tvTragos = findViewById(R.id.textViewTragosPatataCaliente);

        //Asginacion ImageView
        ivPatata = findViewById(R.id.imageViewPatataCaliente);

        //Asingacio de Button
        btnPalabra = findViewById(R.id.buttonPatataCaliente);

        //Asignacióm de Sonido Reloj & Bocina
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
            startActivity(intent);
            //Finalizar Activity
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

    //Método para sacar una frase en pantalla
    public void Buscar(View view){

        if(!carga){
            contadorFrases();

            cargarFrases();

            fraseAletoria();

            tragosAletorios();
        }else{
            fraseAletoria();

            tragosAletorios();
        }
    }

    private void  cargarFrases(){

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        switch (getString(R.string.idioma)){

            case "Español": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'PC' and idioma = 'Español' ;", null);
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
                Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());

        }

        //Cerramos el cursor y la conexion con la base de datos
        fila.close();
        basedatos.close();

    }

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

    private void fraseAletoria(){

        //Animación patata rotar 360 Grados
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

        CountDownTimer ct1 = new CountDownTimer(35000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                ivPatata.setImageResource(R.drawable.patata_wtf);
                CountDownTimer ct2 = new CountDownTimer(15000, 1000) {

                    public void onTick(long millisUntilFinished) {


                    }

                    public void onFinish() {
                        auxiliar = (String) getText(R.string.tragos);
                        tvPalabraCaliente.setText(auxiliar);
                        ivPatata.setImageResource(R.drawable.patata_rip);
                        auxiliar = (String) getText(R.string.bebes);
                        tvTragos.setText(auxiliar+" "+tragos);
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


    private  void tragosAletorios(){
        int tragos = (int)(Math.random()*3+1);
        String tragosAux = ""+tragos;
        tvTragos.setText(tragosAux);
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
        try {
            mpTictak.pause();
            mpTictak.setLooping(false);
            mpBocina.pause();
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