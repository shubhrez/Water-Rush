package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends Activity {

    GameView gameview;
    int gameState;
    public static final String GAMEVIEWSTATE = "gameviewstate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameview = new GameView(this);
        setContentView(gameview);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameState = Splash.pref.getInt(GAMEVIEWSTATE, 0);
        gameview.pause();
//        if(gameState == 0) {
//            gameview.pause();
        if (gameState == 1){
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resume();
    }
}
