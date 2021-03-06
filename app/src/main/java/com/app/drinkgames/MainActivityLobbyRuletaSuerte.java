package com.app.drinkgames;

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

import com.app.drinkgames.sqlite.ConexionSQLiteHelper;

import java.util.ArrayList;

public class MainActivityLobbyRuletaSuerte extends AppCompatActivity {

    private EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8, edt9, edt10;
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
        setContentView(R.layout.activity_main_lobby_ruleta_suerte);

        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //EditTexts
        edt1 = findViewById(R.id.editTextRS1);
        edt2 = findViewById(R.id.editTextRS2);
        edt3 = findViewById(R.id.editTextRS3);
        edt4 = findViewById(R.id.editTextRS4);
        edt5 = findViewById(R.id.editTextRS5);
        edt6 = findViewById(R.id.editTextRS6);
        edt7 = findViewById(R.id.editTextRS7);
        edt8 = findViewById(R.id.editTextRS8);
        edt9 = findViewById(R.id.editTextRS9);
        edt10 = findViewById(R.id.editTextRS10);

        //ImageButtons
        imgDown =findViewById(R.id.buttonRuletaSuerteDown);
        imgUp =findViewById(R.id.buttonRuletaSuerteUp);

        //Invisible del 3 al 10
        edt3.setVisibility(View.INVISIBLE);
        edt4.setVisibility(View.INVISIBLE);
        edt5.setVisibility(View.INVISIBLE);
        edt6.setVisibility(View.INVISIBLE);
        edt7.setVisibility(View.INVISIBLE);
        edt8.setVisibility(View.INVISIBLE);
        edt9.setVisibility(View.INVISIBLE);
        edt10.setVisibility(View.INVISIBLE);

        imgDown.setVisibility(View.INVISIBLE);

        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        idioma = getString(R.string.idioma);
        //Toast.makeText(this, idioma, Toast.LENGTH_LONG).show();

        switch (getString(R.string.idioma)){

            case "Espa??ol": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'Espa??ol' ;", null);
                break;
            case "English": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'English' ;", null);
                break;
            default: fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'English' ;", null);
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

        //Asingar FraseSQLite a editTexts
        edt1.setText(frases.get(0));
        edt2.setText(frases.get(1));
        edt3.setText(frases.get(2));
        edt4.setText(frases.get(3));
        edt5.setText(frases.get(4));
        edt6.setText(frases.get(5));
        edt7.setText(frases.get(6));
        edt8.setText(frases.get(7));
        edt9.setText(frases.get(8));
        edt10.setText(frases.get(9));


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
                break;
            case 5: edt5.setVisibility(View.VISIBLE);
                break;
            case 6: edt6.setVisibility(View.VISIBLE);
                break;
            case 7: edt7.setVisibility(View.VISIBLE);
                break;
            case 8: edt8.setVisibility(View.VISIBLE);
                break;
            case 9: edt9.setVisibility(View.VISIBLE);
                    imgUp.setVisibility(View.VISIBLE);
                break;
            case 10: edt10.setVisibility(View.VISIBLE);
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
            case 4: edt5.setVisibility(View.INVISIBLE);
                break;
            case 5: edt6.setVisibility(View.INVISIBLE);
                break;
            case 6: edt7.setVisibility(View.INVISIBLE);
                break;
            case 7: edt8.setVisibility(View.INVISIBLE);
                break;
            case 8: edt9.setVisibility(View.INVISIBLE);
                break;
            case 9: imgUp.setVisibility(View.VISIBLE);
                    edt10.setVisibility(View.INVISIBLE);
                break;

        }

    }


    public void irRuletaSuerte(View view){

        frasesNuevas.add(String.valueOf(edt1.getText()));
        frasesNuevas.add(String.valueOf(edt2.getText()));
        frasesNuevas.add(String.valueOf(edt3.getText()));
        frasesNuevas.add(String.valueOf(edt4.getText()));
        frasesNuevas.add(String.valueOf(edt5.getText()));
        frasesNuevas.add(String.valueOf(edt6.getText()));
        frasesNuevas.add(String.valueOf(edt7.getText()));
        frasesNuevas.add(String.valueOf(edt8.getText()));
        frasesNuevas.add(String.valueOf(edt9.getText()));
        frasesNuevas.add(String.valueOf(edt10.getText()));

        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        for (int i = 0; i<10 ; i++){

            Integer idAux;
            if(idioma.equals("Espa??ol")){
                idAux = 1000+i;
            }else{
                idAux = 1010+i;
            }

            actualizarFrase(idAux, frasesNuevas.get(i), "RS", idioma );

        }

        basedatos.close();

        Intent intent = new Intent(this, MainActivityRuletaSuerte.class);
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

        basedatos.execSQL("Update Frase set nombre='"+nombre+"' ,tipo='RS', idioma = +'"+idioma+"' where id ="+id);
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
            alertbox.setMessage(getString(R.string.ayuda_lobbyruletasuerte));
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
        // La actividad est?? a punto de hacerse visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Enfocarse en otra actividad  (esta actividad est?? a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // La actividad ya no es visible (ahora est?? "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // La actividad est?? a punto de ser destruida.
    }
}