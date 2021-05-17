package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import herramientas.Usuario;

public class MainActivityPerfil extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText etNombre;
    private ImageView ivEditAvatar,ivEditNombre;
    private String correo;
    private Dialog dialog;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_perfil);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        Toast.makeText(this, correo, Toast.LENGTH_LONG).show();

        //Instancia Usuario
        usuario = new Usuario(1,"Lucy", "Sara@yopmail.com", 0, "S", 0, 4);


        //Recibir Objeto Usuario
        //Asignar Valores
        Thread cliente;
        cliente = new SocketCliente(usuario);
        cliente.start();



        SocketEleccion clienteTT;
        clienteTT = new SocketEleccion(usuario);
        clienteTT.start();
        usuario = clienteTT.getUsuario();

        Toast.makeText(this, usuario.toString(), Toast.LENGTH_LONG).show();

        //Instancia Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //ImageView
        ivEditAvatar = findViewById(R.id.imageViewEditAvatar);
        ivEditNombre = findViewById(R.id.imageViewEditName);

        //EditText
        etNombre = findViewById(R.id.editTextNombreProfile);
        etNombre.setFocusable(false);
        etNombre.setEnabled(false);


    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnBorrarCuenta) {

            usuario.setAuxSeleccion(3);
            Thread borrarUsuario;
            borrarUsuario = new SocketCliente(usuario);
            borrarUsuario.start();

        }
        if (id == R.id.mnLogOut) {

            firebaseAuth.signOut();
            Intent intent = new Intent(this, MainActivityLogin.class);
            startActivity(intent);
            //Finalizar Activity
            finish();
        }

        if (id == R.id.mnPremium) {

            dialogTips();
        }

        return true;
    }


    public void dialogTips(){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_paypremium);
        //Button btnpago = findViewById(R.id.btnPayment);
        dialog.show();

    }

    public void payment(View view){
        Toast.makeText(this, "Pago Realizado", Toast.LENGTH_LONG).show();
        dialog.dismiss();
        usuario.setAds("N");
        /*
        Thread modificarPremiumUsuario;
        modificarPremiumUsuario = new SocketCliente(usuario);
        modificarPremiumUsuario.start();
         */
    }
}