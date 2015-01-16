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

import com.flurry.android.FlurryAgent;

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
        FlurryAgent.init(Start.this, "Q3ZZS4K4S7RTF9BXZX9M");
      }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    protected void onPause() {
        super.onPause();
        gameView.pause();
        finish();
    }
}

