package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instructions extends Activity{

    Button start,quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions1);
        start = (Button) findViewById(R.id.start);
        quest = (Button) findViewById(R.id.quest);
        start.setBackgroundResource(R.drawable.cloud);
        quest.setBackgroundResource(R.drawable.cloud);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });

        quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.UNLOCK");
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
