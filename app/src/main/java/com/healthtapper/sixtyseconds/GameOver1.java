package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver1 extends Activity {

    Button restart;
    int score,bonus,totalScore;
    public static final String ENDLESSGAMEVIEWSTATE = "endlessgameviewstate";
    TextView finalScore,scoreText,scoreValue,bonusText,bonusValue,scoreDesc;
    public static final String ENDLESSHIGHESTSCORE = "endlesshighestscore";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover1);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Toxia_FRE.ttf");

        restart = (Button) findViewById(R.id.playagain);
    //    scoreText = (TextView) findViewById(R.id.score);
        finalScore = (TextView) findViewById(R.id.finalScore);
        scoreText = (TextView) findViewById(R.id.scoreText);
        scoreValue = (TextView) findViewById(R.id.scoreValue);
        bonusText = (TextView) findViewById(R.id.bonusText);
        bonusValue = (TextView) findViewById(R.id.bonusValue);
        scoreDesc = (TextView) findViewById(R.id.scoreDesc);
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
        bonus = bundle.getInt("BONUS");
    //    scoreText.setText(new StringBuilder().append("Best:").append(score).toString());
        totalScore = score + bonus*5;

        scoreText.setText(new StringBuilder().append("Water Collected").toString());
        bonusText.setText(new StringBuilder().append("Bonus").toString());
        scoreValue.setText(new StringBuilder().append(score).toString());
        bonusValue.setText(new StringBuilder().append(bonus*5).toString());
        finalScore.setText(new StringBuilder().append(" : ").append(totalScore).toString());

        scoreText.setTypeface(custom_font);
        bonusText.setTypeface(custom_font);
        scoreDesc.setTypeface(custom_font);
        restart.setTypeface(custom_font);

        int endlesshighestscore = Splash.pref.getInt(ENDLESSHIGHESTSCORE, 0);
        if(totalScore > endlesshighestscore){
            scoreDesc.setText("New High");
        } else {
            scoreDesc.setText("Score");
        }

        if(totalScore > endlesshighestscore){
            SharedPreferences.Editor editor = Splash.pref.edit();
            editor.putInt(ENDLESSHIGHESTSCORE, totalScore);
            editor.commit();
        }

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
