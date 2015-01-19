package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class FreeFlow extends Activity {

    FreePlayGameView gameview;
    int endlessgamestate;
    public static final String ENDLESSGAMEVIEWSTATE = "endlessgameviewstate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameview = new FreePlayGameView(this);
        setContentView(gameview);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        endlessgamestate = Splash.pref.getInt(ENDLESSGAMEVIEWSTATE, 0);
        gameview.pause();
        if (endlessgamestate == 1){
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resume();
    }
}
