package com.healthtapper.sixtyseconds;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
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
    Bitmap drop,bucket,drop5,cloud,cloudflip,splash1,splash2,lightning,pause,grass;
    private List<Drop> drops = new ArrayList<Drop>();
    private List<Drop> crystal = new ArrayList<Drop>();
    private List<Drop> drops5 = new ArrayList<Drop>();
    private List<Drop> snow = new ArrayList<Drop>();
    private List<Drop> stones = new ArrayList<Drop>();
    private List<Drop> bigDrops = new ArrayList<Drop>();
    private List<Drop> bigDropsSelected = new ArrayList<Drop>();
    private List<Drop> snowSelected = new ArrayList<Drop>();
    private List<Tap> taps = new ArrayList<Tap>();

    public static final String HIGHESTSCORE = "highestscore";
    public static final String BUCKET = "bucket";
    public static final String GAMEVIEWSTATE = "gameviewstate";
    public static final String PAUSESTATE = "pausestate";
    int pauseState = 0;

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
    int t1,t2,t3,e1,e2,s1,s2,b1,b2,c1,c2;
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
    private int waterdrip,watersplash,freeze,thunder,power,emptydrop;
    int bigDropCollected = 0;
 //   int bigDropTime = 0;
    int snowCollected = 0;
 //   int snowTime = 0;
    int thunderStart = 0;
    int thunderCount = 0;
    int crystalCount = 0;
    int xtap = rnd.nextInt(200) + 50;;
    int highestscore;
    float pauseX,pauseY;
    int bigNumber = 0;
    int bigScore = 0;
    int bigScoreDisplay = 0;
    int bigScoreCount = 0;
    int snowNumber = 0;
    int snowScore = 0;
    int snowScoreDisplay = 0;
    int snowScoreCount = 0;
    int bonusCount = 0;
    int displayCrystalSelected = 0;
    int displayCrystalSelectedCount = 0;
    Typeface font,font1;

    public GameView(Context context) {
        super(context);
        holder = getHolder();

        drop = BitmapFactory.decodeResource(getResources(), R.drawable.drop);
        pause = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
        drop5 = BitmapFactory.decodeResource(getResources(), R.drawable.drop5);
        cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
        grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        cloudflip = BitmapFactory.decodeResource(getResources(), R.drawable.cloudflip);
        int bucketSize = Splash.pref.getInt(BUCKET,0);
        if(bucketSize <= 0){
            bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucketsmall);
        }
        if(bucketSize >= 1 && bucketSize <= 4){
            bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucket);
        }
        if(bucketSize >= 5) {
            bucket = BitmapFactory.decodeResource(getResources(), R.drawable.superbucket);
        }

        splash1 = BitmapFactory.decodeResource(getResources(), R.drawable.splash1);
        splash2 = BitmapFactory.decodeResource(getResources(), R.drawable.splash2);
        lightning = BitmapFactory.decodeResource(getResources(), R.drawable.lightning);
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        waterdrip = sounds.load(context,R.raw.waterdrip,1);
        power = sounds.load(context,R.raw.power,1);
        watersplash = sounds.load(context,R.raw.watersplash,1);
        freeze = sounds.load(context,R.raw.freeze,1);
        thunder = sounds.load(context,R.raw.thunder,1);
        emptydrop = sounds.load(context,R.raw.emptydrop,1);
        font = Typeface.createFromAsset(context.getAssets(), "Buffied.ttf");
   //     font1 = Typeface.createFromAsset(context.getAssets(), "WindyRainDemo.ttf");

        createDrops();
        createDrops5();
        createStones();
        createTaps();
        createCrystal();
        createbigDrops();
        createSnow();
    }

    private void createDrops() {
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,0));
        drops.add(createDrop(R.drawable.drop,0));
    }

    private void createbigDropsSelected() {
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,0));
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,-450));
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,-350));
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,0));
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,-275));
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,-500));
        bigDropsSelected.add(createDrop(R.drawable.bigdropselected,-400));
    }

    private void createsnowSelected() {
        snowSelected.add(createDrop(R.drawable.snowselected,0));
        snowSelected.add(createDrop(R.drawable.snowselected,-400));
        snowSelected.add(createDrop(R.drawable.snowselected,-600));
        snowSelected.add(createDrop(R.drawable.snowselected,-350));
        snowSelected.add(createDrop(R.drawable.snowselected,-250));
        snowSelected.add(createDrop(R.drawable.snowselected,0));
        snowSelected.add(createDrop(R.drawable.snowselected,-280));
        snowSelected.add(createDrop(R.drawable.snowselected,-500));
    }

    private void createTaps() {

        taps.add(createTap(R.drawable.drop,xtap,-6000));
        taps.add(createTap(R.drawable.drop,xtap,-6100));
        taps.add(createTap(R.drawable.drop,xtap,-6200));
        taps.add(createTap(R.drawable.drop,xtap,-6300));
        taps.add(createTap(R.drawable.drop,xtap,-6400));
        taps.add(createTap(R.drawable.drop,xtap,-6500));
        taps.add(createTap(R.drawable.drop,xtap,-6600));
 //       taps.add(createTap(R.drawable.drop,xtap,-700));
    }

    private void createStones() {
        stones.add(createDrop(R.drawable.stone,0));
        stones.add(createDrop(R.drawable.stone,0));
   //     stones.add(createDrop(R.drawable.stone,0));
    }

