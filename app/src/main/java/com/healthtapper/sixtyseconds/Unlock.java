package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Unlock extends Activity {

    ImageView bucket,bigdrop,snow,crystal,endless,goal1,goal2,superbucket;
    TextView bucketText,bigdropText,snowText,crystalText,magicbucketText,endlessText,title;
    TextView quest1,quest2,quest3,quest4,quest5,quest6;

    public static final String BUCKET = "bucket";
    Button start,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock1);

        int bucketSize = Splash.pref.getInt(BUCKET, 0);

        Typeface font = Typeface.createFromAsset(getAssets(), "Toxia_FRE.ttf");

        start = (Button) findViewById(R.id.start);
  //      back = (Button) findViewById(R.id.back);

        bucketText = (TextView) findViewById(R.id.bucketText);
        bigdropText = (TextView) findViewById(R.id.bigdropText);
        snowText = (TextView) findViewById(R.id.snowText);
        crystalText = (TextView) findViewById(R.id.crystalText);
        magicbucketText = (TextView) findViewById(R.id.magicbucketText);
        endlessText = (TextView) findViewById(R.id.endlessText);
        title = (TextView) findViewById(R.id.title);
        quest1 = (TextView) findViewById(R.id.quest1);
        quest2 = (TextView) findViewById(R.id.quest2);
        quest3 = (TextView) findViewById(R.id.quest3);
        quest4 = (TextView) findViewById(R.id.quest4);
        quest5 = (TextView) findViewById(R.id.quest5);
        quest6 = (TextView) findViewById(R.id.quest6);

        title.setTypeface(font);
        bucketText.setTypeface(font);
        bigdropText.setTypeface(font);
        snowText.setTypeface(font);
        crystalText.setTypeface(font);
        magicbucketText.setTypeface(font);
        endlessText.setTypeface(font);
        quest1.setTypeface(font);
        quest2.setTypeface(font);
        quest3.setTypeface(font);
        quest4.setTypeface(font);
        quest5.setTypeface(font);
        quest6.setTypeface(font);

        bucket = (ImageView) findViewById(R.id.bucket);
        bigdrop = (ImageView) findViewById(R.id.bigdrop);
        snow = (ImageView) findViewById(R.id.snow);
        crystal = (ImageView) findViewById(R.id.crystal);
    //    multiplier = (TextView) findViewById(R.id.multiplier);
        endless = (ImageView) findViewById(R.id.endless);
        goal1 = (ImageView) findViewById(R.id.goal1);
        goal2 = (ImageView) findViewById(R.id.goal2);
        superbucket = (ImageView) findViewById(R.id.superbucket);

        goal1.setBackgroundResource(R.drawable.goal);
        goal2.setBackgroundResource(R.drawable.goal);
        bucket.setBackgroundResource(R.drawable.bucket);
        superbucket.setBackgroundResource(R.drawable.superbucket);

        if (bucketSize == 0) {
            bucketText.setText("Unlocks at score above 250");
     //       bucketText.setTypeface(font);
        //    bucketText.setTextColor(Color.RED);
        } else if (bucketSize >= 1) {
            bucketText.setText("Unlocked");
      //      bucketText.setTypeface(font);
         //   bucketText.setTextColor(Color.GREEN);
        }

        bigdrop.setBackgroundResource(R.drawable.bigdrop);
        if (bucketSize <= 1) {
            bigdropText.setText("Unlocks at score above 500");
       //     bigdropText.setTypeface(font);
   //         bigdropText.setTextColor(Color.RED);
        } else if (bucketSize >= 2) {
            bigdropText.setText("Unlocked");
     //       bigdropText.setTypeface(font);
     //       bigdropText.setTextColor(Color.GREEN);
        }

        crystal.setBackgroundResource(R.drawable.cyrstal);
        if (bucketSize <= 2) {
            crystalText.setText("Unlocks at score above 600");
       //     crystalText.setTypeface(font);
      //      crystalText.setTextColor(Color.RED);
        } else if (bucketSize >= 3) {
            crystalText.setText("Unlocked");
     //       crystalText.setTypeface(font);
       //     crystalText.setTextColor(Color.GREEN);
        }


        snow.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 3) {
            snowText.setText("Unlocks at score above 650");
         //   snowText.setTypeface(font);
       //     snowText.setTextColor(Color.RED);
        } else if (bucketSize >= 4) {
            snowText.setText("Unlocked");
       //     snowText.setTypeface(font);
     //       snowText.setTextColor(Color.GREEN);
        }

//        multiplier.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 4) {
            magicbucketText.setText("Unlocks at score above 900");
        //    multiplierText.setTypeface(font);
      //      multiplierText.setTextColor(Color.RED);
        } else if (bucketSize >= 5) {
            magicbucketText.setText("Unlocked");
      //      multiplierText.setTypeface(font);
     //       multiplierText.setTextColor(Color.GREEN);
        }

        endless.setBackgroundResource(R.drawable.endless);
        if (bucketSize <= 5) {
            endlessText.setText("Unlocks at score above 1250");
   //         endlessText.setTypeface(font);
    //        endlessText.setTextColor(Color.RED);
        } else if (bucketSize >= 6) {
            endlessText.setText("Unlocked");
     //       endlessText.setTypeface(font);
     //       endlessText.setTextColor(Color.GREEN);
        }

        start.setBackgroundResource(R.drawable.cloud);
        start.setTypeface(font);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });

    //    back.setBackgroundResource(R.drawable.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("com.healthtapper.sixtyseconds.SPLASH");
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
