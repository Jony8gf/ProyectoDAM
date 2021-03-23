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
        elements.add(new ListElement(R.drawable.ic_launcher_background, "Yo nunca", "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.ic_launcher_background, "Mímica", "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.ic_launcher_background, "Cara-Cruz", "+2 Jugadores"));
        elements.add(new ListElement(R.drawable.ic_launcher_background, "Las Setas Venenosas", "+2 Jugadores"));

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