package com.healthtapper.sixtyseconds;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Start extends Activity {

    StartGameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        ActionBar bar = getActionBar();
//        bar.hide();
        gameView = new StartGameView(this);
        setContentView(gameView);

//        Thread timer = new Thread() {
//            public void run() {
//                try {
//                    sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    Intent mainActivity = new Intent("com.healthtapper.sixtyseconds.SPLASH");
//                    startActivity(mainActivity);
//                }
//            }
//        };
//        timer.start();
      }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    protected void onPause() {
        super.onPause();
        gameView.pause();
//        try {
//            gameView.thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    //    gameView.thread = null;
        finish();
    }
}