//    private Power createPower(int resource,int y) {
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
//        return new Power(this, bmp,bucket,y);
//    }

    private void createDrops5() {
        drops5.add(createDrop(R.drawable.drop5,-12000));
    }

    private void createCrystal() {
        crystal.add(createDrop(R.drawable.cyrstal,-20000));
    }

    private void createbigDrops() {
        bigDrops.add(createDrop(R.drawable.bigdrop,-15000));
    }

    private void createSnow() {
        snow.add(createDrop(R.drawable.snow,-22000));
    }

    private Drop createDrop(int resource,int y) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
 //       int xDrop = rnd.nextInt(this.getWidth() - bmp.getWidth());
        return new Drop(this, bmp,bucket,y);
    }

    private Tap createTap(int resource,int xTap,int yTap) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Tap(this, bmp,bucket,xTap,yTap);
    }

    public void render(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        int bucketSize = Splash.pref.getInt(BUCKET,0);

        Paint background = new Paint();
        Rect back = new Rect(0,0,getWidth(),getHeight());

        if(thunderStart == 1) {
            if (thunderCount == 0) {
                thunderCount ++;
                background.setColor(Color.YELLOW);
                canvas.drawRect(back,background);
            }
            if(thunderCount == 1) {
                thunderCount ++;
                background.setColor(Color.BLACK);
                canvas.drawRect(back, background);
            }
            if (thunderCount == 2){
                background.setColor(Color.YELLOW);
                canvas.drawRect(back,background);
                thunderStart = 0;
                thunderCount = 0;
            }
        }

        if(freezeFactor == 2){
            background.setARGB(50,0,222,255);
            canvas.drawRect(back,background);
        }
//        centerRect.set(0, getHeight() - 300, getWidth(), getHeight() - 290);
//        canvas.drawRect(back, background);
        int timeElapsed = (int) ((System.currentTimeMillis() - clockStart)/1000);
  //      if(timeleft >= 1)
        timelToBeDisplayed = timeleft - timeElapsed;

        Paint textPaint = new Paint();
        textPaint.setTextSize(150);
        textPaint.setColor(Color.RED);
        String timeLeftText = String.valueOf(timelToBeDisplayed);
//        textPaint.setTextAlign(Paint.Align.CENTER);
        String scoretext = String.valueOf(score);
        textPaint.setTextSize(70);

        for (Drop drop : drops) {
            if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                drop.x = getWidth()/2;
            }
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

        for (Drop drop : bigDropsSelected) {
            if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                drop.x = getWidth()/4;
            }
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

        for (Drop drop : snowSelected) {
            if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                drop.x = 3*getWidth()/4;
            }
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

        if(timelToBeDisplayed <= 55) {
            for (Drop drop : stones) {
                if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                    drop.x = getWidth()/2;
                }
                drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
            }
        }

        for (Tap tap : taps) {
            tap.onDraw(canvas,(61-timelToBeDisplayed));
        }



        for (Drop drop : drops5) {
            if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                drop.x = getWidth()/4;
            }
            drop.onDraw(canvas,(61-timelToBeDisplayed),freezeFactor);
        }

        if(bucketSize >= 3) {
            for (Drop drop : crystal) {
                if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                    drop.x = getWidth()/2;
                }
                drop.onDraw(canvas, (61 - timelToBeDisplayed), freezeFactor);
            }
        }

        if(bucketSize >= 2) {
            for (Drop drop : bigDrops) {
                if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                    drop.x = 3*getWidth()/4;
                }
                drop.onDraw(canvas, (61 - timelToBeDisplayed), freezeFactor);
            }
        }

        if(bucketSize >= 4) {
            for (Drop drop : snow) {
                if(drop.x  > getWidth()-(drop.bmp).getWidth()){
                    drop.x = getWidth()/4;
                }
                drop.onDraw(canvas, (61 - timelToBeDisplayed), freezeFactor);
            }
        }



