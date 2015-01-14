package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends Activity{

    Button restart;
    int score;
    TextView scoreText,achievement;
    public static final String ACHIEVEMENT = "achievementNumber";
    int achievementStatus;
    int bucketSize;
    public static final String BUCKET = "bucket";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        restart = (Button) findViewById(R.id.playagain);
        scoreText = (TextView) findViewById(R.id.scoreText);
        achievement = (TextView) findViewById(R.id.achievement);
        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        bucketSize = Splash.pref.getInt(BUCKET, 0);
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        score = bundle.getInt("SCORE");
//        if(achievementStatus >= 5){
//            score = score*2;
//        }
        scoreText.setText(new StringBuilder().append("Score : ").append(score).toString());

        if(score >= 100){
            if(achievementStatus == 0){
                achievement.setText("Bucket Unlocked");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 1);
                editor.commit();
            }
        }

        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        if(score >= 140){
            if(achievementStatus == 1){
                achievement.setText("Big Drop Unlocked");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 2);
                editor.commit();
            }
        }

        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        if(score >= 180){
            if(achievementStatus == 2){
                achievement.setText("Crystal Unlocked");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 3);
                editor.commit();
            }
        }
        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        if(score >= 200){
            if(achievementStatus == 3){
                achievement.setText("Snow Ball Unlocked");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 4);
                editor.commit();
            }
        }

        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        if(score >= 250){
            if(achievementStatus == 4){
                achievement.setText("x2 Multiplier");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 5);
                editor.commit();
            }
        }

        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        if(score >= 400){
            if(achievementStatus == 5){
                achievement.setText("Congratulations,Endless Unlocked");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 6);
                editor.commit();
            }
        }

        restart.setBackgroundResource(R.drawable.cloud);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });
    }
}
