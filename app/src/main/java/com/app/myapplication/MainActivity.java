package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.myapplication.sockets.SocketCliente;
import com.app.myapplication.utilidades.CervezaBonus;
import com.app.myapplication.utilidades.ItemListener;
import com.app.myapplication.utilidades.ListAdapter;
import com.app.myapplication.utilidades.ListElement;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import herramientas.Usuario;

public class MainActivity extends AppCompatActivity implements ItemListener, CervezaBonus {

    List<ListElement> elements;
    private String posicionElemento;
    private int posicionAux;
    private TextToSpeech mTTS;
    private MediaPlayer mpMusic;
    //Creacion de Objeto Adview
    private AdView mAdView;
    String nombre;
    String email;
    int dadoMinijuego = 0;
    String dadoAux = "";
    Usuario usuario, userAux;
    private  Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Coger datos del Intent anterior
        email = getIntent().getStringExtra("correo");
        //Toast.makeText(this, email, Toast.LENGTH_LONG).show();
        nombre =  getIntent().getStringExtra("nombre");
        dadoAux =  getIntent().getStringExtra("dado");

        if (dadoAux.isEmpty()) {
            dadoMinijuego = 99;
        }else{
            dadoMinijuego = Integer.parseInt(dadoAux);
        }
        //dadoMinijuego = (int)(Math.random()*6+1);


        //Recoger Objeto Usuario
        usuario = new Usuario(1,"Usuario", email, 100, "S", 0, 4);

        //Recibir Objeto Usuario
        //Asignar Valores
        SocketCliente cliente;
        cliente = new SocketCliente(usuario);
        cliente.start();


