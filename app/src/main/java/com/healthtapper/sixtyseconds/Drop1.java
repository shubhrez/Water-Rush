package com.healthtapper.sixtyseconds;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Drop1 {

    private FreePlayGameView gameView;
    public Bitmap bmp,bucket;
    private int MAX_SPEED = 5;
    public int x = 0;
    private int y = 0;
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int width,height;

    public Drop1(FreePlayGameView gameView, Bitmap bmp,Bitmap bucket,int y) {

        this.gameView = gameView;
        this.bmp = bmp;
        Random rnd = new Random();
        x = rnd.nextInt(700) + 1;
        this.y = y;
        ySpeed = rnd.nextInt(5) + 30;
        this.bucket = bucket;
    }

    private void update(int a,int b) {
        y = (y + (ySpeed)/b + a/40);

    }


    public void onDraw(Canvas canvas,int scorefactor,int freezeFactor) {

        update(scorefactor,freezeFactor);
        canvas.drawBitmap(bmp,x,y, null);
    }


    public boolean isCollision(float y1) {

        return (y > y1) ;
    }

    public boolean isCollected(float x1) {

        return (x + (bmp.getWidth()/2) >= x1 + 5 && x + (bmp.getWidth()/2) <= x1 + bucket.getWidth()
                && y + bmp.getHeight() >= gameView.getHeight() - 180 && y + bmp.getHeight() <= gameView.getHeight() - 130 );
    }
}
