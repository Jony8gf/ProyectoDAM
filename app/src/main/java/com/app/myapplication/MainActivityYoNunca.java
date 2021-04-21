package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivityYoNunca extends AppCompatActivity {

    String records = "",error="";

    private final String ipServidor = "remotemysql.com";
    private final String puerto = "3306";
    private final String userName = "rbEmS40PZ9";
    private final String userPassword = "sOKoEWyHIQ";
    private final String nombreBaseDatos = "rbEmS40PZ9";

    private TextView tvFrase;
    ComponenteADFrase cdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yo_nunca);

        tvFrase = findViewById(R.id.textViewYoNuncaFrase);


        try{
            cdf = new ComponenteADFrase();
            cdf.conectarBD();
        }catch (ExcepcionFrase excepcionFrase) {
            Toast.makeText(this, "ERROR CONEXIÓN", Toast.LENGTH_LONG).show();
            excepcionFrase.printStackTrace();
        }


    }


    public void ff (View v){

        ArrayList<UsuarioFrase> reg;

        try{
            reg = cdf.leerEmpleados();
        }catch (ExcepcionFrase excepcionFrase) {
            Toast.makeText(this, "ERROR QUERY", Toast.LENGTH_LONG).show();
            excepcionFrase.printStackTrace();
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