//        if(timelToBeDisplayed == s1) {
//            if(snowcount == 0){
//                createSnow();
//                snowcount = 1;
//            }
//        }
//
//        if(timelToBeDisplayed == s2) {
//            if(snowcount == 1){
//                createSnow();
//                snowcount = 2;
//            }
//        }

        canvas.drawBitmap(grass,0,getHeight() - 150,null);

        if(bucketSize <= 0){
            bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucketsmall);
        }

        if( bucketSize >=1 && bucketSize <= 4){
            bucket = BitmapFactory.decodeResource(getResources(), R.drawable.bucket);
        }
        if(bucketSize >= 5) {
            bucket = BitmapFactory.decodeResource(getResources(), R.drawable.superbucket);
        }
        canvas.drawBitmap(bucket,x - bucket.getWidth()/2,getHeight()-175,null);
        Paint bucketsupport = new Paint();
        bucketsupport.setARGB(150,0,178,255);
   //     Rect bucketsupportRect = new Rect((int) (x - bucket.getWidth()/2) + 10,getHeight() - 60,(int) (x + bucket.getWidth()/2) - 10,getHeight());
      //  canvas.drawRect(bucketsupportRect,bucketsupport);
  //      canvas.drawCircle(x,getHeight() + 40,bucket.getWidth()/2 + 28,bucketsupport);
        Rect bucketsupportRect = new Rect(0,getHeight() - 60,getWidth(),getHeight());

//        canvas.drawRect(bucketsupportRect,bucketsupport);
        canvas.drawBitmap(cloud,getWidth()/4-cloud.getWidth()/2,-100,null);
        canvas.drawBitmap(cloudflip,3*getWidth()/4-cloudflip.getWidth()/2 + 50,-100,null);
        textPaint.setARGB(255,0,178,255);
//        canvas.drawText(new StringBuilder().append("Score : ").append(scoretext).toString(), getWidth() / 2, 80, textPaint);
//        bucketsupport.setARGB(255,0,0,0);
//        canvas.drawCircle(getWidth()-80,60,50,bucketsupport);
        textPaint.setTextSize(60);
        textPaint.setARGB(255,0,0,0);
        if(score < 0){
            canvas.drawText("000",getWidth() - 120, 60, textPaint);
        }
        if(score >= 0 && score <= 9){
            canvas.drawText(new StringBuilder().append("00").append(score).toString(), getWidth() - 120, 60, textPaint);
        } else if(score >= 10 && score <= 99){
            canvas.drawText(new StringBuilder().append("0").append(score).toString(), getWidth() - 120, 60, textPaint);
        } else if(score >= 100 && score <= 999){
            canvas.drawText(new StringBuilder().append(scoretext).toString(), getWidth() - 120, 60, textPaint);
        } else if(score >= 1000){
            canvas.drawText(new StringBuilder().append(scoretext).toString(), getWidth() - 150, 60, textPaint);
        }
   //     canvas.drawText(new StringBuilder().append(scoretext).toString(), getWidth() - 120, 60, textPaint);
        canvas.drawBitmap(drop,getWidth() - 180 - drop.getWidth()/2,5,null);
