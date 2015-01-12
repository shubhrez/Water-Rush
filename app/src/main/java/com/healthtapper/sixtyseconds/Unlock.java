package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class Unlock extends Activity {

    ImageView bucket;
    TextView bucketText;
    public static final String BUCKET = "bucket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);
        int bucketSize = Splash.pref.getInt(BUCKET, 0);
        bucketText = (TextView) findViewById(R.id.bucketText);
        bucket = (ImageView) findViewById(R.id.bucket);
        bucket.setBackgroundResource(R.drawable.bucket);
        if (bucketSize == 0) {
            bucketText.setText("Score 100 to Unlock");
            bucketText.setTextColor(Color.RED);
        } else if (bucketSize == 1) {
            bucketText.setText("Unlocked");
            bucketText.setTextColor(Color.GREEN);
        }
    }
}
