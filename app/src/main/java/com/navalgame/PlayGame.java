package com.navalgame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlayGame extends Activity {

    ImageView boatL,boatM,boatS1,boatS2,boatU1,boatU2,boatU3;
    View gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        gridView = findViewById(R.id.grid_view);
        /*boatL = (ImageView)findViewById(R.id.larger_boat);
        boatM = (ImageView)findViewById(R.id.medium_boat);
        boatS1 = (ImageView)findViewById(R.id.small_boat1);
        boatS2 = (ImageView)findViewById(R.id.small_boat2);
        boatU1 = (ImageView)findViewById(R.id.unit_boat1);
        boatU2 = (ImageView)findViewById(R.id.unit_boat2);
        boatU3 = (ImageView)findViewById(R.id.unit_boat3);

        boatM.setVisibility(View.INVISIBLE);
        boatS1.setVisibility(View.INVISIBLE);
        boatS2.setVisibility(View.INVISIBLE);
        boatU1.setVisibility(View.INVISIBLE);
        boatU2.setVisibility(View.INVISIBLE);
        boatU3.setVisibility(View.INVISIBLE);*/
    }
}
