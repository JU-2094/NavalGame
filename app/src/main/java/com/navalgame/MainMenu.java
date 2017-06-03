package com.navalgame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {

    Button play,config,exit;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        play = (Button)findViewById(R.id.btn_play);
        config = (Button)findViewById(R.id.btn_config);
        exit = (Button)findViewById(R.id.btn_exit);

        mp = MediaPlayer.create(this,R.raw.snapper);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.start();
                /*
                Intent play = new Intent(MainMenu.this,Game.class);
                startActivity(play);*/
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                mp.start();
                System.exit(0);
                onDestroy();

            }
        });
    }
}