//
        canvas.drawBitmap(pause,getWidth() - pause.getWidth() - 10,120 - pause.getHeight()/2 - 10,null);
//        if(bucketSize <= 4) {
//            canvas.drawText("x1", getWidth()/2, 60, textPaint);
//        } else if (bucketSize >= 5){
//            canvas.drawText("x2", getWidth()/2, 60, textPaint);
//        }


        textPaint.setTextSize(50);
        textPaint.setARGB(255,0,0,0);
        if (bucketSize == 0){

            canvas.drawText("x1", getWidth() - 230 - drop.getWidth(), 50, textPaint);
        } else if(bucketSize >= 1 && bucketSize <= 4) {

            canvas.drawText("x2", getWidth() - 230 - drop.getWidth(), 50, textPaint);
        } else if (bucketSize >= 5){

            canvas.drawText("x3", getWidth() - 230 - drop.getWidth(), 50, textPaint);
        }

        highestscore = Splash.pref.getInt(HIGHESTSCORE, 0);
        String highestscoreText = String.valueOf(highestscore);
//        canvas.drawText(new StringBuilder().append("Best:").append(highestscoreText).toString(), getWidth() - 120, 110, textPaint);
        textPaint.setTextSize(40);
        textPaint.setARGB(255,0,0,0);
        canvas.drawText(new StringBuilder().append("Best:").append(highestscoreText).toString(), 10, 120, textPaint);

        textPaint.setTextSize(60);
    //    textPaint.setARGB(255,192,64,0);
        textPaint.setARGB(255,0,0,0);
        if(timelToBeDisplayed >= 10) {
            canvas.drawText(new StringBuilder().append("0:").append(timeLeftText).toString(), 10, 60, textPaint);
        } else {
            canvas.drawText(new StringBuilder().append("0:0").append(timeLeftText).toString(), 10, 60, textPaint);
        }

