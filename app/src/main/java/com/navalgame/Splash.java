package com.navalgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.content_splash);




        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent menu = new Intent(Splash.this,MainMenu.class);
                startActivity(menu);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);
    }
}