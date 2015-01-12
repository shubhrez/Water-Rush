package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class Unlock extends Activity {

    ImageView bucket,bigdrop,snow;
    TextView bucketText,bigdropText,snowText;
    public static final String BUCKET = "bucket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);
        int bucketSize = Splash.pref.getInt(BUCKET, 0);

        bucketText = (TextView) findViewById(R.id.bucketText);
        bigdropText = (TextView) findViewById(R.id.bigdropText);
        snowText = (TextView) findViewById(R.id.snowText);

        bucket = (ImageView) findViewById(R.id.bucket);
        bigdrop = (ImageView) findViewById(R.id.bigdrop);
        snow = (ImageView) findViewById(R.id.snow);

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

        snow.setBackgroundResource(R.drawable.snow);
        if (bucketSize <= 2) {
            snowText.setText("Score 180 or more to Unlock");
            snowText.setTextColor(Color.RED);
        } else if (bucketSize >= 3) {
            snowText.setText("Unlocked");
            snowText.setTextColor(Color.GREEN);
        }

    }
}
