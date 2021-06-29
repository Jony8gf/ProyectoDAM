package com.app.drinkgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.app.drinkgames.utilidades.Usuario;
import com.app.drinkgames.utilidades.Version;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    DatabaseReference bbdd;
    FirebaseDatabase firebaseDatabase;
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvVersion = findViewById(R.id.textViewVersion);

        tvVersion.setText(getString(R.string.beta) + " " + getString(R.string.version));

        firebaseDatabase = FirebaseDatabase.getInstance();
        bbdd = firebaseDatabase.getReference();

        //Version version = new Version("beta", "1.0.1");

        //Incorporamos datos a la base de datos
        //bbdd.child("Version").child(version.getNombre()).setValue(version);


        bbdd.child("Version").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {

                    Version version = datasnapshot.getValue(Version.class);

                    assert version != null;
                    if (version.getVersion().equals(getString(R.string.version))){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashScreen.this, MainActivityLogin.class);
                                startActivity(intent);
                                finish();
                            }
                        },2000);
                    }
                    else {
                        Intent intent = new Intent(SplashScreen.this, MainActivityUpdateApp.class);
                        startActivity(intent);
                        finish();
                    }

                }}


             @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}