package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityLobbyRuletaSuerte extends AppCompatActivity {

    private EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8, edt9, edt10;
    private ImageButton imgDown, imgUp;
    private int contador = 2;
    ArrayList<String> frases = new ArrayList<>();
    ArrayList<String> frasesNuevas = new ArrayList<>();
    private boolean carga = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby_ruleta_suerte);

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

        switch (getString(R.string.idioma)){

            case "Español": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'Español' ;", null);
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

        //Asingar Frase a editTexts
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

        //Asingar EditText a Frases
        //for (int i = 0; i<= 10; i++) frases.remove(i);

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

            ContentValues registro = new ContentValues();
            registro.put("nombre", frasesNuevas.get(i));
            registro.put("tipo", "RS");


            switch (getString(R.string.idioma)){

                case "Español": registro.put("idioma", "Español");
                    //basedatos.rawQuery ("update frase set nombre = "+frases.get(i)+" where tipo = 'RS' and idioma = 'Español' and id = 100"+i+" ;", null);
                    basedatos.update("frase", registro, "id=" + 1000+i , null);

                    break;
                case "English": registro.put("idioma", "English");
                    //basedatos.rawQuery ("update frase set nombre = "+frases.get(i)+" where tipo = 'RS' and idioma = 'Español' and id = 100"+i+" ;", null);
                    basedatos.update("frase", registro, "id=" + 1010+i , null);
                    break;
                default: registro.put("idioma", "English");
                    //basedatos.rawQuery ("update frase set nombre = "+frases.get(i)+" where tipo = 'RS' and idioma = 'Español' and id = 100"+i+" ;", null);
                    basedatos.update("frase", registro, "id=" + 1010+i , null);
            }
        }



        basedatos.close();

        Intent intent = new Intent(this, MainActivityRuletaSuerte.class);
        String  auxContador = ""+contador;
        intent.putExtra(
                "numero",
                auxContador
        );
        startActivity(intent);
        finish();

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