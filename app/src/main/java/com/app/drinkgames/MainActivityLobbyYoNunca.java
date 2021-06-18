package com.app.drinkgames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.app.drinkgames.sockets.SocketCliente;

import java.util.ArrayList;

import herramientas.Frase;
import herramientas.Usuario;

public class MainActivityLobbyYoNunca extends AppCompatActivity {

    private EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8, edt9, edt10, edt11, edt12, edt13, edt14, edt15, edt16, edt17, edt18, edt19, edt20;
    ArrayList<Frase> frases = new ArrayList<>();
    private boolean carga = false;
    private String idioma = "";
    private String eleccion = "";
    private String correo;
    private String ads;
    private Usuario usuario, userAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby_yo_nunca);


        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");
        eleccion = getIntent().getStringExtra("eleccion");

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

            e.printStackTrace();

        }

        userAux = cliente.getUsuario();

        correo = userAux.getCorreo();

        frases = userAux.getFrases();


        //Poner las frases del usuario en los distintos EditTexts
        edt1.setText(frases.get(20).getDescripcion());
        edt2.setText(frases.get(21).getDescripcion());
        edt3.setText(frases.get(22).getDescripcion());
        edt4.setText(frases.get(23).getDescripcion());
        edt5.setText(frases.get(24).getDescripcion());
        edt6.setText(frases.get(25).getDescripcion());
        edt7.setText(frases.get(26).getDescripcion());
        edt8.setText(frases.get(27).getDescripcion());
        edt9.setText(frases.get(28).getDescripcion());
        edt10.setText(frases.get(29).getDescripcion());
        edt11.setText(frases.get(30).getDescripcion());
        edt12.setText(frases.get(31).getDescripcion());
        edt13.setText(frases.get(32).getDescripcion());
        edt14.setText(frases.get(33).getDescripcion());
        edt15.setText(frases.get(34).getDescripcion());
        edt16.setText(frases.get(35).getDescripcion());
        edt17.setText(frases.get(36).getDescripcion());
        edt18.setText(frases.get(37).getDescripcion());
        edt19.setText(frases.get(38).getDescripcion());
        edt20.setText(frases.get(39).getDescripcion());

    }


    public void accederYoNunca(View view){

        modificarFrases();

        Intent intent = new Intent(this, MainActivityYoNunca.class);
        intent.putExtra("correo", correo);
        String eleccion = "prop";
        intent.putExtra("eleccion", eleccion);
        intent.putExtra("ads", userAux.getAds());
        startActivity(intent);
        finish();

    }

    public void modificarFrases(){

        frases.get(20).setDescripcion(edt1.getText().toString());
        frases.get(21).setDescripcion(edt2.getText().toString());
        frases.get(22).setDescripcion(edt3.getText().toString());
        frases.get(23).setDescripcion(edt4.getText().toString());
        frases.get(24).setDescripcion(edt5.getText().toString());
        frases.get(25).setDescripcion(edt6.getText().toString());
        frases.get(26).setDescripcion(edt7.getText().toString());
        frases.get(27).setDescripcion(edt8.getText().toString());
        frases.get(28).setDescripcion(edt9.getText().toString());
        frases.get(29).setDescripcion(edt10.getText().toString());
        frases.get(30).setDescripcion(edt11.getText().toString());
        frases.get(31).setDescripcion(edt12.getText().toString());
        frases.get(32).setDescripcion(edt13.getText().toString());
        frases.get(33).setDescripcion(edt14.getText().toString());
        frases.get(34).setDescripcion(edt15.getText().toString());
        frases.get(35).setDescripcion(edt16.getText().toString());
        frases.get(36).setDescripcion(edt17.getText().toString());
        frases.get(37).setDescripcion(edt18.getText().toString());
        frases.get(38).setDescripcion(edt19.getText().toString());
        frases.get(39).setDescripcion(edt20.getText().toString());


        userAux.setFrases(frases);

        userAux.setAuxSeleccion(2);

        //Recibir Objeto Usuario
        //Asignar Valores
        SocketCliente cliente;
        cliente = new SocketCliente(userAux);
        cliente.start();


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
            int dado = (int)(Math.random()*6+1);
            String dadoAux  = ""+dado;
            intent.putExtra("correo", correo);
            intent.putExtra("dado", dadoAux);
            startActivity(intent);
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