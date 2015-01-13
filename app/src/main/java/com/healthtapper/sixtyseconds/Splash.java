package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Splash extends Activity {

    Button start,instructions,freeflow,unlock;
    TextView title;
    public static SharedPreferences pref;
    static String PREF_NAME = "SixtySeconds";
    public static final String HIGHESTSCORE = "highestscore";
    public static final String BUCKET = "bucket";
    private static Integer highestScore = 0;
    private static Integer bucketSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        start = (Button) findViewById(R.id.start);
        freeflow = (Button) findViewById(R.id.freeflow);
        unlock = (Button) findViewById(R.id.unlock);
        instructions = (Button) findViewById(R.id.instructions);
        start.setBackgroundResource(R.drawable.cloud);
        unlock.setBackgroundResource(R.drawable.cloud);
        freeflow.setBackgroundResource(R.drawable.cloud);
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
                if(bucketSize >= 6) {
                    startActivity(intent);
                } else {
                    Toast.makeText(Splash.this,"Unlock to play",Toast.LENGTH_SHORT).show();
                }
            }
        });

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.UNLOCK");
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

    @Override
    protected void onResume() {
        super.onResume();
        bucketSize = pref.getInt(BUCKET, 0);
        freeflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.FREEFLOW");
                if(bucketSize >= 6) {
                    startActivity(intent);
                } else {
                    Toast.makeText(Splash.this,"Unlock to play",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
