package com.healthtapper.sixtyseconds;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Power {

    private GameView gameView;
    private Bitmap bmp,bucket;
    private int MAX_SPEED = 5;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int width,height;

    public Power(GameView gameView, Bitmap bmp,Bitmap bucket,int y) {

        this.gameView = gameView;
        this.bmp = bmp;
        Random rnd = new Random();
        x = rnd.nextInt(500) + 1;
        this.y = y;
        ySpeed = rnd.nextInt(5) + 25;
        this.bucket = bucket;
    }

    private void update(int a,int b) {
        y = (y + (ySpeed)/b + a/5);

    }


    public void onDraw(Canvas canvas,int speedfactor,int freezeFactor) {
        update(speedfactor,freezeFactor);
        canvas.drawBitmap(bmp,x,y, null);
    }


    public boolean isCollision(float y1) {

        return (y > y1) ;
    }

    public boolean isCollected(float x1) {

        return (x + (bmp.getWidth()/2) >= x1 + 5 && x + (bmp.getWidth()/2) <= x1 + bucket.getWidth() - 5
                && y + bmp.getHeight() >= gameView.getHeight() - 190 && y + bmp.getHeight() <= gameView.getHeight() - 160 );
    }
}

