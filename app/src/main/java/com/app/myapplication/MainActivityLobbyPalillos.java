package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.app.myapplication.sqlite.ConexionSQLiteHelper;

import java.util.ArrayList;

public class MainActivityLobbyPalillos extends AppCompatActivity {

    private EditText edt1, edt2, edt3, edt4;
    private ImageButton imgDown, imgUp;
    private int contador = 2;
    ArrayList<String> frases = new ArrayList<>();
    ArrayList<String> frasesNuevas = new ArrayList<>();
    private boolean carga = false;
    private String idioma = "";
    private String correo;
    private String ads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby_palillos);

        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //EditTexts
        edt1 = findViewById(R.id.editTextPL1);
        edt2 = findViewById(R.id.editTextPL2);
        edt3 = findViewById(R.id.editTextPL3);
        edt4 = findViewById(R.id.editTextPL4);

        //ImageButtons
        imgDown =findViewById(R.id.buttonPalillosDown);
        imgUp =findViewById(R.id.buttonPalillosUp);

        //Invisible del 3 al 4
        edt3.setVisibility(View.INVISIBLE);
        edt4.setVisibility(View.INVISIBLE);

        imgDown.setVisibility(View.INVISIBLE);

        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        //idioma = getString(R.string.idioma);
        //Toast.makeText(this, idioma, Toast.LENGTH_LONG).show();
        fila = basedatos.rawQuery ("select nombre from frase where tipo = 'PL' and idioma = 'English' ;", null);

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

        //Asingar FraseSQLite a editTexts
        edt1.setText(frases.get(0));
        edt2.setText(frases.get(1));
        edt3.setText(frases.get(2));
        edt4.setText(frases.get(3));


        //Cerramos el cursor y la conexion con la base de datos
        fila.close();
        basedatos.close();

    }

    public void up(View view){

        contador++;

        switch (contador){
            case 3: edt3.setVisibility(View.VISIBLE);
                    imgDown.setVisibility(View.VISIBLE);
                break;
            case 4: edt4.setVisibility(View.VISIBLE);
                    imgUp.setVisibility(View.INVISIBLE);
                break;
        }

    }

    public void down(View view){

        contador--;

        switch (contador){

            case 2: edt3.setVisibility(View.INVISIBLE);
                imgDown.setVisibility(View.INVISIBLE);
                break;
            case 3: edt4.setVisibility(View.INVISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void irPalillos(View view){

        frasesNuevas.add(String.valueOf(edt1.getText()));
        frasesNuevas.add(String.valueOf(edt2.getText()));
        frasesNuevas.add(String.valueOf(edt3.getText()));
        frasesNuevas.add(String.valueOf(edt4.getText()));

        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        for (int i = 0; i<4 ; i++){

            Integer idAux;
            idAux = 1050+i;


            actualizarFrase(idAux, frasesNuevas.get(i), "PL", "English" );

        }

        basedatos.close();

        Intent intent = new Intent(this, MainActivityPalillos.class);
        String  auxContador = ""+contador;
        intent.putExtra(
                "numero",
                auxContador
        );
        intent.putExtra("correo", correo);
        intent.putExtra("ads", ads);
        startActivity(intent);
        finish();
    }

    public void actualizarFrase(Integer id, String nombre, String tipo, String idioma){

        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        basedatos.execSQL("Update Frase set nombre='"+nombre+"' ,tipo='PL', idioma = +'"+idioma+"' where id ="+id);
        basedatos.close();
    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ayuda, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.volver) {
            //Pasar de una Activity a otra
            Intent intent = new Intent(this, MainActivity.class);
            int dado = (int)(Math.random()*6+1);
            String dadoAux  = ""+dado;
            intent.putExtra("correo", correo);
            intent.putExtra("dado", dadoAux);
            startActivity(intent);
            finish();
        }
        if (id == R.id.infoboton) {
            //se prepara la alerta creando nueva instancia
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            //seleccionamos la cadena a mostrar
            alertbox.setMessage(getString(R.string.ayuda_palillos));
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