package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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


}