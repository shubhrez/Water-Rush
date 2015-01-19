package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Instructions extends Activity{

    Button start,quest;
    TextView title,guide1,guide2,guide3,guide4,guide5,point1,point2,point3;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        font = Typeface.createFromAsset(getAssets(), "Toxia_FRE.ttf");
        start = (Button) findViewById(R.id.start);
        quest = (Button) findViewById(R.id.quest);

        start.setTypeface(font);
        quest.setTypeface(font);

        title = (TextView) findViewById(R.id.title);
        guide1 = (TextView) findViewById(R.id.guide1);
        guide2 = (TextView) findViewById(R.id.guide2);
        guide3 = (TextView) findViewById(R.id.guide3);
        guide4 = (TextView) findViewById(R.id.guide4);
        guide5 = (TextView) findViewById(R.id.guide5);
        point1 = (TextView) findViewById(R.id.point1);
        point2 = (TextView) findViewById(R.id.point2);
        point3 = (TextView) findViewById(R.id.point3);

        title.setTypeface(font);
        guide1.setTypeface(font);
        guide2.setTypeface(font);
        guide3.setTypeface(font);
        guide4.setTypeface(font);
        guide5.setTypeface(font);
        point1.setTypeface(font);
        point2.setTypeface(font);
        point3.setTypeface(font);

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
