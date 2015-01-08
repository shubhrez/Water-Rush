package com.healthtapper.sixtyseconds;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Drop {

    private GameView gameView;
    private Bitmap bmp,bucket;
    private int MAX_SPEED = 5;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int width,height;

    public Drop(GameView gameView, Bitmap bmp,Bitmap bucket) {

        this.gameView = gameView;
        this.bmp = bmp;
        Random rnd = new Random();
        x = rnd.nextInt(500) + 1;
        ySpeed = rnd.nextInt(5) + 25;
        this.bucket = bucket;
    }

    private void update() {
        y = y + ySpeed;

    }


    public void onDraw(Canvas canvas) {

        update();
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
