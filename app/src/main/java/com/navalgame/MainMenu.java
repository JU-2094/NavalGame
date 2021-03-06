package com.navalgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        Animation escalar  = AnimationUtils.loadAnimation(MainMenu.this, R.anim.trasladar);
        escalar.reset();

        play.startAnimation(escalar);
        config.startAnimation(escalar);
        exit.startAnimation(escalar);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.start();

                Intent play = new Intent(MainMenu.this,Game.class);
                startActivity(play);
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);

                builder.setMessage("\nCANSECO SANCHEZ HECTOR MIGUEL" +
                        "GARCIA CAMACHO LUIS ANGEL\n" +
                        "HUERTA QUINTERO RAFAEL\n" +
                        "MOLINA ALBA VICTOR")
                        .setTitle("Integrantes");

                AlertDialog dialog = builder.create();
                dialog.show();
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
