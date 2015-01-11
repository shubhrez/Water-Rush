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

public class GameView1 extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    Boolean running = false;
    Thread thread = null;
    int timeleft = 60;
    Bitmap drop,bucket,drop5,cloud,splash1,splash2,lightning;
    private List<Drop1> drops = new ArrayList<Drop1>();
    private List<Drop1> drops5 = new ArrayList<Drop1>();
    private List<Drop1> snow = new ArrayList<Drop1>();
    private List<Drop1> stones = new ArrayList<Drop1>();
    private List<Drop1> bigDrops = new ArrayList<Drop1>();
    private List<Tap1> taps = new ArrayList<Tap1>();
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
    int xtap;
    private int waterdrip,watersplash,freeze;
    int life = 0;
    int bigDropCollected = 0;

    public GameView1(Context context) {
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
        createDrops5();
        createStones();
        createSnow();
        createbigDrops();
        createTaps();

    }

    private void createDrops() {
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,0));
        //    drops.add(createDrop(R.drawable.drop));
    }
//
    private void createTaps() {
        xtap = rnd.nextInt(200) + 50;
        taps.add(createTap(R.drawable.drop,xtap,-16000));
        taps.add(createTap(R.drawable.drop,xtap,-16000-100));
        taps.add(createTap(R.drawable.drop,xtap,-16000-200));
        taps.add(createTap(R.drawable.drop,xtap,-16000-300));
        taps.add(createTap(R.drawable.drop,xtap,-16000-400));
        taps.add(createTap(R.drawable.drop,xtap,-16000-500));
        taps.add(createTap(R.drawable.drop,xtap,-16000-600));
        //       taps.add(createTap(R.drawable.drop,xtap,-700));
    }

    private void createStones() {
        stones.add(createDrop(R.drawable.stone,0));
        stones.add(createDrop(R.drawable.stone,0));
        //   stones.add(createDrop(R.drawable.stone));
    }

    private void createDrops5() {
        drops5.add(createDrop(R.drawable.drop5,-10000));
    }

    private void createbigDrops() {
        bigDrops.add(createDrop(R.drawable.bigdrop,-14000));
    }

    private void createSnow() {
        snow.add(createDrop(R.drawable.snow,-8000));
    }

    private Drop1 createDrop(int resource,int yPosition) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Drop1(this, bmp,bucket,yPosition);
    }

    private Tap1 createTap(int resource,int xTap,int yTap) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Tap1(this, bmp,bucket,xTap,yTap);
    }

    public void render(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        Paint background = new Paint();
        background.setARGB(50,0,222,255);
        Rect back = new Rect(0,0,getWidth(),getHeight());
        if(freezeFactor == 2){
            canvas.drawRect(back,background);
        }

        Paint textPaint = new Paint();
        textPaint.setTextSize(150);
//        textPaint.setColor(Color.RED);
//        String timeLeftText = String.valueOf(timelToBeDisplayed);
//        textPaint.setTextAlign(Paint.Align.CENTER);
        //      canvas.drawText(timeLeftText,getWidth()/2,getHeight()/2,textPaint);
//        canvas.drawText(new StringBuilder().append("00 : ").append(timeLeftText).toString(), 80, 80, textPaint);
//        canvas.drawBitmap(bucket,x - bucket.getWidth()/2,getHeight()-200,null);
        String scoretext = String.valueOf(score);
        String lifetext = String.valueOf(life);
        textPaint.setTextSize(70);
//        canvas.drawText(new StringBuilder().append("Score : ").append(scoretext).toString(), getWidth() / 2, 50, textPaint);

        for (Drop1 drop : drops) {
            drop.onDraw(canvas,score,freezeFactor);
        }


    //    if(timelToBeDisplayed <= 55) {
            for (Drop1 drop : stones) {
                drop.onDraw(canvas, score, freezeFactor);
            }
     //   }

        for (Tap1 tap : taps) {
            tap.onDraw(canvas,score);
        }




        for (Drop1 drop : drops5) {
            drop.onDraw(canvas,score,freezeFactor);
        }

        for (Drop1 drop : bigDrops) {
            drop.onDraw(canvas,score,freezeFactor);
        }

        for (Drop1 drop : snow) {
            drop.onDraw(canvas,score,freezeFactor);
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
        canvas.drawText(new StringBuilder().append(scoretext).toString(), getWidth() - 120, 80, textPaint);
        canvas.drawText(new StringBuilder().append(lifetext).toString(), 40, 80, textPaint);
//        textPaint.setTextSize(60);
//        if(timelToBeDisplayed >= 10) {
//            canvas.drawText(new StringBuilder().append("00:").append(timeLeftText).toString(), 100, 80, textPaint);
//        } else {
//            canvas.drawText(new StringBuilder().append("00:0").append(timeLeftText).toString(), 100, 80, textPaint);
//        }
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
                Drop1 drop = drops.get(i);
                if (drop.isCollision(getHeight())) {
                    drops.remove(drop);
                    drops.add(createDrop(R.drawable.drop,0));
                }
            }

            for (int i = drops.size() - 1; i >= 0; i--) {
                Drop1 drop = drops.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops.remove(drop);
                    drops.add(createDrop(R.drawable.drop,0));
                    score += 1;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                }
            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop1 drop = drops5.get(i);
                if (drop.isCollision(getHeight())) {
                    drops5.remove(drop);
                    drops5.add(createDrop(R.drawable.drop5,-10000));
                    //             drops5.add(createDrop(R.drawable.drop5));

                }

            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop1 drop = drops5.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops5.remove(drop);
                    //           drops5.add(createDrop(R.drawable.drop5));
                    drops5.add(createDrop(R.drawable.drop5,-10000));
                    score += 5;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    displayfiveseconds = 1;

                }

            }

            for (int i = bigDrops.size() - 1; i >= 0; i--) {
                Drop1 drop = bigDrops.get(i);
                if (drop.isCollision(getHeight())) {
                    bigDrops.remove(drop);
                    bigDrops.add(createDrop(R.drawable.bigdrop,-14000));
                    //             drops5.add(createDrop(R.drawable.drop5));

                }

            }

            for (int i = bigDrops.size() - 1; i >= 0; i--) {
                Drop1 drop = bigDrops.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    bigDrops.remove(drop);
                    //           drops5.add(createDrop(R.drawable.drop5));
                    bigDrops.add(createDrop(R.drawable.bigdrop,-14000));
                    score += 10;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);

                }

            }

            for (int i = snow.size() - 1; i >= 0; i--) {
                Drop1 drop = snow.get(i);
                if (drop.isCollision(getHeight())) {
                    snow.remove(drop);
                    snow.add(createDrop(R.drawable.snow,-8000));
                    //             drops5.add(createDrop(R.drawable.drop5));
                }

            }

            for (int i = snow.size() - 1; i >= 0; i--) {
                Drop1 drop = snow.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    snow.remove(drop);
                    //           drops5.add(createDrop(R.drawable.drop5));
                    snow.add(createDrop(R.drawable.snow,-8000));
                    freezeFactor = 2;
                    sounds.play(freeze, 1.0f, 1.0f, 0, 0, 1.5f);
                }

            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop1 drop = stones.get(i);
                if (drop.isCollision(getHeight())) {
                    stones.remove(drop);
                    stones.add(createDrop(R.drawable.stone,0));
                }
            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop1 drop = stones.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    stones.remove(drop);
                    stones.add(createDrop(R.drawable.stone,0));
                    score -= 5;
                    life ++;
                    sounds.play(watersplash, 0.05f, 0.05f, 0, 0, 1.5f);
                    splash = 1;
                    //               startSplash(System.currentTimeMillis());
                }
            }

            for (int i = taps.size() - 1; i >= 0; i--) {
                Tap1 tap = taps.get(i);
                if (tap.isCollision(getHeight())) {
                    taps.remove(tap);
                    taps.add(createTap(R.drawable.drop,xtap,-16000));
                    //         taps.add(createDrop(R.drawable.drop));
                }
            }

            for (int i = taps.size() - 1; i >= 0; i--) {
                Tap1 tap = taps.get(i);
                if (tap.isCollected(x - bucket.getWidth() / 2)) {
                    taps.remove(tap);
                    taps.add(createTap(R.drawable.drop,xtap,-16000));
                    //       tap.add(createDrop(R.drawable.drop));
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    score += 1;
                }
            }

        }


        if(life  >= 3) {
            gameoverActivity();
//            timeleft = 60;
            score = 0;
            life =0 ;
//            tapcount = 0;
 //           fivecount = 0;
  //          bigdropcount = 0;
   //         snowcount = 0;
            for (int i = drops.size() - 1; i >= 0; i--) {
                Drop1 drop = drops.get(i);
                drops.remove(drop);
                drops.add(createDrop(R.drawable.drop,0));
            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop1 drop = stones.get(i);
                stones.remove(drop);
                stones.add(createDrop(R.drawable.stone,0));
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        return true;
    }

//    public void startSplash(long time) {
//
//        while (System.currentTimeMillis() <= time + 1000) {
//            splash = 1;
//        }
//        splash = 0;
//    }
//
//


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
        Intent intent = new Intent("com.healthtapper.sixtyseconds.GAMEOVER1");
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