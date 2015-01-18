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
    TextView bucketText,bigdropText,snowText,crystalText,multiplierText,endlessText,title,multiplier;

    public static final String BUCKET = "bucket";
    Button start,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);

        int bucketSize = Splash.pref.getInt(BUCKET, 0);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "WindyRainDemo.ttf");

        start = (Button) findViewById(R.id.start);
        back = (Button) findViewById(R.id.back);

        bucketText = (TextView) findViewById(R.id.bucketText);
        bigdropText = (TextView) findViewById(R.id.bigdropText);
        snowText = (TextView) findViewById(R.id.snowText);
        crystalText = (TextView) findViewById(R.id.crystalText);
        multiplierText = (TextView) findViewById(R.id.multiplierText);
        endlessText = (TextView) findViewById(R.id.endlessText);
        title = (TextView) findViewById(R.id.title);

        title.setTypeface(custom_font);
//        bucketText.setTypeface(custom_font);
//        bigdropText.setTypeface(custom_font);
//        snowText.setTypeface(custom_font);
//        crystalText.setTypeface(custom_font);
//        multiplierText.setTypeface(custom_font);
//        endlessText.setTypeface(custom_font);

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
            bucketText.setText("Score 100 or more to Unlock");
        //    bucketText.setTextColor(Color.RED);
        } else if (bucketSize >= 1) {
            bucketText.setText("Unlocked");
         //   bucketText.setTextColor(Color.GREEN);
        }

        bigdrop.setBackgroundResource(R.drawable.bigdrop);
        if (bucketSize <= 1) {
            bigdropText.setText("Score 140 or more to Unlock");
   //         bigdropText.setTextColor(Color.RED);
        } else if (bucketSize >= 2) {
            bigdropText.setText("Unlocked");
     //       bigdropText.setTextColor(Color.GREEN);
        }

        crystal.setBackgroundResource(R.drawable.cyrstal);
        if (bucketSize <= 2) {
            crystalText.setText("Score 180 or more to Unlock");
      //      crystalText.setTextColor(Color.RED);
        } else if (bucketSize >= 3) {
            crystalText.setText("Unlocked");
       //     crystalText.setTextColor(Color.GREEN);
        }


        snow.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 3) {
            snowText.setText("Score 200 or more to Unlock");
       //     snowText.setTextColor(Color.RED);
        } else if (bucketSize >= 4) {
            snowText.setText("Unlocked");
     //       snowText.setTextColor(Color.GREEN);
        }

//        multiplier.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 4) {
            multiplierText.setText("Score 250 or more to Unlock");
      //      multiplierText.setTextColor(Color.RED);
        } else if (bucketSize >= 5) {
            multiplierText.setText("Unlocked");
     //       multiplierText.setTextColor(Color.GREEN);
        }

        endless.setBackgroundResource(R.drawable.endless);
        if (bucketSize <= 5) {
            endlessText.setText("Score 400 or more to Unlock");
    //        endlessText.setTextColor(Color.RED);
        } else if (bucketSize >= 6) {
            endlessText.setText("Unlocked");
     //       endlessText.setTextColor(Color.GREEN);
        }

        start.setBackgroundResource(R.drawable.cloud);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });

        back.setBackgroundResource(R.drawable.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.SPLASH");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
  //      finish();
    }
}
