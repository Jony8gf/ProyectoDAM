package com.app.drinkgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
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
    private CheckBox chkGuardar;
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

        //Instancia ChekBox
        chkGuardar = findViewById(R.id.chkGuardarSesion);

        cargarPreferencias();

        dialogTips();

    }


    //M??todo para acceder a la activity de Recuparar Contrase??a
    public void pasarRecuperarContrasena(View view){

        //Parar musica
        mpMusica.stop();
        mpMusica.release();

        Intent intent = new Intent(this, MainActivityRecuperarContrasena.class);
        startActivity(intent);
        //Finalizar Activity
        finish();
    }


    //M??todo para acceder al menu de minijuegos desde un usuario Invitado
    public void pasarInvitado(View view){

        //Parar musica
        mpMusica.stop();
        mpMusica.release();

        int invitadoNumero = (int) (Math.random() *700);
        String invitado = "Invitado "+ invitadoNumero;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nombre", invitado);
        intent.putExtra("correo", "invidado@correo.com");
        intent.putExtra("dado", "99");
        startActivity(intent);
        //Finalizar Activity
        finish();
    }

    //M??todo para acceder a la activity de registrarse en el sistema
    public void pasarSigIn(View view){

        //Parar musica
        mpMusica.stop();
        mpMusica.release();

        Intent intent = new Intent(this, MainActivitySignIn.class);
        startActivity(intent);
        //Finalizar Activity
        finish();
    }


    //M??todo para filtrar los valores de iniciar sesion introducidos por el usuario
    public void login(View view){

        email = etEmail.getText().toString();
        passwd = etPasswd.getText().toString();


        if (!email.isEmpty() && !passwd.isEmpty()){

            email = email.toLowerCase();
            comprobacionUsuario();

        }else{
            Toast.makeText(this, R.string.correo_no, Toast.LENGTH_SHORT).show();
        }
    }


    //M??todo para validar la authentificaci??n del usuario
    private void comprobacionUsuario() {
        mAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivityLogin.this, MainActivity.class);
                    intent.putExtra("correo", email);
                    intent.putExtra("dado", "99");

                    if (chkGuardar.isChecked()){
                        guardarPreferencia();
                    }else{
                        SharedPreferences preferences = getSharedPreferences("Credenciales",
                                Context.MODE_PRIVATE);
                        preferences.edit().clear().apply();
                    }

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

    //M??todo cargar Preferencias
    private void cargarPreferencias(){

        SharedPreferences preferences = this.getSharedPreferences("Credenciales",
                Context.MODE_PRIVATE);

        etEmail.setText(preferences.getString("email", ""));
        etPasswd.setText(preferences.getString("passwd", ""));

        String auxEmail = etEmail.getText().toString();

        if(!auxEmail.isEmpty()){
            chkGuardar.setChecked(true);
        }
    }

    //M??todo para guardar los datos en Shared Preference;
    private  void guardarPreferencia(){

        SharedPreferences preferences = this.getSharedPreferences("Credenciales",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("passwd", passwd);
        editor.commit();

    }


    //M??todo para mostrar un Dialog de Ayuda ante los da??os del alcohol
    public void dialogTips(){

        Dialog dialog= new Dialog(this);
        if (numRand == 0 || numRand == 1) {
            dialog.setContentView(R.layout.dialog_notcar);
        }else{
            dialog.setContentView(R.layout.dialog_notdrink);
        }

        dialog.setTitle("Drink tips");
        dialog.show();

        //Contador de 4 segundos para cerrar el Dialog de Ayuda
        CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                dialog.dismiss();
            }
        }.start();

    }


    //M??todo para saber mas sobre los problemas que conlleva el alcohol (Enlaces a navegador)
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
        // La actividad est?? a punto de hacerse visible.
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
        mpMusica.start();
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