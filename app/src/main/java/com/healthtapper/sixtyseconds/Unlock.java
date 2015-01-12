package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class Unlock extends Activity {

    ImageView bucket,bigdrop,snow,crystal,multiplier,endless;
    TextView bucketText,bigdropText,snowText,crystalText,multiplierText,endlessText;
    public static final String BUCKET = "bucket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);
        int bucketSize = Splash.pref.getInt(BUCKET, 0);

        bucketText = (TextView) findViewById(R.id.bucketText);
        bigdropText = (TextView) findViewById(R.id.bigdropText);
        snowText = (TextView) findViewById(R.id.snowText);
        crystalText = (TextView) findViewById(R.id.crystalText);
        multiplierText = (TextView) findViewById(R.id.multiplierText);
        endlessText = (TextView) findViewById(R.id.endlessText);

        bucket = (ImageView) findViewById(R.id.bucket);
        bigdrop = (ImageView) findViewById(R.id.bigdrop);
        snow = (ImageView) findViewById(R.id.snow);
        crystal = (ImageView) findViewById(R.id.crystal);
        multiplier = (ImageView) findViewById(R.id.multiplier);
        endless = (ImageView) findViewById(R.id.endless);

        bucket.setBackgroundResource(R.drawable.bucket);
        if (bucketSize == 0) {
            bucketText.setText("Score 100 or more to Unlock");
            bucketText.setTextColor(Color.RED);
        } else if (bucketSize >= 1) {
            bucketText.setText("Unlocked");
            bucketText.setTextColor(Color.GREEN);
        }

        bigdrop.setBackgroundResource(R.drawable.bigdrop);
        if (bucketSize <= 1) {
            bigdropText.setText("Score 140 or more to Unlock");
            bigdropText.setTextColor(Color.RED);
        } else if (bucketSize >= 2) {
            bigdropText.setText("Unlocked");
            bigdropText.setTextColor(Color.GREEN);
        }

        crystal.setBackgroundResource(R.drawable.cyrstal);
        if (bucketSize <= 2) {
            crystalText.setText("Score 180 or more to Unlock");
            crystalText.setTextColor(Color.RED);
        } else if (bucketSize >= 3) {
            crystalText.setText("Unlocked");
            crystalText.setTextColor(Color.GREEN);
        }


        snow.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 3) {
            snowText.setText("Score 200 or more to Unlock");
            snowText.setTextColor(Color.RED);
        } else if (bucketSize >= 4) {
            snowText.setText("Unlocked");
            snowText.setTextColor(Color.GREEN);
        }

  //      multiplier.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 4) {
            multiplierText.setText("Score 250 or more to Unlock");
            multiplierText.setTextColor(Color.RED);
        } else if (bucketSize >= 5) {
            multiplierText.setText("Unlocked");
            multiplierText.setTextColor(Color.GREEN);
        }

    //          endless.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 5) {
            endlessText.setText("Score 400 or more to Unlock");
            endlessText.setTextColor(Color.RED);
        } else if (bucketSize >= 6) {
            endlessText.setText("Unlocked");
            endlessText.setTextColor(Color.GREEN);
        }

    }
}
