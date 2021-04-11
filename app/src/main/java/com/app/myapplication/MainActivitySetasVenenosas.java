package com.app.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivitySetasVenenosas extends AppCompatActivity {

    private int buttonID;
    private ImageButton boton;
    private String auxiliar;
    private TextView tvContadorTragos;
    private ImageButton btnSeta1x1, btnSeta1x2, btnSeta1x3, btnSeta1x4;
    private ImageButton btnSeta2x1, btnSeta2x2, btnSeta2x3, btnSeta2x4;
    private ImageButton btnSeta3x1, btnSeta3x2, btnSeta3x3, btnSeta3x4;
    private ImageButton btnSeta4x1, btnSeta4x2, btnSeta4x3, btnSeta4x4;
    private ImageButton btnSeta5x1, btnSeta5x2, btnSeta5x3, btnSeta5x4;
    private String setasPochas[] =  new String[]{
            "seta_reto","seta_bebes","seta_mandas"
    };
    private String setasRadiactivas[] =  new String[]{
            "seta_x2"
    };
    private String setasBuenas[] =  new String[]{
            "seta_uno","seta_dos"
    };
    private int id = 0;
    private int aleatorio = 0;
    private int contador = 0;
    private int tragos = 0;

    private MediaPlayer mpSetaMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setas_venenosas);


        //Asignacion a TextViews
        tvContadorTragos = findViewById(R.id.textViewContadorTragosSetas);

        //Botones FILA 1
        btnSeta1x1 = findViewById(R.id.seta_1x1);
        btnSeta1x2 = findViewById(R.id.seta_1x2);
        btnSeta1x3 = findViewById(R.id.seta_1x3);
        btnSeta1x4 = findViewById(R.id.seta_1x4);

        //Botones FILA 2
        btnSeta2x1 =findViewById(R.id.seta_2x1);
        btnSeta2x2 =findViewById(R.id.seta_2x2);
        btnSeta2x3 =findViewById(R.id.seta_2x3);
        btnSeta2x4 =findViewById(R.id.seta_2x4);

        //Botones FILA 3
        btnSeta3x1 =findViewById(R.id.seta_3x1);
        btnSeta3x2 =findViewById(R.id.seta_3x2);
        btnSeta3x3 =findViewById(R.id.seta_3x3);
        btnSeta3x4 =findViewById(R.id.seta_3x4);

        //Botones FILA 4

        btnSeta4x1 =findViewById(R.id.seta_4x1);
        btnSeta4x2 =findViewById(R.id.seta_4x2);
        btnSeta4x3 =findViewById(R.id.seta_4x3);
        btnSeta4x4 =findViewById(R.id.seta_4x4);


        //Botones FILA 5
        btnSeta5x1 =findViewById(R.id.seta_5x1);
        btnSeta5x2 =findViewById(R.id.seta_5x2);
        btnSeta5x3 =findViewById(R.id.seta_5x3);
        btnSeta5x4 =findViewById(R.id.seta_5x4);
    }


    public void cogerSeta(View view){
        //Obtener Id del Botón
        buttonID = view.getId();
        boton = view.findViewById(buttonID);

        auxiliar = (String) getText(R.string.tragos);

        //Creacion de if
        //Este se lanzara cuando sea la primera ronda o el acumulador de tragos sea 0
        if(tragos == 0 || contador == 0){

            //Asignacion de Sonido
            mpSetaMusica = MediaPlayer.create(this, R.raw.seta_buena);
            //Iniciar musica/sonidos
            mpSetaMusica.start();

            //Creación de algoritmo matematico para establecer la primera casilla
            aleatorio = (int)(Math.random()*2);
            //Cambio de Imagen según numero aleatorio & denegar acceseo al boton
            id = getResources().getIdentifier(setasBuenas[aleatorio], "drawable", getPackageName());
            boton.setBackgroundResource(id);
            boton.setEnabled(false);

            //Creacion de if
            //Este se ejecutara cuando el valor de aleatorio sea 0
            if(aleatorio == 0){
                //Suma de tragos & Mostrado por pantalla
                tragos = tragos + 1;
                tvContadorTragos.setText(auxiliar+" "+tragos);
                //Acumulación de contador
                contador++;

            }
            //Creacion de else, este se ejecutara cuando el valor de aleatorio distinto a 0
            else{
                //Suma de tragos & Mostrado por pantalla
                tragos = tragos + 2;
                tvContadorTragos.setText(auxiliar+" "+tragos);
                //Acumulación de contador
                contador++;
            }
        }
        //Creacion de else, este se ejecutara cuando la variable tragos y contador sean distinto de 0
        else{

            //Creacion de algortmo matematico para establecer la casilla
            aleatorio = (int)(Math.random()*13);

            //Creación de if
            //Si el valor de aleatorio es 5 o menos a 5
            if(aleatorio <= 5){

                //Asignacion de Sonido
                mpSetaMusica = MediaPlayer.create(this, R.raw.seta_buena);
                //Iniciar musica/sonidos
                mpSetaMusica.start();

                //Creación de algoritmo matematico para extablecer la casilla y numero de tragos
                aleatorio = (int)(Math.random()*2);
                //Cambio de Imagen según numero aleatorio & denegar acceso a botón
                id = getResources().getIdentifier(setasBuenas[aleatorio], "drawable", getPackageName());
                boton.setBackgroundResource(id);
                boton.setEnabled(false);

                //Creacion de if, este se lanzara cunado el valor de aleatorio sea 0
                if(aleatorio == 0){
                    //Suma de tragos & Mostrado por pantalla
                    tragos = tragos + 1;
                    tvContadorTragos.setText(auxiliar+" "+tragos);
                    //Acumulación de contador
                    contador++;
                }
                //Creacion de else, se lanzará cuando el valor de aleatorio sea distinto a 1
                else{
                    //Suma de tragos & Mostrado por pantalla
                    tragos = tragos + 2;
                    tvContadorTragos.setText(auxiliar+" "+tragos);
                    //Acumulación de contador
                    contador++;
                }

            }
            //Creación de if
            //Si el valor de aleatorio es 6
            if(aleatorio == 6){

                //Asignacion de Sonido
                mpSetaMusica = MediaPlayer.create(this, R.raw.seta_radioactiva);
                //Iniciar musica/sonidos
                mpSetaMusica.start();

                //Cambio de Imagen según numero aleatorio & denegar acceso a botón
                id = getResources().getIdentifier(setasRadiactivas[0], "drawable", getPackageName());
                boton.setBackgroundResource(id);
                boton.setEnabled(false);
                //Multiplicación de tragos & Mostrado por pantalla
                tragos = tragos * 2;
                tvContadorTragos.setText(auxiliar+" "+tragos);
                //Acumulación de contador
                contador++;

            }
            //Creacion de else, este se lanzara cuando aletorio sea mayor a 6
            if (aleatorio > 6){

                //Asignacion de Sonido
                mpSetaMusica = MediaPlayer.create(this, R.raw.seta_pocha);
                //Iniciar musica/sonidos
                mpSetaMusica.start();

                aleatorio = (int)(Math.random()*3);
                id = getResources().getIdentifier(setasPochas[aleatorio], "drawable", getPackageName());
                boton.setBackgroundResource(id);
                boton.setEnabled(false);
                tragos=0;
                //Acumulación de contador
                contador++;
            }
        }
        //Creación de if, se ejecutará cuando el valor de aleatorio sea 4
        if(contador == 20){

            //Asignacion de Sonido
            mpSetaMusica = MediaPlayer.create(this, R.raw.seta_pocha);
            //Iniciar musica/sonidos
            mpSetaMusica.start();

            //Creación de algoritmo matematico para extablecer la casilla y numero de tragos
            aleatorio = (int)(Math.random()*3);

            //Creacion de if, este se lanzara cunado el valor de aleatorio sea 0
            id = getResources().getIdentifier(setasPochas[aleatorio], "drawable", getPackageName());
            boton.setBackgroundResource(id);
            boton.setEnabled(false);
            contador = 0;

            //Creación de if, este se ejecutara cuando el valor de contador sea 0
            if(contador == 0){
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                //seleccionamos la cadena a mostrar
                alertbox.setMessage(R.string.play_gamme);
                //elegimos un positivo SI y creamos un Listener
                alertbox.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    //Funcion llamada cuando se pulsa el boton Si
                    public void onClick(DialogInterface arg0, int arg1) {

                        auxiliar = (String) getText(R.string.pulsar_casilla);
                        tvContadorTragos.setText(auxiliar);

                        btnSeta1x1.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta1x2.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta1x3.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta1x4.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta2x1.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta2x2.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta2x3.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta2x4.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta3x1.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta3x2.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta3x3.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta3x4.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta4x1.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta4x2.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta4x3.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta4x4.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta5x1.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta5x2.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta5x3.setBackgroundResource(R.drawable.seta_cero);
                        btnSeta5x4.setBackgroundResource(R.drawable.seta_cero);

                        btnSeta1x1.setEnabled(true);
                        btnSeta1x2.setEnabled(true);
                        btnSeta1x3.setEnabled(true);
                        btnSeta1x4.setEnabled(true);
                        btnSeta2x1.setEnabled(true);
                        btnSeta2x2.setEnabled(true);
                        btnSeta2x3.setEnabled(true);
                        btnSeta2x4.setEnabled(true);
                        btnSeta3x1.setEnabled(true);
                        btnSeta3x2.setEnabled(true);
                        btnSeta3x3.setEnabled(true);
                        btnSeta3x4.setEnabled(true);
                        btnSeta4x1.setEnabled(true);
                        btnSeta4x2.setEnabled(true);
                        btnSeta4x3.setEnabled(true);
                        btnSeta4x4.setEnabled(true);
                        btnSeta5x1.setEnabled(true);
                        btnSeta5x2.setEnabled(true);
                        btnSeta5x3.setEnabled(true);
                        btnSeta5x4.setEnabled(true);

                        tragos = 0;
                        contador =0;
                    }
                });

                //mostramos el alertbox
                alertbox.show();

            }
        }
    }
}