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
import android.os.CountDownTimer;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Boolean running = false;
    Thread thread = null;
    int timeleft = 60;
    Bitmap drop,bucket,drop5,cloud,splash1,splash2,lightning;
    private List<Drop> drops = new ArrayList<Drop>();
    private List<Drop> drops5 = new ArrayList<Drop>();
    private List<Drop> snow = new ArrayList<Drop>();
    private List<Drop> stones = new ArrayList<Drop>();
    private List<Drop> bigDrops = new ArrayList<Drop>();
    private List<Tap> taps = new ArrayList<Tap>();
    float x = 250;
    static final long FPS = 20;
    int score = 0;
    long clockStart;
    int secondCollected = 0;
    int timelToBeDisplayed = 0;
    int tapcount = 0;
    int fivecount = 0;
    int bigdropcount = 0;
    int snowcount = 0;
    int t1,t2,t3,e1,e2,s1,s2,b1,b2;
    int splash = 0;
    int splashcount = 0;
    int displayfiveseconds = 0;
    int displayfivesecondscount = 0;
    Random rnd = new Random();
    int freezeFactor = 1;
    int freezecount = 0;
    int x1 = 200;
    int x2 = 300;
    int x1speed = 20;
    int x2speed = 20;
    private SoundPool sounds;
    private int waterdrip,watersplash,freeze;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        drop = BitmapFactory.decodeResource(getResources(), R.drawable.drop);
        drop5 = BitmapFactory.decodeResource(getResources(), R.drawable.drop5);
        cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
        bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucket);
        splash1 = BitmapFactory.decodeResource(getResources(), R.drawable.splash1);
        splash2 = BitmapFactory.decodeResource(getResources(), R.drawable.splash2);
        lightning = BitmapFactory.decodeResource(getResources(), R.drawable.lightning);
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        waterdrip = sounds.load(context,R.raw.waterdrip,1);
        watersplash = sounds.load(context,R.raw.watersplash,1);
        freeze = sounds.load(context,R.raw.freeze,1);
        createDrops();
//        createDrops5();
        createStones();
