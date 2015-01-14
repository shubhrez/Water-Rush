package com.healthtapper.sixtyseconds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;


public class StartGameView extends SurfaceView implements Runnable {

    static final long FPS = 20;
    long clockStart;
    SurfaceHolder holder;
    Thread thread = null;
    private List<StartDrop> drops = new ArrayList<StartDrop>();
    Bitmap bucket;
    int count = 0;
    Boolean running = false;
    private SoundPool sounds;
    private int thunder;
    int thunderCount = 0;

    public StartGameView(Context context) {
        super(context);
        holder = getHolder();
        bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucket);
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        thunder = sounds.load(context,R.raw.thunder,1);
        createDrops();
    }

    private void createDrops() {
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,-100));
        drops.add(createDrop(R.drawable.drop,-150));
        drops.add(createDrop(R.drawable.drop,-200));
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,-50));
        drops.add(createDrop(R.drawable.drop,-200));
        drops.add(createDrop(R.drawable.drop,-50));
        drops.add(createDrop(R.drawable.drop,-200));
        drops.add(createDrop(R.drawable.drop,-80));
    }

    private StartDrop createDrop(int resource,int y) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        //       int xDrop = rnd.nextInt(this.getWidth() - bmp.getWidth());
        return new StartDrop(this, bmp,y);
    }

    public void render(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        Paint background = new Paint();
        Rect back = new Rect(0,0,getWidth(),getHeight());

            if (thunderCount == 0) {
                sounds.play(thunder, 0.2f, 0.2f, 0, 0, 1.5f);
                thunderCount ++;
                background.setColor(Color.YELLOW);
                canvas.drawRect(back,background);
            } else if(thunderCount == 1) {
                thunderCount ++;
                background.setColor(Color.BLACK);
                canvas.drawRect(back, background);
            } else if (thunderCount == 2){
                thunderCount ++;
                background.setColor(Color.BLACK);
                canvas.drawRect(back,background);
            } else if(thunderCount == 3) {
                thunderCount ++;
                background.setColor(Color.YELLOW);
                canvas.drawRect(back, background);
            } else if (thunderCount == 4){
                thunderCount ++;
                background.setColor(Color.BLACK);
                canvas.drawRect(back,background);
            } else if (thunderCount == 5){
                thunderCount ++;
                background.setColor(Color.BLACK);
                canvas.drawRect(back,background);
            } else if (thunderCount == 6){
                thunderCount ++;
                background.setColor(Color.YELLOW);
                canvas.drawRect(back,background);
            }


        for (StartDrop drop : drops) {
            drop.onDraw(canvas);
        }

        Paint textPaint = new Paint();
        textPaint.setTextSize(75);
        textPaint.setARGB(255,0,178,255);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Water Rush", getWidth()/2, getHeight()/2, textPaint);

        synchronized (holder) {

            for (int i = drops.size() - 1; i >= 0; i--) {
                StartDrop drop = drops.get(i);
                if (drop.isCollision(getHeight())) {
                    drops.remove(drop);
                    drops.add(createDrop(R.drawable.drop, 0));
                    count++;
                }
            }
                if(count >= 10){
                    splashActivity();
                }

        }
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

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
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
    }


    public void splashActivity(){
        Context context = getContext();
        Intent intent = new Intent("com.healthtapper.sixtyseconds.SPLASH");
        context.startActivity(intent);
    }


}
