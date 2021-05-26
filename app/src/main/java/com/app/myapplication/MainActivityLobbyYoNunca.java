package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import herramientas.Frase;
import herramientas.Usuario;

public class MainActivityLobbyYoNunca extends AppCompatActivity {

    private EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8, edt9, edt10, edt11, edt12, edt13, edt14, edt15, edt16, edt17, edt18, edt19, edt20;
    ArrayList<Frase> frases = new ArrayList<>();
    ArrayList<Frase> frasesNuevas = new ArrayList<>();
    private boolean carga = false;
    private String idioma = "";
    private String correo;
    private String ads;
    private Usuario usuario, userAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby_yo_nunca);


        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        idioma = getString(R.string.idioma);

        //EditTexts
        edt1 = findViewById(R.id.editTextYN1);
        edt2 = findViewById(R.id.editTextYN2);
        edt3 = findViewById(R.id.editTextYN3);
        edt4 = findViewById(R.id.editTextYN4);
        edt5 = findViewById(R.id.editTextYN5);
        edt6 = findViewById(R.id.editTextYN6);
        edt7 = findViewById(R.id.editTextYN7);
        edt8 = findViewById(R.id.editTextYN8);
        edt9 = findViewById(R.id.editTextYN9);
        edt10 = findViewById(R.id.editTextYN10);
        edt11 = findViewById(R.id.editTextYN11);
        edt12 = findViewById(R.id.editTextYN12);
        edt13 = findViewById(R.id.editTextYN13);
        edt14 = findViewById(R.id.editTextYN14);
        edt15 = findViewById(R.id.editTextYN15);
        edt16 = findViewById(R.id.editTextYN16);
        edt17 = findViewById(R.id.editTextYN17);
        edt18 = findViewById(R.id.editTextYN18);
        edt19 = findViewById(R.id.editTextYN19);
        edt20 = findViewById(R.id.editTextYN20);


        //Recoger Objeto Usuario
        usuario = new Usuario(1,"Usuario", correo, 100, "S", 0, 4);

        //Recibir Objeto Usuario
        //Asignar Valores
        SocketCliente cliente;
        cliente = new SocketCliente(usuario);
        cliente.start();


        try {

            Thread.sleep(3200);

        } catch (InterruptedException e) {

        }

        userAux = cliente.getUsuario();
        correo = userAux.getCorreo();
        //Toast.makeText(this, userAux.toString(), Toast.LENGTH_LONG).show();

        frases = userAux.getFrases();

        edt1.setText(frases.get(0).getDescripcion());
        edt2.setText(frases.get(1).getDescripcion());
        edt3.setText(frases.get(2).getDescripcion());
        edt4.setText(frases.get(3).getDescripcion());
        edt5.setText(frases.get(4).getDescripcion());
        edt6.setText(frases.get(5).getDescripcion());
        edt7.setText(frases.get(6).getDescripcion());
        edt8.setText(frases.get(7).getDescripcion());
        edt9.setText(frases.get(8).getDescripcion());
        edt10.setText(frases.get(9).getDescripcion());
        edt11.setText(frases.get(10).getDescripcion());
        edt12.setText(frases.get(11).getDescripcion());
        edt13.setText(frases.get(12).getDescripcion());
        edt14.setText(frases.get(13).getDescripcion());
        edt15.setText(frases.get(14).getDescripcion());
        edt16.setText(frases.get(15).getDescripcion());
        edt17.setText(frases.get(16).getDescripcion());
        edt18.setText(frases.get(17).getDescripcion());
        edt19.setText(frases.get(18).getDescripcion());
        edt20.setText(frases.get(19).getDescripcion());





    }
}