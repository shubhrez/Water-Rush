package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver1 extends Activity {

    Button restart;
    int score;
    public static final String ENDLESSGAMEVIEWSTATE = "endlessgameviewstate";
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover1);
        restart = (Button) findViewById(R.id.playagain);
        scoreText = (TextView) findViewById(R.id.score);
        restart.setBackgroundResource(R.drawable.cloud);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent("com.healthtapper.sixtyseconds.FREEFLOW");
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        score = bundle.getInt("SCORE");
        scoreText.setText(new StringBuilder().append("Best:").append(score).toString());

        SharedPreferences.Editor editor = Splash.pref.edit();
        editor.putInt(ENDLESSGAMEVIEWSTATE,0);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