        try {

            Thread.sleep(3300);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        userAux = cliente.getUsuario();
        email = userAux.getCorreo();
        //Toast.makeText(this, userAux.toString(), Toast.LENGTH_LONG).show();


        //Comprobacion para saber si el usuario tiene el premium
        //En caso de si tenerlo ("N") [NO ADS] cargar el Mobile AdMob
        // Y cargar anuncio en el banner
        if (userAux.getAds().equals("S")){
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


        //Inicialización de Musica del menú
        mpMusic =  MediaPlayer.create(this, R.raw.musica_inicio_george);
        mpMusic.start();
        mpMusic.setLooping(true);

        //Toast.makeText(this, R.string.idioma, Toast.LENGTH_SHORT).show();
        switch (getString(R.string.idioma)){

            case "Español": //Toast.makeText(this, "Español", Toast.LENGTH_SHORT).show();
                            mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    Locale spanish = new Locale("es", "ES");
                                    if (status == TextToSpeech.SUCCESS) {
                                        int result = mTTS.setLanguage(spanish);
                                        if (result == TextToSpeech.LANG_MISSING_DATA
                                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                            Log.e("TTS", "Language not supported");
                                        }
                                    } else {
                                        Log.e("TTS", "Initialization failed");
                                    }
                                }
                            });
                break;
            case "English": //Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
                            mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    if (status == TextToSpeech.SUCCESS) {
                                        int result = mTTS.setLanguage(Locale.ENGLISH);
                                        if (result == TextToSpeech.LANG_MISSING_DATA
                                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                            Log.e("TTS", "Language not supported");
                                        }
                                    } else {
                                        Log.e("TTS", "Initialization failed");
                                    }
                                }
                            });
                break;
            default: //Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        }


        bonus(dadoMinijuego);
        init();
    }

    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnPerfil) {

            if(email.equals("invidado@correo.com")){

                String infoDenegado = getString(R.string.acceso_denegado_invitado);
                Toast.makeText(this, infoDenegado, Toast.LENGTH_LONG).show();

            }else{
                //Pasar de una Activity a otra
                Intent intent = new Intent(this, MainActivityPerfil.class);
                intent.putExtra("correo", email);
                startActivity(intent);
                //Finalizar Activity
                finish();
            }

        }

        if (id == R.id.mnInfoGames) {

            dialogAyudaTypesGames();

        }

        return true;
    }


    //Metodo para mostrar en un recylerView todos los minijuegos a modo menu scroll
    public void init() {

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        elements = new ArrayList<ListElement>();
        elements.add(new ListElement(R.drawable.menu_mimica, getString(R.string.mimica), "+2 Jugadores", R.drawable.pensar));
        elements.add(new ListElement(R.drawable.menu_patata_caliente, getString(R.string.patata_caliente), "+2 Jugadores", R.drawable.pensar));
        elements.add(new ListElement(R.drawable.menu_yonunca, getString(R.string.yo_nunca), "+2 Jugadores", R.drawable.secret));
        elements.add(new ListElement(R.drawable.menu_caracruz, getString(R.string.cara_cruz), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.menu_setasvenenosas, getString(R.string.setas_venenosas), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.menu_ruletarusa, getString(R.string.ruleta_rusa), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.menu_ruleta, getString(R.string.ruleta_suerte), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.menu_mayormenor, getString(R.string.mayor_menor), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.menu_cincocosas, getString(R.string.cinco_cosas), "+2 Jugadores", R.drawable.pensar));
        elements.add(new ListElement(R.drawable.menu_slotmachine, getString(R.string.slotmachine), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.menu_masprobable, getString(R.string.masprobable), "+2 Jugadores", R.drawable.secret));
        elements.add(new ListElement(R.drawable.menu_botella, getString(R.string.botella), "+2 Jugadores", R.drawable.suerte));
        elements.add(new ListElement(R.drawable.palillo_entero, getString(R.string.palillos), "+2 Jugadores", R.drawable.exclusivo));
        //elements.add(new ListElement(R.drawable.menu_yonunca,"Yo nunca prueba", "+2 Jugadores", R.drawable.exclusivo));

        ListAdapter listAdapter = new ListAdapter(elements,
                this, this);

        recyclerView.setAdapter(listAdapter);

    }


    //Metodo para redireccionar a otra activity según el minijuego elegido
    @Override
    public void onClick(int position) {
        posicionElemento = elements.get(position).getNombre();
        Toast.makeText(this, posicionElemento, Toast.LENGTH_SHORT).show();
        Intent intent;

        posicionAux = position;

        switch (position){
            case 0: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mimica
                intent = new Intent(this, MainActivityMimica.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 1: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Patata Caliente
                intent = new Intent(this, MainActivityPatataCaliente.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 2: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Yo Nunca

                //Llamada al dialig de eleccion de tipo de frases
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_eleccion);
                dialog.show();

                break;
            case 3: mpMusic.pause();
                //Cara o Cruz
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityCaraCruz.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 4: mpMusic.pause();
                //Setas Venenosas
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivitySetasVenenosas.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 5: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Ruleta Rusa
                intent = new Intent(this, MainActivityRuletaRusa.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 6: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Ruleta de la suerte
                //intent = new Intent(this, MainActivityRuletaSuerte.class);
                intent = new Intent(this, MainActivityLobbyRuletaSuerte.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 7: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mayor o menor
                intent = new Intent(this, MainActivityMayorMenor.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 8: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //5 cosas
                intent = new Intent(this, MainActivityCincoCosas.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 9: mpMusic.pause();
                //SlotMachine
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivitySlotMachine.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;
            case 10: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mas probable

                //Llamada al dialig de eleccion de tipo de frases
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_eleccion);
                dialog.show();

                break;
            case 11: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Botella
                intent = new Intent(this, MainActivityBotella.class);
                intent.putExtra("correo", email);
                intent.putExtra("ads", userAux.getAds());
                startActivity(intent);
                finish();
                break;

            case 12: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Palillos

                //JUEGO EXCLUSIVO
                //El usuario debe de tener al menos 10 cervezas para jugar
                if (userAux.getCervezas()>=10){

                    //se prepara la alerta creando nueva instancia
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                    //seleccionamos la cadena a mostrar
                    alertbox.setMessage(R.string.gastar_cervezas);
                    //elegimos un positivo SI y creamos un Listener
                    alertbox.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        //Funcion llamada cuando se pulsa el boton Si
                        public void onClick(DialogInterface arg0, int arg1) {


                            userAux.setCervezas(userAux.getCervezas()-10);
                            userAux.setAuxSeleccion(2);
                            //Recibir Objeto Usuario
                            //Asignar Valores
                            SocketCliente cliente;
                            cliente = new SocketCliente(userAux);
                            cliente.start();

                            try {

                                Thread.sleep(2000);

                            } catch (InterruptedException e) {

                                e.printStackTrace();
                            }

                            Intent intemt = new Intent(MainActivity.this, MainActivityLobbyPalillos.class);
                            intemt.putExtra("correo", email);
                            intemt.putExtra("ads", userAux.getAds());
                            startActivity(intemt);
                            finish();
                        }
                    });

                    //mostramos el alertbox
                    alertbox.show();



                }else{

                    dialogNotCervezasEnable();
                }

                break;

            default: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    //Método para mostrar la ayuda/dialog de los distintos tipos de minijuegos
    public void dialogAyudaTypesGames (){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_help_types_games);
        dialog.show();

    }

    //Método para mostrar un dialog que el usuario no dispone de cervezas suficientes
    public void dialogNotCervezasEnable (){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_not_beers);
        dialog.show();

    }



    //Método para generar un dado aleatorio entre 6 y si coincide
    //con el recibido de la activity anterior el usuario recibira
    //tantas cervezas como el numero del dado
    @Override
    public void bonus(int dadoMinijuego) {

        int dadoMenu = (int)(Math.random()*6+1);
        //dadoMinijuego = dadoMenu;

        if (dadoMenu == dadoMinijuego){
            Dialog dialog= new Dialog(this);
            dialog.setContentView(R.layout.dialog_winbeer);
            dialog.setTitle("Win Beers");
            TextView beerBonus = (TextView) dialog.findViewById(R.id.textViewBeerBonus);

            String auxiliarTexto = (String) getText(R.string.ganado_cervezas);
            beerBonus.setText(auxiliarTexto + " "+ dadoMenu);

            Integer total = userAux.getCervezas() + dadoMenu;
            //HILO MODIFICAR.....
            userAux.setCervezas(total);
            userAux.setAuxSeleccion(2);

            Thread modificarCervezasUsuario;
            modificarCervezasUsuario = new SocketCliente(userAux);
            modificarCervezasUsuario.start();

            dialog.show();
        }
    }

    public void eleccionMinigame(View view){

        int id = view.getId();
        Intent intent;

        switch (id){
            case R.id.buttonEleccionFrasesPredeterminadas:


                    if(posicionAux == 2){

                        intent = new Intent(this, MainActivityYoNunca.class);
                        intent.putExtra("correo", email);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("ads", userAux.getAds());
                        intent.putExtra("eleccion", "pred");
                        startActivity(intent);
                        finish();
                    }

                    if(posicionAux == 10){

                        intent = new Intent(this, MainActivityMasProbable.class);
                        intent.putExtra("correo", email);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("ads", userAux.getAds());
                        intent.putExtra("eleccion", "pred");
                        startActivity(intent);
                        finish();
                    }


                break;
            case R.id.buttonEleccionFrasesPropias:

                if(email.equals("invidado@correo.com")){

                    String infoDenegado = getString(R.string.acceso_denegado_invitado);
                    Toast.makeText(this, infoDenegado, Toast.LENGTH_LONG).show();

                }else{
                    if(posicionAux == 2){

                        intent = new Intent(this, MainActivityLobbyYoNunca.class);
                        intent.putExtra("correo", email);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("ads", userAux.getAds());
                        intent.putExtra("eleccion", "prop");
                        startActivity(intent);
                        finish();
                    }

                    if(posicionAux == 10){

                        intent = new Intent(this, MainActivityLobbyMasProbable.class);
                        intent.putExtra("correo", email);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("ads", userAux.getAds());
                        intent.putExtra("eleccion", "prop");
                        startActivity(intent);
                        finish();
                    }
                }


                break;

            default:

                break;
        }


        dialog.dismiss();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salir)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.show();
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
            mpMusic.pause();
            mpMusic.setLooping(false);
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