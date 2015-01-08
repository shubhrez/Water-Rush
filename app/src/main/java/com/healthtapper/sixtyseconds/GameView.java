package com.healthtapper.sixtyseconds;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Boolean running = false;
    Thread thread = null;
    int timeleft = 60;
    Bitmap drop,bucket,drop5;
    private List<Drop> drops = new ArrayList<Drop>();
    private List<Drop> drops5 = new ArrayList<Drop>();
    float x = 250;
    static final long FPS = 20;
    int score = 0;
    long clockStart;
    int secondCollected = 0;
    int timelToBeDisplayed = 0;


    public GameView(Context context) {
        super(context);
        holder = getHolder();
        drop = BitmapFactory.decodeResource(getResources(), R.drawable.drop);
        drop5 = BitmapFactory.decodeResource(getResources(), R.drawable.drop5);
        bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucket);

        createDrops();
        createDrops5();

    }

    private void createDrops() {
        drops.add(createDrop(R.drawable.drop));
        drops.add(createDrop(R.drawable.drop));
        drops.add(createDrop(R.drawable.drop));
        drops.add(createDrop(R.drawable.drop));
    }

    private void createDrops5() {
        drops5.add(createDrop(R.drawable.drop5));
    }

    private Drop createDrop(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Drop(this, bmp,bucket);
    }

    public void render(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        int timeElapsed = (int) ((System.currentTimeMillis() - clockStart)/1000);
  //      if(timeleft >= 1)
        timelToBeDisplayed = timeleft - timeElapsed;
        Paint textPaint = new Paint();
        textPaint.setTextSize(150);
        textPaint.setColor(Color.RED);
        String timeLeftText = String.valueOf(timelToBeDisplayed);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(timeLeftText,getWidth()/2,getHeight()/2,textPaint);
        canvas.drawBitmap(bucket,x - bucket.getWidth()/2,getHeight()-200,null);
        String scoretext = String.valueOf(score);
        textPaint.setTextSize(50);
        canvas.drawText(new StringBuilder().append("Score : ").append(scoretext).toString(), getWidth() / 2, 50, textPaint);

        for (Drop drop : drops) {
            drop.onDraw(canvas);
        }


        if(timelToBeDisplayed <= 50 && timelToBeDisplayed >= 45) {
            for (Drop drop : drops5) {
                drop.onDraw(canvas);
            }
        }

        synchronized (holder) {

                for (int i = drops.size() - 1; i >= 0; i--) {
                    Drop drop = drops.get(i);
                    if (drop.isCollision(getHeight())) {
                        drops.remove(drop);
                        drops.add(createDrop(R.drawable.drop));
                    }
                }

            for (int i = drops.size() - 1; i >= 0; i--) {
                Drop drop = drops.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops.remove(drop);
                    drops.add(createDrop(R.drawable.drop));
                    score += 1;
                }
            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop drop = drops5.get(i);
                if (drop.isCollision(getHeight())) {
                    drops5.remove(drop);
                    drops5.add(createDrop(R.drawable.drop5));
                }
            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop drop = drops5.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops5.remove(drop);
                    drops5.add(createDrop(R.drawable.drop5));
                    timeleft += 5;
                }
            }

            }


             if(timelToBeDisplayed  <= 0) {
                 gameoverActivity();
                 timeleft = 60;
                 score = 0;
                 for (int i = drops.size() - 1; i >= 0; i--) {
                     Drop drop = drops.get(i);
                     drops.remove(drop);
                     drops.add(createDrop(R.drawable.drop));
                 }
             }

        }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        return true;
    }


    public void pause() {
        running = false;
        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        thread = null;
        if(timelToBeDisplayed != 0)
            timeleft = timelToBeDisplayed;
    }

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void gameoverActivity(){
        Context context = getContext();
        Intent intent = new Intent("com.healthtapper.sixtyseconds.GAMEOVER");
//        Bundle bundle = new Bundle();
////Add your data from getFactualResults method to bundle
//        bundle.putInt("SCORE", score);
////Add the bundle to the intent
//        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public void run() {
        clockStart = System.currentTimeMillis();
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {

            if(!holder.getSurface().isValid())
                continue;

            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = holder.lockCanvas();
                synchronized (holder) {
                    render(c);
                }
            } finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    thread.sleep(sleepTime);
                else
                    thread.sleep(10);
            } catch (Exception e) {
            }

        }
    }
}