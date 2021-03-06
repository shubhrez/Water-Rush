package com.healthtapper.sixtyseconds;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Tap1 {

    private FreePlayGameView gameView;
    private Bitmap bmp,bucket;
    private int MAX_SPEED = 5;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int width,height;

    public Tap1(FreePlayGameView gameView, Bitmap bmp,Bitmap bucket,int x,int y) {

        this.gameView = gameView;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        ySpeed = 20;
        this.bucket = bucket;
    }

    private void update(int a) {
        y = y + (ySpeed) + a/50;

    }


    public void onDraw(Canvas canvas,int scorefactor) {

        update(scorefactor);
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