//        if(pauseState == 1){
//            canvas.drawText("Resume",getWidth()/2,getHeight()/2,textPaint);
//        }


        if(splash == 1) {
            if (splashcount <= 10) {
                canvas.drawBitmap(splash1, x + bucket.getWidth() / 2 + 5, getHeight() - 200, null);
                canvas.drawBitmap(splash2, x - bucket.getWidth() / 2 - 65, getHeight() - 200, null);
                canvas.drawBitmap(splash1, x + bucket.getWidth() / 2 + 45, getHeight() - 160, null);
                canvas.drawBitmap(splash2, x - bucket.getWidth() / 2 - 105, getHeight() - 160, null);
                Paint five = new Paint();
                five.setTextSize(75);
                five.setARGB(255,0,178,255);
                five.setTextAlign(Paint.Align.CENTER);
                five.setTypeface(font);
                canvas.drawText("-5",getWidth()/2,getHeight()/2 + 100,five);
                splashcount++;
            } else {
                splash = 0;
                splashcount = 0;
            }
        }

        if(bigNumber >= 7 ){
            bigScoreDisplay = 1;
        }

        if(bigScoreDisplay == 1) {
            if (bigScoreCount <= 40) {
                Paint five = new Paint();
                five.setTextSize(75);
                five.setARGB(255,0,178,255);
                five.setTextAlign(Paint.Align.CENTER);
                five.setTypeface(font);
                canvas.drawText(new StringBuilder().append("+ ").append(bigScore).toString(),getWidth()/2 - 100,getHeight() -250,five);
                bigScoreCount ++;
            } else {
                bigScoreDisplay = 0;
                bigNumber = 0;
                bigScoreCount = 0;
                bigScore = 0;
            }
        }

        if(snowNumber >= 8 ){
            snowScoreDisplay = 1;
        }

        if(snowScoreDisplay == 1) {
            if (snowScoreCount <= 40) {
                Paint five = new Paint();
                five.setTextSize(75);
                five.setARGB(255,0,178,255);
                five.setTextAlign(Paint.Align.CENTER);
                five.setTypeface(font);
                canvas.drawText(new StringBuilder().append("+ ").append(snowScore).toString(),getWidth()/2 + 100,getHeight() -250,five);
                snowScoreCount ++;
            } else {
                snowScoreDisplay = 0;
                snowNumber = 0;
                snowScoreCount = 0;
                snowScore = 0;
            }
        }

        if(displayfiveseconds == 1) {
            if (displayfivesecondscount <= 20) {
                Paint five = new Paint();
                five.setTextSize(75);
                five.setARGB(255,0,178,255);
                five.setTextAlign(Paint.Align.CENTER);
                five.setTypeface(font);
                canvas.drawText("+ 5 sec",getWidth()/2,getHeight()/2 -200,five);
                displayfivesecondscount ++;
            } else {
                displayfiveseconds = 0;
                displayfivesecondscount = 0;
            }
        }

        if(displayCrystalSelected == 1) {
            if (displayCrystalSelectedCount <= 20) {
                Paint five = new Paint();
                five.setTextSize(75);
                five.setARGB(255,0,178,255);
                five.setTextAlign(Paint.Align.CENTER);
                five.setTypeface(font);
                canvas.drawText("+ 25",getWidth()/2,getHeight()/2,five);
                displayCrystalSelectedCount ++;
            } else {
                displayCrystalSelectedCount = 0;
                displayCrystalSelected = 0;
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

//        if(bigDropCollected == 1) {
//            if (bigDropTime <= 100) {
//                bigDropTime += 1 ;
//            } else {
//                bigDropCollected = 0;
//                bigDropTime = 0;
//            }
//        }

        if(bigDropCollected == 1){
            createbigDropsSelected();
            bigDropCollected = 0;
        }


        if(snowCollected == 1){
            createsnowSelected();
            snowCollected = 0;
        }

//
//        if(snowCollected == 1) {
//            if (snowTime <= 100) {
//                snowTime += 1 ;
//            } else {
//                snowCollected = 0;
//                snowTime = 0;
//            }
//        }

        synchronized (holder) {

                for (int i = drops.size() - 1; i >= 0; i--) {
                    Drop drop = drops.get(i);
                    if (drop.isCollision(getHeight())) {
                        drops.remove(drop);
                        drops.add(createDrop(R.drawable.drop,0));
//                        if(bigDropCollected == 1){
//                            drops.add(createDrop(R.drawable.bigdrop));
//
//                        } else if(snowCollected == 1){
//                            drops.add(createDrop(R.drawable.snow));
////
//                        } else {
//                            drops.add(createDrop(R.drawable.drop));
//
//                        }
                    }
                }

            for (int i = drops.size() - 1; i >= 0; i--) {
                Drop drop = drops.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops.remove(drop);
                     drops.add(createDrop(R.drawable.drop,0));
                     score += 1;
                     sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                }
            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop drop = drops5.get(i);
                if (drop.isCollision(getHeight())) {
                    drops5.remove(drop);
                    createDrops5();
                }
            }

            for (int i = drops5.size() - 1; i >= 0; i--) {
                Drop drop = drops5.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    drops5.remove(drop);
                    createDrops5();
                    timeleft += 5;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    sounds.play(power, 0.2f, 0.2f, 0, 0, 1.5f);
                    displayfiveseconds = 1;
                    bonusCount ++;
                }

            }

            for (int i = bigDrops.size() - 1; i >= 0; i--) {
                Drop drop = bigDrops.get(i);
                if (drop.isCollision(getHeight() + 2000)) {
                    bigDrops.remove(drop);
                    createbigDrops();
                    //             drops5.add(createDrop(R.drawable.drop5));
                }
            }

            for (int i = bigDrops.size() - 1; i >= 0; i--) {
                Drop drop = bigDrops.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    bigDrops.remove(drop);
                    createbigDrops();
                    bigDropCollected = 1;
       //             score += 10;
                    thunderStart = 1;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    sounds.play(thunder, 0.2f, 0.2f, 0, 0, 1.5f);
                    bonusCount ++;
                }

            }

            for (int i = bigDropsSelected.size() - 1; i >= 0; i--) {
                Drop drop = bigDropsSelected.get(i);
                if (drop.isCollision(getHeight())) {
                    bigDropsSelected.remove(drop);
                    bigNumber ++;
                }
            }

            for (int i = bigDropsSelected.size() - 1; i >= 0; i--) {
                Drop drop = bigDropsSelected.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    bigDropsSelected.remove(drop);
                    score += 5;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    bigNumber ++;
                    bigScore += 5;
             //       bigScoreDisplay = 1;
                }
            }

            for (int i = snowSelected.size() - 1; i >= 0; i--) {
                Drop drop = snowSelected.get(i);
                if (drop.isCollision(getHeight())) {
                    snowSelected.remove(drop);
                    snowNumber ++;
                }
            }

            for (int i = snowSelected.size() - 1; i >= 0; i--) {
                Drop drop = snowSelected.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    snowSelected.remove(drop);
                    score += 10;
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    snowNumber ++;
                    snowScore += 10;
               //     snowScoreDisplay = 1;
                }
            }

            for (int i = crystal.size() - 1; i >= 0; i--) {
                Drop drop = crystal.get(i);
                if (drop.isCollision(getHeight() + 2000)) {
                    crystal.remove(drop);
                    createCrystal();
                }
            }

            for (int i = crystal.size() - 1; i >= 0; i--) {
                Drop drop = crystal.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    crystal.remove(drop);
                    createCrystal();
                    freezeFactor = 2;
                    score += 25;
                    displayCrystalSelected = 1;
                    sounds.play(freeze, 0.2f, 0.2f, 0, 0, 1.5f);
                    bonusCount ++;
                }

            }

            for (int i = snow.size() - 1; i >= 0; i--) {
                Drop drop = snow.get(i);
                if (drop.isCollision(getHeight())) {
                    snow.remove(drop);
                    createSnow();
                }
            }

            for (int i = snow.size() - 1; i >= 0; i--) {
                Drop drop = snow.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    snow.remove(drop);
                    createSnow();
    //                freezeFactor = 2;
                    snowCollected = 1;
                    thunderStart = 1;
                    sounds.play(freeze, 1.0f, 1.0f, 0, 0, 1.5f);
                    sounds.play(thunder, 0.2f, 0.2f, 0, 0, 1.5f);
                    bonusCount ++;
                }
            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop drop = stones.get(i);
                if (drop.isCollision(getHeight())) {
                    stones.remove(drop);
                    stones.add(createDrop(R.drawable.stone,0));
                }
            }

            for (int i = stones.size() - 1; i >= 0; i--) {
                Drop drop = stones.get(i);
                if (drop.isCollected(x - bucket.getWidth() / 2)) {
                    stones.remove(drop);
                    stones.add(createDrop(R.drawable.stone,0));
                    if(score == 0) {
                        sounds.play(emptydrop, 1.0f, 1.0f, 0, 0, 1.5f);
                    }
                    if(score > 0) {
                        sounds.play(watersplash, 0.05f, 0.05f, 0, 0, 1.5f);
                        splash = 1;
                    }
                    score -= 5;
                    if(score < 0){
                        score = 0;
                    }

     //               startSplash(System.currentTimeMillis());
                }
            }

            for (int i = taps.size() - 1; i >= 0; i--) {
                Tap tap = taps.get(i);
                if (tap.isCollision(getHeight())) {
                    taps.remove(tap);
                    taps.add(createTap(R.drawable.drop,xtap,-6000));
                }
            }

            for (int i = taps.size() - 1; i >= 0; i--) {
                Tap tap = taps.get(i);
                if (tap.isCollected(x - bucket.getWidth() / 2)) {
                    taps.remove(tap);
                    taps.add(createTap(R.drawable.drop,xtap,-6000));
                    sounds.play(waterdrip, 0.2f, 0.2f, 0, 0, 1.5f);
                    score += 1;
                }
            }

            }


             if(timelToBeDisplayed  <= 0) {
//                 scoretext = String.valueOf(score);
//                 textPaint.setTextSize(70);
//                 canvas.drawText(new StringBuilder().append(scoretext).toString(), getWidth() - 80, 60, textPaint);
    //             int highestscore = Splash.pref.getInt(HIGHESTSCORE, 0);
  //               bucketSize = Splash.pref.getInt(BUCKET, 0);
//                 if(bucketSize >= 5){
//                     score = score*2;
//                 }
//                 if(highestscore < score){
//                     SharedPreferences.Editor editor = Splash.pref.edit();
//                     editor.putInt(HIGHESTSCORE, score);
//                     editor.commit();
//                 }


//                 if(score >= 100){
//                     bucketSize = Splash.pref.getInt(BUCKET, 0);
//                     if(bucketSize == 0) {
//                         SharedPreferences.Editor editor1 = Splash.pref.edit();
//                         editor1.putInt(BUCKET, 1);
//                         editor1.commit();
//                     }
//                 }
//
//                 if(score >= 140){
//                     bucketSize = Splash.pref.getInt(BUCKET, 0);
//                     if(bucketSize == 1) {
//                         SharedPreferences.Editor editor = Splash.pref.edit();
//                         editor.putInt(BUCKET, 2);
//                         editor.commit();
//                     }
//                 }
//
//                 if(score >= 180) {
//                     bucketSize = Splash.pref.getInt(BUCKET, 0);
//                     if (bucketSize == 2) {
//                         SharedPreferences.Editor editor = Splash.pref.edit();
//                         editor.putInt(BUCKET, 3);
//                         editor.commit();
//                     }
//                 }
//
//                 if(score >= 200) {
//                     bucketSize = Splash.pref.getInt(BUCKET, 0);
//                     if (bucketSize == 3) {
//                         SharedPreferences.Editor editor = Splash.pref.edit();
//                         editor.putInt(BUCKET, 4);
//                         editor.commit();
//                     }
//                 }
//
//                 if(score >= 250) {
//                     bucketSize = Splash.pref.getInt(BUCKET, 0);
//                     if (bucketSize == 4) {
//                         SharedPreferences.Editor editor = Splash.pref.edit();
//                         editor.putInt(BUCKET, 5);
//                         editor.commit();
//                     }
//                 }
//
//
//                 if(score >= 400) {
//                     bucketSize = Splash.pref.getInt(BUCKET, 0);
//                     if (bucketSize == 5) {
//                         SharedPreferences.Editor editor = Splash.pref.edit();
//                         editor.putInt(BUCKET, 6);
//                         editor.commit();
//                     }
//                 }

                 SharedPreferences.Editor editor = Splash.pref.edit();
                 editor.putInt(GAMEVIEWSTATE,1);
                 editor.commit();

//                 while (true) {
//                     try {
//                         thread.join();
//                     } catch (InterruptedException e) {
//                         e.printStackTrace();
//                     }
//                     break;
//                 }
//                 thread = null;

                 gameoverActivity();
                 timeleft = 60;
                 score = 0;
                 freezeFactor = 1;
                 tapcount = 0;
                 fivecount = 0;
                 bigdropcount = 0;
                 snowcount = 0;
                 crystalCount = 0;
                 snowCollected = 0;
                 bigDropCollected = 0;
                 bonusCount = 0;
                 displayCrystalSelected = 0;
                 displayCrystalSelectedCount = 0;

                 for (int i = drops.size() - 1; i >= 0; i--) {
                     Drop drop = drops.get(i);
                     drops.remove(drop);
                     drops.add(createDrop(R.drawable.drop,0));
                 }

                 for (int i = stones.size() - 1; i >= 0; i--) {
                     Drop drop = stones.get(i);
                     stones.remove(drop);
                     stones.add(createDrop(R.drawable.stone,0));
                 }

                 for (int i = snow.size() - 1; i >= 0; i--) {
                     Drop drop = snow.get(i);
                         snow.remove(drop);
                         createSnow();
                 }

                 for (int i = taps.size() - 1; i >= 0; i--) {
                     Tap tap = taps.get(i);
                         taps.remove(tap);
                     }
                 createTaps();

                 for (int i = crystal.size() - 1; i >= 0; i--) {
                     Drop drop = crystal.get(i);
                         crystal.remove(drop);
                         createCrystal();

                 }

                 for (int i = snowSelected.size() - 1; i >= 0; i--) {
                     Drop drop = snowSelected.get(i);
                         snowSelected.remove(drop);
                     }

                 for (int i = bigDropsSelected.size() - 1; i >= 0; i--) {
                     Drop drop = bigDropsSelected.get(i);
                         bigDropsSelected.remove(drop);
                     }


                 for (int i = bigDrops.size() - 1; i >= 0; i--) {
                     Drop drop = bigDrops.get(i);
                         bigDrops.remove(drop);
                         createbigDrops();
                 }

                 for (int i = drops5.size() - 1; i >= 0; i--) {
                     Drop drop = drops5.get(i);
                         drops5.remove(drop);
                         createDrops5();
                 }

             }

        }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float check = event.getY();
        if(check >= getHeight()/2) {
            x = event.getX();
        }

        pauseX = event.getX();
        pauseY = event.getY();

        if (pauseX >= getWidth() - pause.getWidth() - 10 && pauseX <= getWidth() && pauseY >= 120 - pause.getHeight()/2 - 10 && pauseY <= 120 + pause.getHeight()/2 - 10) {
            //               setPausedView();
            //       gameLoopThread.setPaused(true);
            pauseActivity();
        }

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

