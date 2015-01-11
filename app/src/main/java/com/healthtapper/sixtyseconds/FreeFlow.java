package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.os.Bundle;

public class FreeFlow extends Activity {

    FreePlayGameView gameview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameview = new FreePlayGameView(this);
        setContentView(gameview);

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameview.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resume();
    }
}
