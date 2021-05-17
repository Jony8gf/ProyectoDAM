package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivityYoNunca extends AppCompatActivity {

    int numero = 0;
    boolean carga = false;
    String idioma = "";
    ArrayList<String> frases = new ArrayList<>();
    private TextToSpeech mTTS;

    private TextView tvFrase, tvTragos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yo_nunca);

        //Asignacion de TextView
        tvFrase = findViewById(R.id.textViewYoNuncaFrase);
        tvTragos = findViewById(R.id.textViewYoNuncaTragps);

        // Cargar IDIOMA
        idioma = getString(R.string.idioma);

        switch (getString(R.string.idioma)){

            case "Español": Toast.makeText(this, "Español", Toast.LENGTH_SHORT).show();
                mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        Locale spanish = new Locale("es", "ES");
                        if (status == TextToSpeech.SUCCESS) {
                            int result = mTTS.setLanguage(spanish);
                            if (result == TextToSpeech.LANG_MISSING_DATA
                                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "Language not supported");
                            }
                        } else {
                            Log.e("TTS", "Initialization failed");
                        }
                    }
                });
                break;
            case "English": Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
                mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = mTTS.setLanguage(Locale.ENGLISH);
                            if (result == TextToSpeech.LANG_MISSING_DATA
                                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "Language not supported");
                            }
                        } else {
                            Log.e("TTS", "Initialization failed");
                        }
                    }
                });
                break;
            default: Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        }
    }


    //Método para sacar una frase en pantalla
    public void Buscar(View view){

        if(!carga){
            contadorFrases();

            //cargarFrases(idioma);
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

            case "Español": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'YN' and idioma = 'Español' ;", null);
                break;
            case "English": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'YN' and idioma = 'English' ;", null);
                break;
            default: fila = basedatos.rawQuery ("select nombre from frase where tipo = 'YN' and idioma = 'English' ;", null);
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

        //int fraseRandom = (int)(Math.random()*numero);
        int fraseRandom = (int)(Math.random()*frases.size());
        mTTS.speak(frases.get(fraseRandom), TextToSpeech.QUEUE_FLUSH, null);
        tvFrase.setText(frases.get(fraseRandom));

    }

    private  void tragosAletorios(){
        int tragos = (int)(Math.random()*3+1);
        String tragosAux = ""+tragos;
        tvTragos.setText(tragosAux);
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
            alertbox.setMessage(getString(R.string.ayuda_yonunca));
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