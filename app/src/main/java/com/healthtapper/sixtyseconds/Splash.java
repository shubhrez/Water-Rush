package com.healthtapper.sixtyseconds;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;


public class Splash extends Activity {

    Button start,instructions,freeflow,unlock;
    TextView title,unlocktext;
    public static SharedPreferences pref;
    static String PREF_NAME = "SixtySeconds";
    public static final String HIGHESTSCORE = "highestscore";
    public static final String BUCKET = "bucket";
    private static Integer highestScore = 0;
    private static Integer bucketSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

   //     FlurryAgent.init(this,"Q3ZZS4K4S7RTF9BXZX9M");

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Toxia_FRE.ttf");

        start = (Button) findViewById(R.id.start);
        freeflow = (Button) findViewById(R.id.freeflow);
        unlock = (Button) findViewById(R.id.unlock);
        instructions = (Button) findViewById(R.id.instructions);

        start.setTypeface(custom_font);
        freeflow.setTypeface(custom_font);
        unlock.setTypeface(custom_font);
        instructions.setTypeface(custom_font);

        title = (TextView) findViewById(R.id.title);
        title.setTypeface(custom_font);
        start.setBackgroundResource(R.drawable.cloud);
        unlock.setBackgroundResource(R.drawable.cloud);
        freeflow.setBackgroundResource(R.drawable.cloud);
        pref = getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
        instructions.setBackgroundResource(R.drawable.cloud);
    //    start.setText(R.string.hello_world);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.MAINACTIVITY");
                startActivity(intent);
            }
        });

        freeflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.FREEFLOW");
                if(bucketSize >= 6) {
                    startActivity(intent);
                } else {
                    Toast.makeText(Splash.this,"Unlock to play",Toast.LENGTH_SHORT).show();
                }
            }
        });

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.UNLOCK");
                startActivity(intent);
            }
        });


        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.INSTRUCTIONS");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucketSize = pref.getInt(BUCKET, 0);
        freeflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.healthtapper.sixtyseconds.FREEFLOW");
                if(bucketSize >= 6) {
                    startActivity(intent);
                } else {
//                    Toast.makeText(Splash.this,"Unlock to play",Toast.LENGTH_SHORT).show();
                    LayoutInflater inflater = LayoutInflater.from(Splash.this);
                    View layout = inflater.inflate(R.layout.endlessdialog, null);

                    Typeface custom_font = Typeface.createFromAsset(getAssets(), "Toxia_FRE.ttf");

                    AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
       //             builder.setMessage("Unlock to play Endless");
                    builder.setCancelable(true);
                    builder.setView(layout);
                    unlocktext = (TextView) layout.findViewById(R.id.unlocktext);
              //      unlocktext.setTypeface(custom_font);
         //           TextView textView = (TextView) dialog.findViewById(android.R.id.message);

                    builder.setNegativeButton("Check Out Quest",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Intent intent = new Intent("com.healthtapper.sixtyseconds.UNLOCK");
                                    startActivity(intent);

                                }
                            });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
                    builder.show();
//                    TextView textView = (TextView) dialog.findViewById(android.R.id.message);
//                    textView.setTextSize(25);
//                    textView.setTextColor(getResources().getColor(R.color.blue));

                }
            }
        });

//        if(bucketSize >= 6){
//            freeflow.setText("Play Endless");
//            freeflow.setTextSize(25);
//        } else {
//            freeflow.setText("Unlock to Play Endless");
//            freeflow.setTextSize(20);
//        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FlurryAgent.init(Splash.this,"Q3ZZS4K4S7RTF9BXZX9M");
//        FlurryAgent.onStartSession(this);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        FlurryAgent.onEndSession(this);
//    }
}
