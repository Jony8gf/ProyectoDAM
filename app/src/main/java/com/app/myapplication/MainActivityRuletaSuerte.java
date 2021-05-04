package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivityRuletaSuerte extends AppCompatActivity  implements Animation.AnimationListener{

    private String auxContador, auxiliar;
    private String calculo;
    private TextView tvFrase, tvTragos;
    private ImageView imgRuleta;
    private Button btnGirar;
    int number = 0;
    long lngDegrees = 0;
    private  boolean blnButtonRotation = true;
    private boolean carga = false;
    private int tragos;
    ArrayList<String> frases = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ruleta_suerte);

        auxContador = getIntent().getStringExtra("numero");
        Toast.makeText(this, auxContador, Toast.LENGTH_LONG).show();

        //TextViews
        tvFrase = findViewById(R.id.textViewRuletaSuerteFrase);
        tvTragos = findViewById(R.id.textViewRuletaSuerteTragos);

        //Button
        btnGirar = findViewById(R.id.buttonGirarRuletaSuerte);

        //ImageView
        imgRuleta = (ImageView) findViewById(R.id.imageViewRuletaSuerte);

        number = Integer.parseInt(auxContador);

        switch (auxContador){

            case "2": imgRuleta.setImageResource(R.drawable.roulette_2);
                break;
            case "3": imgRuleta.setImageResource(R.drawable.roulette_3);
                break;
            case "4": imgRuleta.setImageResource(R.drawable.roulette_4);
                break;
            case "5": imgRuleta.setImageResource(R.drawable.roulette_5);
                break;
            case "6": imgRuleta.setImageResource(R.drawable.roulette_6);
                break;
            case "7": imgRuleta.setImageResource(R.drawable.roulette_7);
                break;
            case "8": imgRuleta.setImageResource(R.drawable.roulette_8);
                break;
            case "9": imgRuleta.setImageResource(R.drawable.roulette_9);
                break;
            case "10": imgRuleta.setImageResource(R.drawable.roulette_10);
                break;
        }


        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        switch (getString(R.string.idioma)){

            case "Español": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'Español' ;", null);
                break;
            case "English": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'English' ;", null);
                break;
            default: fila = basedatos.rawQuery ("select nombre from frase where tipo = 'RS' and idioma = 'English' ;", null);
        }

        if (fila != null) {
            fila.moveToFirst();
            do {
                //Asignamos el arraylist los elementos
                String frase = fila.getString(0);
                frases.add(frase);
                carga = true;
                Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());
        }

    }



    @Override
    public void onAnimationStart(Animation animation)
    {
        this.blnButtonRotation = false;
        btnGirar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        calculo = String.valueOf((int)(((double)this.number) - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.number)))) - 1);
        Toast toast = Toast.makeText(this, " " + calculo + " ", Toast.LENGTH_LONG);
        tvFrase.setText(calculo);
        //toast.setGravity(149,0,0);
        toast.show();
        tvFrase.setText(frases.get(Integer.parseInt(String.valueOf(calculo))));

        //Generador de Tragos NumeroTragos
        tragos = (int)(Math.random()*4);
        if(tragos == 0){
            tragos=2;
        }

        auxiliar = (String)getText(R.string.tragos);
        tvTragos.setText(tragos + " "+ auxiliar);

        this.blnButtonRotation = true;
        btnGirar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void girarRuleta(View v)
    {
        if(this.blnButtonRotation)
        {
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees, (float)
                    (this.lngDegrees + ((long)ran)),1,0.5f,1,0.5f);

            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imgRuleta.setAnimation(rotateAnimation);
            imgRuleta.startAnimation(rotateAnimation);

        }

        tvFrase.setText("");
        tvTragos.setText("");
    }

    

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
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