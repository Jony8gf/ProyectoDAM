package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.myapplication.sockets.SocketCliente;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import herramientas.Usuario;

public class MainActivitySignIn extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private EditText etNombre, etPasswd1, etPasswd2, etEmail;
    private CheckBox ckbPoliticas;
    private String nombre, passwd1, passwd2, email, requerido;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);

        //Instancia de Firebase Analytics (Control de Usuarios)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Instancia de Fireseba DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Asignacion de EditTexts
        etNombre = findViewById(R.id.editTextNombreSigIn);
        etPasswd1 = findViewById(R.id.editTextContraseña1SigIn);
        etPasswd2 = findViewById(R.id.editTextContraseña2SigIn);
        etEmail = findViewById(R.id.editTextEmailSigIn);

        //Asignacion de CheckBox
        ckbPoliticas = findViewById(R.id.checkBoxPrivacidadSigIn);
    }

    public void registroUsuario(View view){

        nombre = etNombre.getText().toString();
        passwd1 = etPasswd1.getText().toString();
        passwd2 = etPasswd2.getText().toString();
        email = etEmail.getText().toString();
        email = email.toLowerCase();

        if(nombre.equals("")){

            requerido = getString(R.string.requerido);
            etNombre.setError(requerido);
        }
        if(passwd1.equals("")){

            requerido = getString(R.string.requerido);
            etPasswd1.setError(requerido);
        }
        if(passwd2.equals("")){

            requerido = getString(R.string.requerido);
            etPasswd2.setError(requerido);
        }
        if(email.equals("")){

            requerido = getString(R.string.requerido);
            etEmail.setError(requerido);

        }

        if(ckbPoliticas.isChecked()){

            if(passwd1.equals(passwd2) && !passwd1.equals("") && 6 <= passwd1.length()){

                //Creacion de Usuario (Documento)
                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setCorreo(email);
                usuario.setAds("S");
                usuario.setCervezas(3);
                usuario.setAvatar(1);
                usuario.setAuxSeleccion(1);


                //Pasar al Soket Objeto para cree el Usuario
                Thread cliente;
                cliente = new SocketCliente(usuario);
                cliente.start();

                try {

                    Thread.sleep(2000);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }


                //Incorporamos datos a la base de datos
                //databaseReference.child("Persona").child(usuario.getUid()).setValue(usuario);

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, passwd1);

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("correo", email);
                intent.putExtra("dado", "99");
                startActivity(intent);
                Toast.makeText(this, R.string.registrado, Toast.LENGTH_LONG).show();
                finish();

            }else{

                etPasswd1.setText("");
                etPasswd2.setText("");
                Toast.makeText(this, R.string.contrasenas_no_coinciden, Toast.LENGTH_LONG).show();
                Toast.makeText(this, R.string.contrasenas_caracteres, Toast.LENGTH_LONG).show();

            }

        }else{

            Toast.makeText(this, R.string.aceptar_usos, Toast.LENGTH_LONG).show();

        }
    }

    

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Pasar de una Activity a otra
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
        //Finalizar Activity
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
        /*
        if (UtilsNetwork.isOnline(this)){



        }else{
            Intent intent = new Intent(this,  MainActivity_NoConexionInternet.class);
            startActivity(intent);
            finish();
        }
         */
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