package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class Unlock extends Activity {

    ImageView bucket;
    TextView bucketText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);
        bucketText = (TextView) findViewById(R.id.bucketText);
        bucket = (ImageView) findViewById(R.id.bucket);
        bucket.setBackgroundResource(R.drawable.bucket);
        bucketText.setText("Score 100 to Unlock");
    }
}
