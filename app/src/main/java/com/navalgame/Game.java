package com.navalgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Game extends AppCompatActivity {

    View gridView;
    Button btnBegin;
    ImageView boatL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);


        gridView = findViewById(R.id.grid_view);
        btnBegin = (Button) findViewById(R.id.btn_begin);

        boatL = (ImageView)findViewById(R.id.larger_boat);

        boatL.setTag("boatL");
        boatL.setOnTouchListener(new ListenerOnTouch());

    }



    private class ListenerOnTouch implements View.OnTouchListener{
        float dx,dy;
        boolean isclick;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isclick=true;
                    dx = v.getX() - event.getRawX();
                    dy = v.getY() - event.getRawY();

                    Log.e("POSITION X,Y, dx, dy",v.getX()+","+v.getY()+","+dx+","+dy);
                    Log.e("Rotation",v.getRotation()+"");
                    break;
                case MotionEvent.ACTION_UP:
                    if(isclick){
                        v.setRotation(v.getRotation()+90);
                    }
                case MotionEvent.ACTION_MOVE:
                    isclick=false;

                    if( processTag(v.getTag().toString(), v.getX(), v.getY()) ){

                        v.animate()
                                .x(event.getRawX() + dx)
                                .y(event.getRawY() + dy)
                                .setDuration(0)
                                .start();
                    }else{
                        restoreValues(v);
                    }

                    break;
                default:
                    return false;
            }
            return true;
        }

        private boolean processTag(String tag,float moveX,float moveY){

            int boundX = gridView.getWidth() - gridView.getWidth()/11;
            int boundY = gridView.getHeight();
            switch (tag){
                case "boatL":
                    Log.e("MOVEX",moveX+"__");
                    if(moveX<0 || moveX>boundX)
                        return false;
                    else
                        return true;
                default:

            }
            return true;
        }

        private void restoreValues(View v){

        }
    }
}
