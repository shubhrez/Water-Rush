package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PauseActivityEndless extends Activity {

    Button resume;
    TextView gameover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pauseendless);
        Typeface font = Typeface.createFromAsset(getAssets(), "Toxia_FRE.ttf");
        resume = (Button) findViewById(R.id.resume);
        gameover = (TextView) findViewById(R.id.gameover);
        resume.setTypeface(font);
        gameover.setTypeface(font);
        resume.setBackgroundResource(R.drawable.cloud);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.FREEFLOW");
                startActivity(intent);
            }
        });
    }
}
