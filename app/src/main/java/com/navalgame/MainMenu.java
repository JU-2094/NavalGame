package com.navalgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {

    Button play,config,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        play = (Button)findViewById(R.id.play);
        config = (Button)findViewById(R.id.config);
        exit = (Button)findViewById(R.id.exit);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent play = new Intent(MainMenu.this,Game.class);
                startActivity(play);*/
            }
        });
    }
}
