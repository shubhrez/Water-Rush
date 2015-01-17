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
    int score,bonus;
    TextView scoreText,achievement,scorevalue,bonusvalue,finalscore;
    public static final String ACHIEVEMENT = "achievementNumber";
    int achievementStatus;
    int bucketSize;
    public static final String BUCKET = "bucket";
    public static final String HIGHESTSCORE = "highestscore";
    public static final String GAMEVIEWSTATE = "gameviewstate";
    int multiplier,finalScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        restart = (Button) findViewById(R.id.playagain);
        scoreText = (TextView) findViewById(R.id.scoreText);
        finalscore = (TextView) findViewById(R.id.finalscore);
        bonusvalue = (TextView) findViewById(R.id.bonus);
        scorevalue = (TextView) findViewById(R.id.scoreValue);
        achievement = (TextView) findViewById(R.id.achievement);
        achievementStatus = Splash.pref.getInt(ACHIEVEMENT,0);
        bucketSize = Splash.pref.getInt(BUCKET, 0);
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        score = bundle.getInt("SCORE");
        bonus = bundle.getInt("BONUS");
//        if(achievementStatus >= 5){
//            score = score*2;
//        }


        int highestscore = Splash.pref.getInt(HIGHESTSCORE, 0);
        if(bucketSize <= 4){
            multiplier = 1;
        } else if (bucketSize >= 5){
            multiplier = 2;
        }

        finalScore = multiplier*score + bonus*5;

      //  scoreText.setText(new StringBuilder().append(score).toString());
//        multiplierText.setText(new StringBuilder().append("x").append(multiplier).toString());
//        finalScoreText.setText(new StringBuilder().append(finalScore).toString());


        scoreText.setText("Water Collected");
        scorevalue.setText(new StringBuilder().append(score).append(" x").append(multiplier).toString());
        bonusvalue.setText(new StringBuilder().append(bonus*5).toString());
        finalscore.setText(new StringBuilder().append(finalScore).toString());

        if(finalScore >= 100){
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
        if(finalScore >= 140){
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
        if(finalScore >= 180){
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
        if(finalScore >= 200){
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
        if(finalScore >= 250){
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
        if(finalScore >= 400){
            if(achievementStatus == 5){
                achievement.setText("Congratulations,Endless Unlocked");
                achievement.setTextSize(35);
                achievement.setTextColor(Color.RED);
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(ACHIEVEMENT, 6);
                editor.commit();
            }
        }

        if(finalScore >= 100){
            bucketSize = Splash.pref.getInt(BUCKET, 0);
            if(bucketSize == 0) {
                SharedPreferences.Editor editor1 = Splash.pref.edit();
                editor1.putInt(BUCKET, 1);
                editor1.commit();
            }
        }

        if(finalScore >= 140){
            bucketSize = Splash.pref.getInt(BUCKET, 0);
            if(bucketSize == 1) {
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(BUCKET, 2);
                editor.commit();
            }
        }

        if(finalScore >= 180) {
            bucketSize = Splash.pref.getInt(BUCKET, 0);
            if (bucketSize == 2) {
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(BUCKET, 3);
                editor.commit();
            }
        }

        if(finalScore >= 200) {
            bucketSize = Splash.pref.getInt(BUCKET, 0);
            if (bucketSize == 3) {
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(BUCKET, 4);
                editor.commit();
            }
        }

        if(finalScore >= 250) {
            bucketSize = Splash.pref.getInt(BUCKET, 0);
            if (bucketSize == 4) {
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(BUCKET, 5);
                editor.commit();
            }
        }


        if(finalScore >= 400) {
            bucketSize = Splash.pref.getInt(BUCKET, 0);
            if (bucketSize == 5) {
                SharedPreferences.Editor editor = Splash.pref.edit();
                editor.putInt(BUCKET, 6);
                editor.commit();
            }
        }

        if(highestscore < finalScore){
                     SharedPreferences.Editor editor = Splash.pref.edit();
                     editor.putInt(HIGHESTSCORE, finalScore);
                     editor.commit();
                 }


        SharedPreferences.Editor editor = Splash.pref.edit();
        editor.putInt(GAMEVIEWSTATE,0);
        editor.commit();




        restart.setBackgroundResource(R.drawable.cloud);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
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
