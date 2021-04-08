package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivitySlotMachine extends AppCompatActivity {

    private ImageView slot1, slot2, slot3;
    private TextView tvTragos;
    private WheelSlot wheel1, wheel2, wheel3;

    private String auxiliar;
    private int tragos;

    private boolean isStarted;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slot_machine);

        //Asignacion de ImageViews
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);

        //Asignacion de TextView
        tvTragos = findViewById(R.id.textViewSlotTragos);
    }

    public void tiradaSlot(View v){

        runWheells();

        tvTragos.setText("");

        if (isStarted) {

            CountDownTimer ct1 = new CountDownTimer(7000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {

                    stopWheells();

                }

            }.start();
        }
    }

    public void runWheells(){


        wheel1 = new WheelSlot(new WheelSlot.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot1.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(0, 200));

        wheel1.start();

        wheel2 = new WheelSlot(new WheelSlot.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot2.setImageResource(img);
                    }
                });
            }
        }, 190, randomLong(150, 400));

        wheel2.start();

        wheel3 = new WheelSlot(new WheelSlot.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot3.setImageResource(img);
                    }
                });
            }
        }, 190, randomLong(150, 400));

        wheel3.start();

        isStarted = true;
    }

    public void stopWheells(){

        wheel1.stopWheel();
        wheel2.stopWheel();
        wheel3.stopWheel();



        if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {

            switch (wheel1.currentIndex){

                case  0:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 1");
                    break;
                case  1:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 2");
                    break;
                case  2:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 3");
                    break;
                case  3:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 5");
                    break;
                case  4:
                    auxiliar = (String) getText(R.string.mandas_bebes);
                    tvTragos.setText(auxiliar +" 7");
                    break;
                default:
                    break;
            }

        } else {
            auxiliar = (String) getText(R.string.bebes);
            tvTragos.setText(auxiliar +" 1");
        }

        isStarted = false;

    }

}