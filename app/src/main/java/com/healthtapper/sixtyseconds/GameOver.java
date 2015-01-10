package com.healthtapper.sixtyseconds;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOver extends Activity{

    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        restart = (Button) findViewById(R.id.playagain);
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
