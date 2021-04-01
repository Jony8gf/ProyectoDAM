package com.app.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityRecuperarContrasena extends AppCompatActivity {


    private EditText etEmailReset;
    private TextView tvCorreoInvisible;
    private String email , auxInfo;
    private FirebaseAuth mAuth;
    private Button btnReset;
    private ProgressDialog mDialog;
    private View viewActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recuperar_contrasena);

        //Asignacion de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Asignacio de ProgressDialog
        mDialog = new ProgressDialog(this);

        //Asignacion de TextView
        tvCorreoInvisible = findViewById(R.id.textViewCorreoInvisble);

        //Asigacion de EditText
        etEmailReset = findViewById(R.id.editTextEmailRecuperar);

        //Asignacion de Button
        btnReset = findViewById(R.id.buttonResetPasswd);

        auxInfo = tvCorreoInvisible.getText().toString();

    }
    public void restablecerPassword(View view){

        email = etEmailReset.getText().toString();

        if(!email.isEmpty()){

            //String msg = String.valueOf(R.string.esperar);
            mDialog.setMessage(getString(R.string.esperar));
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
            reiniciarPassword();

        }else{
            Toast.makeText(this, R.string.email_ingresar, Toast.LENGTH_SHORT).show();
        }

    }

    private void reiniciarPassword() {

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                if(task.isSuccessful()){

                    tvCorreoInvisible.setText(auxInfo +" "+email);
                    tvCorreoInvisible.setVisibility(viewActivity.VISIBLE);

                    etEmailReset.setVisibility(viewActivity.INVISIBLE);
                    btnReset.setVisibility(viewActivity.INVISIBLE);

                }else{
                    Toast.makeText(MainActivityRecuperarContrasena.this, R.string.error, Toast.LENGTH_SHORT).show();
                }

                mDialog.dismiss();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

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