package com.app.myapplication;

import static java.lang.Thread.sleep;

public class WheelCoin extends Thread {

    interface WheelListener {
        void newImage(int img);
    }

    private static final int[] imgs = {R.drawable.moneda_cara, R.drawable.moneda_cruz};

    public int currentIndex;
    private WheelListener wheelListener;
    private long frameDuration;
    private long startIn;
    private boolean isStarted;

    public WheelCoin(WheelListener wheelListener, long frameDuration, long startIn) {
        this.wheelListener = wheelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        isStarted = true;
    }

    public void nextImg() {
        currentIndex++;

        if (currentIndex == imgs.length) {
            currentIndex = 0;
        }
    }

    @Override
    public void run() {
        try {
            sleep(startIn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(isStarted) {
            try {
                sleep(frameDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nextImg();

            if (wheelListener != null) {
                wheelListener.newImage(imgs[currentIndex]);
            }
        }
    }

    public void stopWheel() {
        isStarted = false;
    }
}