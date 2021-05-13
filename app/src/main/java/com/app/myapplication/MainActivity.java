package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ItemListener{

    List<ListElement> elements;
    private String posicionElemento;
    private int posiciomiento;
    private TextToSpeech mTTS;
    private MediaPlayer mpMusic;

    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpMusic =  MediaPlayer.create(this, R.raw.musica_inicio_george);
        mpMusic.start();
        mpMusic.setLooping(true);

        //Toast.makeText(this, R.string.idioma, Toast.LENGTH_SHORT).show();
        switch (getString(R.string.idioma)){

            case "Español": Toast.makeText(this, "Español", Toast.LENGTH_SHORT).show();
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
            case "English": Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
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
            default: Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        }

        nombre =  getIntent().getStringExtra("nombre");

        init();
    }


    public void init() {

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        elements = new ArrayList<ListElement>();
        elements.add(new ListElement(R.drawable.menu_mimica, getString(R.string.mimica), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_patata_caliente, getString(R.string.patata_caliente), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_yonunca, getString(R.string.yo_nunca), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_caracruz, getString(R.string.cara_cruz), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_setasvenenosas, getString(R.string.setas_venenosas), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_ruletarusa, getString(R.string.ruleta_rusa), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_ruleta, getString(R.string.ruleta_suerte), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_mayormenor, getString(R.string.mayor_menor), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_cincocosas, getString(R.string.cinco_cosas), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_slotmachine, getString(R.string.slotmachine), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_masprobable, getString(R.string.masprobable), "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.menu_botella, getString(R.string.botella), "+2 Jugadores"));
        //Los Palillos

        ListAdapter listAdapter = new ListAdapter(elements,
                this, this);

        recyclerView.setAdapter(listAdapter);

    }


    @Override
    public void onClick(int position) {
        posicionElemento = elements.get(position).getNombre();
        Toast.makeText(this, posicionElemento, Toast.LENGTH_SHORT).show();
        Intent intent;

        switch (position){
            case 0: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mimica
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityMimica.class);
                startActivity(intent);
                finish();
                break;
            case 1: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Patata Caliente
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityPatataCaliente.class);
                startActivity(intent);
                finish();
                break;
            case 2: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Yo Nunca
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityYoNunca.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
                finish();
                break;
            case 3: mpMusic.pause();
                //Cara o Cruz
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                 intent = new Intent(this, MainActivityCaraCruz.class);
                startActivity(intent);
                finish();
                break;
            case 4: mpMusic.pause();
                //Setas Venenosas
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivitySetasVenenosas.class);
                startActivity(intent);
                finish();
                break;
            case 5: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Ruleta Rusa
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityRuletaRusa.class);
                startActivity(intent);
                finish();
                break;
            case 6: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Ruleta de la suerte
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //intent = new Intent(this, MainActivityRuletaSuerte.class);
                intent = new Intent(this, MainActivityLobbyRuletaSuerte.class);
                startActivity(intent);
                finish();
                break;
            case 7: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mayor o menor
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityMayorMenor.class);
                startActivity(intent);
                finish();
                break;
            case 8: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //5 cosas
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityCincoCosas.class);
                startActivity(intent);
                finish();
                break;
            case 9: mpMusic.pause();
                //SlotMachine
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivitySlotMachine.class);
                startActivity(intent);
                finish();
                break;
            case 10: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mas probable
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityMasProbable.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
                finish();
                break;
            case 11: mpMusic.pause();
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                //Mas probable
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                intent = new Intent(this, MainActivityBotella.class);
                startActivity(intent);
                finish();
                break;

            default: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
        }
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