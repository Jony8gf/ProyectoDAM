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

public class MainActivityLobbyMasProbable extends AppCompatActivity {

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
        setContentView(R.layout.activity_main_lobby_mas_probable);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");
        eleccion = getIntent().getStringExtra("eleccion");

        idioma = getString(R.string.idioma);

        //EditTexts
        edt1 = findViewById(R.id.editTextMP1);
        edt2 = findViewById(R.id.editTextMP2);
        edt3 = findViewById(R.id.editTextMP3);
        edt4 = findViewById(R.id.editTextMP4);
        edt5 = findViewById(R.id.editTextMP5);
        edt6 = findViewById(R.id.editTextMP6);
        edt7 = findViewById(R.id.editTextMP7);
        edt8 = findViewById(R.id.editTextMP8);
        edt9 = findViewById(R.id.editTextMP9);
        edt10 = findViewById(R.id.editTextMP10);
        edt11 = findViewById(R.id.editTextMP11);
        edt12 = findViewById(R.id.editTextMP12);
        edt13 = findViewById(R.id.editTextMP13);
        edt14 = findViewById(R.id.editTextMP14);
        edt15 = findViewById(R.id.editTextMP15);
        edt16 = findViewById(R.id.editTextMP16);
        edt17 = findViewById(R.id.editTextMP17);
        edt18 = findViewById(R.id.editTextMP18);
        edt19 = findViewById(R.id.editTextMP19);
        edt20 = findViewById(R.id.editTextMP20);

        //Recoger Objeto Usuario
        usuario = new Usuario(1,"Usuario", correo, 100, "S", 0, 4);

        //Recibir Objeto Usuario
        //Asignar Valores
        SocketCliente cliente;
        cliente = new SocketCliente(usuario);
        cliente.start();


        try {

            Thread.sleep(3500);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        userAux = cliente.getUsuario();

        correo = userAux.getCorreo();

        frases = userAux.getFrases();

        //Poner las frases del usuario en los distintos EditTexts
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

    public void accederMasProbable(View view){

        modificarFrases();

        Intent intent = new Intent(this, MainActivityMasProbable.class);
        intent.putExtra("correo", correo);
        String eleccion = "prop";
        intent.putExtra("eleccion", eleccion);
        intent.putExtra("ads", userAux.getAds());
        startActivity(intent);
        finish();

    }

    public void modificarFrases(){

        frases.get(0).setDescripcion(edt1.getText().toString());
        frases.get(1).setDescripcion(edt2.getText().toString());
        frases.get(2).setDescripcion(edt3.getText().toString());
        frases.get(3).setDescripcion(edt4.getText().toString());
        frases.get(4).setDescripcion(edt5.getText().toString());
        frases.get(5).setDescripcion(edt6.getText().toString());
        frases.get(6).setDescripcion(edt7.getText().toString());
        frases.get(7).setDescripcion(edt8.getText().toString());
        frases.get(8).setDescripcion(edt9.getText().toString());
        frases.get(9).setDescripcion(edt10.getText().toString());
        frases.get(10).setDescripcion(edt11.getText().toString());
        frases.get(11).setDescripcion(edt12.getText().toString());
        frases.get(12).setDescripcion(edt13.getText().toString());
        frases.get(13).setDescripcion(edt14.getText().toString());
        frases.get(14).setDescripcion(edt15.getText().toString());
        frases.get(15).setDescripcion(edt16.getText().toString());
        frases.get(16).setDescripcion(edt17.getText().toString());
        frases.get(17).setDescripcion(edt18.getText().toString());
        frases.get(18).setDescripcion(edt19.getText().toString());
        frases.get(19).setDescripcion(edt20.getText().toString());


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
            alertbox.setMessage(getString(R.string.ayuda_masprobable));
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