//        createTaps();

    }

    private void createDrops() {
        drops.add(createDrop(R.drawable.drop));
        drops.add(createDrop(R.drawable.drop));
        drops.add(createDrop(R.drawable.drop));
    //    drops.add(createDrop(R.drawable.drop));
    }

    private void createTaps() {
        int xtap = rnd.nextInt(200) + 50;
        taps.add(createTap(R.drawable.drop,xtap,0));
        taps.add(createTap(R.drawable.drop,xtap,-100));
        taps.add(createTap(R.drawable.drop,xtap,-200));
        taps.add(createTap(R.drawable.drop,xtap,-300));
        taps.add(createTap(R.drawable.drop,xtap,-400));
        taps.add(createTap(R.drawable.drop,xtap,-500));
        taps.add(createTap(R.drawable.drop,xtap,-600));
 //       taps.add(createTap(R.drawable.drop,xtap,-700));
    }

    private void createStones() {
        stones.add(createDrop(R.drawable.stone));
        stones.add(createDrop(R.drawable.stone));
     //   stones.add(createDrop(R.drawable.stone));
    }

    private void createDrops5() {
        drops5.add(createDrop(R.drawable.drop5));
    }

    private void createbigDrops() {
        bigDrops.add(createDrop(R.drawable.bigdrop));
    }

    private void createSnow() {
        snow.add(createDrop(R.drawable.snow));
    }

    private Drop createDrop(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Drop(this, bmp,bucket);
    }

    private Tap createTap(int resource,int xTap,int yTap) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Tap(this, bmp,bucket,xTap,yTap);
    }

    public void render(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        Paint background = new Paint();
        background.setARGB(50,0,222,255);
        Rect back = new Rect(0,0,getWidth(),getHeight());
        if(freezeFactor == 2){
            canvas.drawRect(back,background);
        }
//        centerRect.set(0, getHeight() - 300, getWidth(), getHeight() - 290);
//        canvas.drawRect(back, background);
        int timeElapsed = (int) ((System.currentTimeMillis() - clockStart)/1000);
  //      if(timeleft >= 1)
        timelToBeDisplayed = timeleft - timeElapsed;

        canvas.drawBitmap(lightning,x1,0,null);
        canvas.drawBitmap(lightning,x2,0,null);
        if(x1 >= getWidth() - lightning.getWidth() || x1 <= 0) {
            x1speed = -x1speed;
        }

        if(x2 >= getWidth() - lightning.getWidth() || x2 <= 0) {
            x2speed = -x2speed;
        }
        x1 = x1 + 4*x1speed;
        x2 = x2 + 4*x2speed;

        Paint textPaint = new Paint();
        textPaint.setTextSize(150);
        textPaint.setColor(Color.RED);
        String timeLeftText = String.valueOf(timelToBeDisplayed);
        textPaint.setTextAlign(Paint.Align.CENTER);
  //      canvas.drawText(timeLeftText,getWidth()/2,getHeight()/2,textPaint);
//        canvas.drawText(new StringBuilder().append("00 : ").append(timeLeftText).toString(), 80, 80, textPaint);
//        canvas.drawBitmap(bucket,x - bucket.getWidth()/2,getHeight()-200,null);
        String scoretext = String.valueOf(score);
        textPaint.setTextSize(70);
//        canvas.drawText(new StringBuilder().append("Score : ").append(scoretext).toString(), getWidth() / 2, 50, textPaint);

        for (Drop drop : drops) {
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }


        if(timelToBeDisplayed <= 55) {
            for (Drop drop : stones) {
                drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
            }
        }

            for (Tap tap : taps) {
                tap.onDraw(canvas,(61-timelToBeDisplayed));
            }


   //     if(timelToBeDisplayed == 50) {
        if(timelToBeDisplayed == t1) {
            if(tapcount == 0){
                createTaps();
                tapcount = 1;
            }
        }

      //  if(timelToBeDisplayed == 30) {
        if(timelToBeDisplayed == t2) {
            if(tapcount == 1){
                createTaps();
                tapcount = 2;
            }
        }

        if(timelToBeDisplayed == t3) {
            if(tapcount == 2){
                createTaps();
                tapcount = 3;
            }
        }


        for (Drop drop : drops5) {
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

        for (Drop drop : bigDrops) {
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

        for (Drop drop : snow) {
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

//        if(timelToBeDisplayed == 40) {
//        if(timelToBeDisplayed == e1 || timelToBeDisplayed == e2) {
//            if(fivecount == 0){
//                createDrops5();
//                fivecount = 1;
//            }
//        }


        if(timelToBeDisplayed == e1 ) {
            if(fivecount == 0){
                createDrops5();
                fivecount = 1;
            }
        }


   //     if(timelToBeDisplayed == 20) {
        if(timelToBeDisplayed == e2) {
            if(fivecount == 1){
                createDrops5();
                fivecount = 2;
            }
        }

        if(timelToBeDisplayed == b1 ) {
            if(bigdropcount == 0){
                createbigDrops();
                bigdropcount = 1;
            }
        }


        //     if(timelToBeDisplayed == 20) {
        if(timelToBeDisplayed == b2) {
            if(bigdropcount == 1){
                createbigDrops();
                bigdropcount = 2;
            }
        }

        if(timelToBeDisplayed == s1) {
            if(snowcount == 0){
                createSnow();
                snowcount = 1;
            }
        }

        if(timelToBeDisplayed == s2) {
            if(snowcount == 1){
                createSnow();
                snowcount = 2;
            }
        }

        canvas.drawBitmap(bucket,x - bucket.getWidth()/2,getHeight()-200,null);
        Paint bucketsupport = new Paint();
        bucketsupport.setARGB(150,0,178,255);
   //     Rect bucketsupportRect = new Rect((int) (x - bucket.getWidth()/2) + 10,getHeight() - 60,(int) (x + bucket.getWidth()/2) - 10,getHeight());
      //  canvas.drawRect(bucketsupportRect,bucketsupport);
  //      canvas.drawCircle(x,getHeight() + 40,bucket.getWidth()/2 + 28,bucketsupport);
        Rect bucketsupportRect = new Rect(0,getHeight() - 60,getWidth(),getHeight());
        canvas.drawRect(bucketsupportRect,bucketsupport);
        canvas.drawBitmap(cloud,getWidth()/4-cloud.getWidth()/2,-100,null);
        canvas.drawBitmap(cloud,3*getWidth()/4-cloud.getWidth()/2 + 50,-100,null);
        textPaint.setARGB(255,0,178,255);
//        canvas.drawText(new StringBuilder().append("Score : ").append(scoretext).toString(), getWidth() / 2, 80, textPaint);
//        bucketsupport.setARGB(255,0,0,0);
//        canvas.drawCircle(getWidth()-80,60,50,bucketsupport);
        canvas.drawText(new StringBuilder().append(scoretext).toString(), getWidth() - 80, 80, textPaint);
        textPaint.setTextSize(60);
        if(timelToBeDisplayed >= 10) {
            canvas.drawText(new StringBuilder().append("00:").append(timeLeftText).toString(), 100, 80, textPaint);
        } else {
            canvas.drawText(new StringBuilder().append("00:0").append(timeLeftText).toString(), 100, 80, textPaint);
        }
        if(splash == 1) {
            if (splashcount <= 10) {
                canvas.drawBitmap(splash1, x + bucket.getWidth() / 2 + 5, getHeight() - 200, null);
                canvas.drawBitmap(splash2, x - bucket.getWidth() / 2 - 65, getHeight() - 200, null);
                canvas.drawBitmap(splash1, x + bucket.getWidth() / 2 + 45, getHeight() - 160, null);
                canvas.drawBitmap(splash2, x - bucket.getWidth() / 2 - 105, getHeight() - 160, null);
                splashcount++;
            } else {
                splash = 0;
                splashcount = 0;
            }
        }

        if(displayfiveseconds == 1) {
            if (displayfivesecondscount <= 20) {
                textPaint.setTextSize(50);
                canvas.drawText("+ 5 sec",getWidth()/2,getHeight()/2 -200,textPaint);
                displayfivesecondscount ++;
            } else {
                displayfiveseconds = 0;
                displayfivesecondscount = 0;
            }
        }

        if(freezeFactor == 2) {
            if (freezecount <= 200) {
                freezecount += 1 ;
            } else {
                freezeFactor = 1;
                freezecount = 0;
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
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                }
            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop drop = drops5.get(i);
                if (drop.isCollision(getHeight())) {
                    drops5.remove(drop);
       //             drops5.add(createDrop(R.drawable.drop5));

                }

            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop drop = drops5.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops5.remove(drop);
         //           drops5.add(createDrop(R.drawable.drop5));
                    timeleft += 5;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    displayfiveseconds = 1;

                }

            }

            for (int i = bigDrops.size() - 1; i >= 0; i--) {
                Drop drop = bigDrops.get(i);
                if (drop.isCollision(getHeight())) {
                    bigDrops.remove(drop);
                    //             drops5.add(createDrop(R.drawable.drop5));

                }

            }

            for (int i = bigDrops.size() - 1; i >= 0; i--) {
                Drop drop = bigDrops.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    bigDrops.remove(drop);
                    //           drops5.add(createDrop(R.drawable.drop5));
                    score += 10;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);

                }

            }

            for (int i = snow.size() - 1; i >= 0; i--) {
                Drop drop = snow.get(i);
                if (drop.isCollision(getHeight())) {
                    snow.remove(drop);
                    //             drops5.add(createDrop(R.drawable.drop5));
                }

            }

            for (int i = snow.size() - 1; i >= 0; i--) {
                Drop drop = snow.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    snow.remove(drop);
                    //           drops5.add(createDrop(R.drawable.drop5));
                    freezeFactor = 2;
                    sounds.play(freeze, 1.0f, 1.0f, 0, 0, 1.5f);
                }

            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop drop = stones.get(i);
                if (drop.isCollision(getHeight())) {
                    stones.remove(drop);
                    stones.add(createDrop(R.drawable.stone));
                }
            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop drop = stones.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    stones.remove(drop);
                    stones.add(createDrop(R.drawable.stone));
                    score -= 5;
                    sounds.play(watersplash, 0.05f, 0.05f, 0, 0, 1.5f);
                    splash = 1;
     //               startSplash(System.currentTimeMillis());
                }
            }

            for (int i = taps.size() - 1; i >= 0; i--) {
                Tap tap = taps.get(i);
                if (tap.isCollision(getHeight())) {
                    taps.remove(tap);

           //         taps.add(createDrop(R.drawable.drop));
                }
            }

            for (int i = taps.size() - 1; i >= 0; i--) {
                Tap tap = taps.get(i);
                if (tap.isCollected(x - bucket.getWidth() / 2)) {
                    taps.remove(tap);
             //       tap.add(createDrop(R.drawable.drop));
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    score += 1;
                }
            }

            }


             if(timelToBeDisplayed  <= 0) {
                 gameoverActivity();
                 timeleft = 60;
                 score = 0;
                 tapcount = 0;
                 fivecount = 0;
                 bigdropcount = 0;
                 snowcount = 0;
                 for (int i = drops.size() - 1; i >= 0; i--) {
                     Drop drop = drops.get(i);
                     drops.remove(drop);
                     drops.add(createDrop(R.drawable.drop));
                 }

                 for (int i = stones.size() - 1; i >= 0; i--) {
                     Drop drop = stones.get(i);
                     stones.remove(drop);
                     stones.add(createDrop(R.drawable.stone));
                 }
             }

        }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        return true;
    }

    public void startSplash(long time) {

        while (System.currentTimeMillis() <= time + 1000) {
            splash = 1;
        }
         splash = 0;
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
        t1 = rnd.nextInt(10) + 30;
        t2 = rnd.nextInt(10) + 20;
        t2 = rnd.nextInt(10) + 10;
        e1 = rnd.nextInt(20) + 30;
        e2 = rnd.nextInt(20) + 10;
        s1 = rnd.nextInt(10) + 30;
        s2 = rnd.nextInt(10) + 10;
        b1 = rnd.nextInt(10) + 40;
        b2 = rnd.nextInt(10) + 20;
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