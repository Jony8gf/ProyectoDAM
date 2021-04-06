package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ItemListener{

    List<ListElement> elements;
    private String posicionElemento;
    private int posiciomiento;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //Los Palillos
        //Botella

        ListAdapter listAdapter = new ListAdapter(elements,
                this, this);

        recyclerView.setAdapter(listAdapter);

    }


    @Override
    public void onClick(int position) {
        posicionElemento = elements.get(position).getNombre();
        Toast.makeText(this, posicionElemento, Toast.LENGTH_SHORT).show();

        switch (position){
            case 0: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 1: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 2: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 3:
                //Cara o Cruz
                mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(this, MainActivityCaraCruz.class);
                startActivity(intent);
                finish();
                break;
            case 4: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 5: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 6: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 7: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 8: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 9: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 10: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case 11: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
                break;
            default: mTTS.speak(posicionElemento, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}