package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instructions extends Activity{

    Button start,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        start = (Button) findViewById(R.id.start);
        back = (Button) findViewById(R.id.back);
        start.setBackgroundResource(R.drawable.cloud);
        back.setBackgroundResource(R.drawable.back);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });

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
        finish();
    }
}
