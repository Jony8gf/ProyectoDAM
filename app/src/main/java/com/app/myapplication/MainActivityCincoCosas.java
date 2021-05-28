package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.myapplication.SQLite.ConexionSQLiteHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import herramientas.Usuario;


public class MainActivityCincoCosas extends AppCompatActivity {

    private MediaPlayer mpSiguiente, mpTicTac, mpBocina;
    private TextView tvFrase;
    private TextView tvTragos;
    private TextView tvTiempo;
    private Button btComenzar;
    private boolean ocultar  = true;
    private String contadorAux;
    private String idioma, correo, ads;
    private String auxiliar;
    ArrayList<String> frases = new ArrayList<>();
    int numero = 0;
    boolean carga = false;
    int tragos;
    int contador = 15;
    //Creacion de Objeto Adview
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cinco_cosas);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        ads = getIntent().getStringExtra("ads");

        //Recoger Objeto Usuario
        Usuario usuario = new Usuario(1,"Usuario", correo, 0, "S", 0, 4);

        //Comprobacion para saber si el usuario tiene el premium
        //En caso de si tenerlo ("N") [NO ADS] cargar el Mobile AdMob
        // Y cargar anuncio en el banner
        if (ads.equals("S")){
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

        //Musica Siguiente && TicTac
        mpSiguiente = MediaPlayer.create(this, R.raw.siguiente);
        mpTicTac = MediaPlayer.create(this, R.raw.tictak);
        mpBocina = MediaPlayer.create(this, R.raw.bocina);

        //Asignacion a TextViews
        tvFrase = findViewById(R.id.textViewCincoCosasFrase);
        tvTragos = findViewById(R.id.textViewCincoCosasTragos);
        tvTiempo = findViewById(R.id.textViewCincoCosasTiempo);

        btComenzar = findViewById(R.id.buttonCincoCosas);

        // Cargar IDIOMA
        idioma = getString(R.string.idioma);

    }

    //Método para sacar una frase en pantalla
    public void siguienteFrase(View view){

        if(!carga){
            contadorFrases();

            cargarFrases();

            fraseAletoria();

            tragosAletorios();
        }else{
            fraseAletoria();

            tragosAletorios();
        }
    }


    //Metodo para cargar Frases del SQLite y almacenarlas en un ArrayList
    private void  cargarFrases(){

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        //Consultamos los datos
        Cursor fila = null;

        switch (getString(R.string.idioma)){

            case "Español": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'CC' and idioma = 'Español' ;", null);
                break;
            case "English": fila = basedatos.rawQuery ("select nombre from frase where tipo = 'CC' and idioma = 'English' ;", null);
                break;
            default: fila = basedatos.rawQuery ("select nombre from frase where tipo = 'CC' and idioma = 'English' ;", null);
        }

        if (fila != null) {
            fila.moveToFirst();
            do {
                //Asignamos el arraylist los elementos
                String frase = fila.getString(0);
                frases.add(frase);
                carga = true;
                //Toast.makeText(this, frase, Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());

        }

        //Cerramos el cursor y la conexion con la base de datos
        fila.close();
        basedatos.close();

    }

    //Metodo para saber cuantas frases están disponibles desde el SQLite
    private void contadorFrases(){

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase basedatos = admin.getWritableDatabase();

        Cursor num = basedatos.rawQuery ("select count() from frase where tipo = 'CC';", null);
        if(num.moveToFirst()){
            String count = num.getString(0);
            numero = Integer.parseInt(count);
            basedatos.close();
        }
    }


    //Metodo para mostrar una frase anteriormente cargada en el arraylist
    private void fraseAletoria(){

        int fraseRandom = (int)(Math.random()*frases.size());
        tvFrase.setText(frases.get(fraseRandom));
        auxiliar = (String) getText(R.string.ejecucion);
        btComenzar.setText(auxiliar);
        btComenzar.setEnabled(false);

        //Introducir Sonido
        mpTicTac.start();
        mpTicTac.setLooping(true);


        //Contador (Cuenta atrás 15 segundos)
        CountDownTimer ct1 = new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                contador--;
                contadorAux = contador+"";
                tvTiempo.setText(contadorAux);
            }

            public void onFinish() {


                        auxiliar = (String) getText(R.string.tragos);
                        tvFrase.setText(auxiliar);
                        auxiliar = (String) getText(R.string.bebes_or_mandas);
                        tvTragos.setText(auxiliar+" "+tragos);
                        auxiliar = (String) getText(R.string.nueva_palabra);
                        btComenzar.setText(auxiliar);
                        btComenzar.setEnabled(true);
                        mpTicTac.pause();
                        mpBocina.start();
                        contador = 15;
            }
        }.start();
    }




    //Metodo para generar tragos de forma aleatoria
    private  void tragosAletorios(){
        tragos = (int)(Math.random()*3+1);
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
            alertbox.setMessage(getString(R.string.ayuda_cincocosas));
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
        try {
            mpSiguiente.pause();
            mpTicTac.setLooping(false);
            mpTicTac.pause();
            mpBocina.pause();
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