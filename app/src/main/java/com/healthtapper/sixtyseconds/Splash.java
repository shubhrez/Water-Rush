package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Splash extends Activity {

    Button start,instructions;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        start = (Button) findViewById(R.id.start);
        instructions = (Button) findViewById(R.id.instructions);
        start.setBackgroundResource(R.drawable.cloud);
        instructions.setBackgroundResource(R.drawable.cloud);
    //    start.setText(R.string.hello_world);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
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
