package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemListener{

    List<ListElement> elements;
    String posicionElemento;
    int posiciomiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

        /*
        if (posicionElemento.equals("Yo nunca")) {
            Intent intent = new Intent(this, MainActivity2_YoNunca.class);
            startActivity(intent);
        }*/
    }
}