//    int gameState = Splash.pref.getInt(GAMEVIEWSTATE, 0);
//    if(gameState == 0) {
//        pauseActivity();
//    }
    }

    public void resume() {
            running = true;
            thread = new Thread(this);
            thread.start();
    }

    public void pauseActivity(){
        Context context = getContext();
        Intent intent = new Intent("com.healthtapper.sixtyseconds.PAUSEACTIVITY");
        context.startActivity(intent);
    }

    public void gameoverActivity(){
        Context context = getContext();
        Intent intent = new Intent("com.healthtapper.sixtyseconds.GAMEOVER");
        Bundle bundle = new Bundle();
//Add your data from getFactualResults method to bundle
        bundle.putInt("SCORE", score);
        bundle.putInt("BONUS", bonusCount);
//Add the bundle to the intent
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public void run() {
        clockStart = System.currentTimeMillis();
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
//        xtap = rnd.nextInt(200) + 50;

//        t1 = rnd.nextInt(10) + 30;
//        t2 = rnd.nextInt(10) + 20;
//        t2 = rnd.nextInt(10) + 10;
//        e1 = rnd.nextInt(20) + 30;
//        e2 = rnd.nextInt(20) + 10;
//        s1 = rnd.nextInt(10) + 30;
//        s2 = rnd.nextInt(10) + 10;
//        b1 = rnd.nextInt(10) + 40;
//        b2 = rnd.nextInt(10) + 20;
//        c1 = rnd.nextInt(10) + 40;
//        c2 = rnd.nextInt(10) + 20;
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