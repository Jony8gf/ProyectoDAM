package com.app.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityLogin extends AppCompatActivity {

    private EditText etEmail, etPasswd;
    private String email, passwd;

    int numRand;

    private ImageButton btnVolumen;
    private MediaPlayer mpMusica;
    private boolean volumen = true;

    private final static String urlDGT = "https://www.dgt.es/es/la-dgt/campanas/1985/Si-bebes-no-conduzcas.shtml";
    private final static String urlPH = "https://proyectohombre.es/alcohol/";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //Instancia de Firebase Authentification
        mAuth = FirebaseAuth.getInstance();

        //Instancia numRand (Dialog)
        numRand = (int)(Math.random()*4);

        //Instancia de MediaPlayer
        mpMusica = MediaPlayer.create(this, R.raw.musica_inicio_george);
        mpMusica.start();
        mpMusica.setLooping(true);


        //Instancia EditText
        etEmail = findViewById(R.id.editTextUsuarioLogin);
        etPasswd = findViewById(R.id.editTextPasswordLogin);

        dialogTips();
    }

    public void pasarRecuperarContrasena(View view){

        mpMusica.stop();
        mpMusica.release();

        Intent intent = new Intent(this, MainActivityRecuperarContrasena.class);
        //Intent intent = new Intent(this, MainActivitySetasVenenosas.class);
        //Intent intent = new Intent(this, MainActivityYoNunca.class);
        startActivity(intent);
        //Finalizar Activity
        finish();
    }

    public void pasarInvitado(View view){

        mpMusica.stop();
        mpMusica.release();

        int invitadoNumero = (int) (Math.random() *700);
        String invitado = "Invitado "+ invitadoNumero;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nombre", invitado);
        startActivity(intent);
        //Finalizar Activity
        finish();
    }

    public void pasarSigIn(View view){

        mpMusica.stop();
        mpMusica.release();

        Intent intent = new Intent(this, MainActivitySignIn.class);
        startActivity(intent);
        //Finalizar Activity
        finish();
    }

    public void login(View view){

        email = etEmail.getText().toString();
        passwd = etPasswd.getText().toString();

        if (!email.isEmpty() && !passwd.isEmpty()){

            mpMusica.stop();
            mpMusica.release();
            comprobacionUsuario();

        }else{
            Toast.makeText(this, R.string.correo_no, Toast.LENGTH_SHORT).show();
        }
    }

    private void comprobacionUsuario() {
        mAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivityLogin.this, MainActivity.class);
                    intent.putExtra("correo", email);
                    startActivity(intent);

                    //Finalizar Activity
                    finish();
                }
                else{
                    Toast.makeText(MainActivityLogin.this, R.string.correo_no, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void dialogTips(){

        Dialog dialog= new Dialog(this);
        if (numRand == 0 || numRand == 1) {
            dialog.setContentView(R.layout.dialog_notcar);
            Button btnSaberMas = findViewById(R.id.btnSaberCar);
        }else{
            dialog.setContentView(R.layout.dialog_notdrink);
            Button btnSaberMas = findViewById(R.id.btnSaberDrink);
        }

        dialog.setTitle("Drink tips");
        dialog.show();

        CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                dialog.dismiss();
            }
        }.start();

    }

    public void saberMas(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (numRand == 1) {
            intent.setData(Uri.parse(urlDGT));
        }else{
            intent.setData(Uri.parse(urlPH));
        }
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        try {
            mpMusica.start();
            mpMusica.setLooping(true);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
        //View view = new View(getApplicationContext());
        mpMusica.start();

        /*if (UtilsNetwork.isOnline(this))


        }else{
            Intent intent = new Intent(this,  MainActivity_NoConexionInternet.class);
            startActivity(intent);
            finish();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            mpMusica.pause();
            mpMusica.setLooping(false);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
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