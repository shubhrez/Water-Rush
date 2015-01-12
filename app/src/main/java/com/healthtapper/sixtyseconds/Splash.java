package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Splash extends Activity {

    Button start,instructions,freeflow;
    TextView title;
    public static SharedPreferences pref;
    static String PREF_NAME = "SixtySeconds";
    public static final String HIGHESTSCORE = "highestscore";
    private static Integer highestScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        start = (Button) findViewById(R.id.start);
        freeflow = (Button) findViewById(R.id.freeflow);
        instructions = (Button) findViewById(R.id.instructions);
        start.setBackgroundResource(R.drawable.cloud);
        pref = getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
        instructions.setBackgroundResource(R.drawable.cloud);
    //    start.setText(R.string.hello_world);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });

        freeflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.FREEFLOW");
                startActivity(intent);
            }
        });

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.INSTRUCTIONS");
                startActivity(intent);
            }
        });
    }
}
