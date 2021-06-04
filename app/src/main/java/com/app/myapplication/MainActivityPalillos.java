package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.myapplication.sqlite.ConexionSQLiteHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import herramientas.Usuario;

public class MainActivityPalillos extends AppCompatActivity {

    private String idioma = "";
    private String ads = "";
    private String correo;
    private String perdedor = "";
    private String auxContador;
    private TextView tvTragos, tvInfo;
    private int contador = 1;
    private int number = 0;
    private int palilloRandom=  0;
    private int id=  0;
    private ImageView imgPalillo1, imgPalillo2, imgPalillo3, imgPalillo4, imgTaparPalillo, imgAux;
    private AdView mAdView;
    ArrayList<String> jugadores = new ArrayList<>();
    ArrayList <Integer> map = new ArrayList<>();
    private boolean carga = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_palillos);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");
        auxContador = getIntent().getStringExtra("numero");

        // Cargar IDIOMA
        idioma = getString(R.string.idioma);

        //TextViews
        tvInfo = findViewById(R.id.textViewPalillosInfo);
        tvTragos = findViewById(R.id.textViewTragosPalillos);

        //ImageView
        imgPalillo1 = findViewById(R.id.imageViewPalillo1);
        imgPalillo2 = findViewById(R.id.imageViewPalillo2);
        imgPalillo3 = findViewById(R.id.imageViewPalillo3);
        imgPalillo4 = findViewById(R.id.imageViewPalillo4);
        imgTaparPalillo = findViewById(R.id.imageViewTaparPalillos);

        imgTaparPalillo.setEnabled(false);

        //Recoger Objeto Usuario
        Usuario usuario = new Usuario(1,"Usuario", "usuario@yopmail.com", 0, ads, 0, 4);
        Toast.makeText(this, usuario.getAds(), Toast.LENGTH_LONG).show();


        //Comprobacion para saber si el usuario tiene el premium
        //En caso de si tenerlo ("N") [NO ADS] cargar el Mobile AdMob
        // Y cargar anuncio en el banner
        if (usuario.getAds().equals("S")){
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Code to be executed when an ad request fails.
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            });
        }

        number = Integer.parseInt(auxContador);

        seleccionarPalillos();

        //Conexion con bd
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        //idioma = getString(R.string.idioma);
        //Toast.makeText(this, idioma, Toast.LENGTH_LONG).show();
        fila = basedatos.rawQuery ("select nombre from frase where tipo = 'PL' and idioma = 'English' ;", null);

        if (fila != null) {
            fila.moveToFirst();
            do {
                //Asignamos el arraylist los elementos
                String frase = fila.getString(0);
                jugadores.add(frase);
                carga = true;
                //Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());

        }

        //Cerramos el cursor y la conexion con la base de datos
        fila.close();
        basedatos.close();

        tvInfo.setText(getString(R.string.turno)+""+jugadores.get(0));

    }

    public void escogerPalillo (View view){

        numeroAleatorio();

        id = view.getId();
        map.add(id);
        imgAux = view.findViewById(id);
        imgAux.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Number"+number, Toast.LENGTH_LONG ).show();

        if(contador<=number){

            tvInfo.setText(getString(R.string.turno)+""+jugadores.get(contador));
            contador++;
            Toast.makeText(this, ""+contador, Toast.LENGTH_LONG ).show();

        }

        if(contador == number){

            switch (palilloRandom){

                case 1:imgPalillo1.setImageResource(R.drawable.palillo_pequeno);
                    if(map.get(0) == imgPalillo1.getId()){
                        Toast.makeText(this, jugadores.get(0), Toast.LENGTH_LONG).show();
                        perdedor = jugadores.get(0);
                    }
                    break;
                case 2:imgPalillo2.setImageResource(R.drawable.palillo_pequeno);
                    if(map.get(1) == imgPalillo2.getId()){
                        Toast.makeText(this, jugadores.get(1), Toast.LENGTH_LONG).show();
                        perdedor = jugadores.get(1);
                    }
                    break;
                case 3:imgPalillo3.setImageResource(R.drawable.palillo_pequeno);
                    if(map.get(2) == imgPalillo3.getId()){
                        Toast.makeText(this, jugadores.get(2), Toast.LENGTH_LONG).show();
                        perdedor = jugadores.get(2);
                    }
                    break;
                case 4:imgPalillo4.setImageResource(R.drawable.palillo_pequeno);
                    if(map.get(3) == imgPalillo4.getId()){
                        Toast.makeText(this, jugadores.get(3), Toast.LENGTH_LONG).show();
                        perdedor = jugadores.get(3);
                    }
                    break;
            }


            imgPalillo1.setVisibility(View.VISIBLE);
            imgPalillo2.setVisibility(View.VISIBLE);
            imgPalillo3.setVisibility(View.VISIBLE);
            imgPalillo4.setVisibility(View.VISIBLE);
            //Imagen palillo normal
            imgTaparPalillo.setVisibility(View.INVISIBLE);
            contador = 0;
            tvInfo.setText(getString(R.string.turno)+""+jugadores.get(0));
            mostrarDialog();
            int tragos = (int)(Math.random()*3+1);
            tvTragos.setText(perdedor+" "+getString(R.string.bebes)+" "+tragos);



        }
    }


    public void numeroAleatorio (){
        palilloRandom = (int)(Math.random()*number+1);
    }

    public void seleccionarPalillos(){

        switch (number){

            case 2:
                imgPalillo3.setVisibility(View.INVISIBLE);
                imgPalillo4.setVisibility(View.INVISIBLE);
                break;
            case 3: imgPalillo4.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void mostrarDialog(){
        //se prepara la alerta creando nueva instancia
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        //seleccionamos la cadena a mostrar
        alertbox.setMessage(R.string.play_gamme);
        //elegimos un positivo SI y creamos un Listener
        alertbox.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            //Funcion llamada cuando se pulsa el boton Si
            public void onClick(DialogInterface arg0, int arg1) {


                seleccionarPalillos();
                imgTaparPalillo.setVisibility(View.VISIBLE);
                contador = 0;
                imgPalillo1.setImageResource(R.drawable.palillo_entero);
                imgPalillo2.setImageResource(R.drawable.palillo_entero);
                imgPalillo3.setImageResource(R.drawable.palillo_entero);
                imgPalillo4.setImageResource(R.drawable.palillo_entero);


                map = new ArrayList<>();
            }
        });

        //mostramos el alertbox
        alertbox.show();
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
            alertbox.setMessage(getString(R.string.ayuda_palillos));
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