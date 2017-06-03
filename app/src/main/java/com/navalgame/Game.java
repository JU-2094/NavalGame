package com.navalgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Game extends AppCompatActivity {

    View gridView;
    Button btnBegin;
    ImageView boatL,boatM,boatS1,boatS2,boatU1,boatU2,boatU3;

    final int CONST_GRID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);


        gridView = findViewById(R.id.grid_view);
        btnBegin = (Button) findViewById(R.id.btn_begin);

        boatL = (ImageView)findViewById(R.id.larger_boat);
        boatM = (ImageView)findViewById(R.id.medium_boat);
        boatS1 = (ImageView)findViewById(R.id.small_boat1);
        boatS2 = (ImageView)findViewById(R.id.small_boat2);
        boatU1 = (ImageView)findViewById(R.id.unit_boat1);
        boatU2 = (ImageView)findViewById(R.id.unit_boat2);
        boatU3 = (ImageView)findViewById(R.id.unit_boat3);

        boatL.setTag("boatL");
        boatL.setOnTouchListener(new ListenerOnTouch());

        boatM.setTag("boatM");
        boatM.setOnTouchListener(new ListenerOnTouch());

        boatS1.setTag("boatS");
        boatS1.setOnTouchListener(new ListenerOnTouch());

        boatS2.setTag("boatS");
        boatS2.setOnTouchListener(new ListenerOnTouch());

        boatU1.setTag("boatU");
        boatU1.setOnTouchListener(new ListenerOnTouch());

        boatU2.setTag("boatU");
        boatU2.setOnTouchListener(new ListenerOnTouch());

        boatU3.setTag("boatU");
        boatU3.setOnTouchListener(new ListenerOnTouch());


        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(gridView);
        YoYo.with(Techniques.Landing)
                .duration(2000)
                .playOn(btnBegin);

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
                        if(v.getRotation()>=360)
                            v.setRotation(90);
                        else
                            v.setRotation(v.getRotation()+90);
                    }
                    processView(v);
                    break;

                case MotionEvent.ACTION_MOVE:
                    isclick=false;


                    if( processView(v) && onRange(event.getRawX() + dx,event.getRawY() + dy)) {

                        v.animate()
                                .x(getposition(event.getRawX() + dx, 0))
                                .y(getposition(event.getRawY() + dy, 1))
                                .setDuration(0)
                                .start();

                    }else{
//                        v.setX(0);
//                        v.setY(0);
                    }

                    break;
                default:
                    return false;
            }
            processView(v);
            return true;
        }

        private float getposition(float val,int type){

            float res=0;

            float xUnit = gridView.getWidth()/CONST_GRID;
            float yUnit = gridView.getHeight()/CONST_GRID;

            switch (type){
                case 0:
                    int indexX = (int) ((val+xUnit*0.4)/xUnit);
                    res = indexX*xUnit;
                    break;

                case 1:
                    int indexY = (int) ((Math.abs(val) + yUnit*0.4)/yUnit);
                    if(val<0){
                        res = -indexY*yUnit - yUnit;
                    }else{
                        res = indexY*yUnit + yUnit;
                    }

                    break;
            }


            return res;
        }

        private boolean onRange(float x,float y){
            float boundYtop = (gridView.getHeight() - gridView.getHeight()/CONST_GRID)/2;
            float boundYbottom = -boundYtop;
            float boundX = gridView.getWidth() - gridView.getWidth()/CONST_GRID;

            return (x>0 && x<boundX) && (y<boundYtop && y>boundYbottom);

        }

        private void adjust(View v){
            float xUnit = gridView.getWidth()/CONST_GRID;
            float yUnit = gridView.getHeight()/CONST_GRID;

            int indexX = (int) ((v.getX()+xUnit*0.4)/xUnit);
            int indexY = (int) ((Math.abs(v.getY()) + yUnit*0.4)/yUnit);

            float movX = indexX*xUnit;
            float movY;

            if(v.getY()<0){
                movY = -indexY*yUnit - yUnit/2;
            }else{
                movY = indexY*yUnit + yUnit/2;
            }

            v.setX(movX);
            v.setY(movY);
        }

        private boolean processView(View v){
            boolean ret;
            int cond = 0;
            String tag = v.getTag().toString();
            float moveX = v.getX();
            float moveY = v.getY();
            float rot = v.getRotation();

            float xUnit = gridView.getWidth()/CONST_GRID;
            float yUnit = gridView.getHeight()/CONST_GRID;
            float xtraX = 0;
            float xtraY = 0;

            int boundX = gridView.getWidth() - gridView.getWidth()/CONST_GRID;
            int boundYtop = (gridView.getHeight() - gridView.getHeight()/CONST_GRID)/2;
            int boundYbottom = -boundYtop;

            switch (tag){
                case "boatL":

                    if(rot==90 || rot==270) {
                        xtraX = 2 * xUnit;
                        xtraX = (xtraX - (xtraX*0.2f));
                    }else{
                        xtraY = 2 * yUnit;
                        xtraY = (xtraY - (xtraY*0.2f));
                    }
                    break;
                case "boatM":
                    if(rot==90 || rot==270) {
                        xtraX = 1.5f * xUnit;
                        xtraX = (xtraX - (xtraX*0.2f));
                    }else{
                        xtraY = 1.5f * yUnit;
                        xtraY = (xtraY - (xtraY*0.2f));
                    }
                    break;
                case "boatS":
                    if(rot==90 || rot==270) {
                        xtraX = xUnit;
                        xtraX = (xtraX - (xtraX*0.2f));
                    }else{
                        xtraY = yUnit;
                        xtraY = (xtraY - (xtraY*0.2f));
                    }
                    break;
                case "boatU":
                    if(rot==90 || rot==270) {
                        xtraX = 0.5f * xUnit;
                        xtraX = (xtraX - (xtraX*0.2f));
                    }else{
                        xtraY = 0.5f * yUnit;
                        xtraY = (xtraY - (xtraY*0.2f));
                    }
                    break;
                default:

            }

            ret = !((moveX-xtraX)<0 || (moveX+xtraX)>boundX
                    || (moveY-xtraY)<boundYbottom || (moveY+xtraY)>boundYtop);

            if(!ret) {
                if((moveX-xtraX)<0)
                    cond = 0;
                if((moveX+xtraX)>boundX)
                    cond = 1;
                if((moveY-xtraY)<boundYbottom)
                    cond = 2;
                if((moveY+xtraY)>boundYtop)
                    cond = 3;
                restoreValues(v, xUnit, yUnit,cond);

            }
            adjust(v);
            return ret;
        }

        private void restoreValues(View v,float xUnit,float yUnit, int cond){

            xUnit = xUnit*0.1f;
            yUnit = yUnit*0.65f;

            switch (cond){
                case 0:
                    v.setX(v.getX() + xUnit);
                    break;
                case 1:
                    v.setX(v.getX()-2-xUnit);
                    break;
                case 2:
                    v.setY(v.getY()+2+yUnit);
                    break;
                case 3:
                    v.setY(v.getY()-2-yUnit);
                    break;
            }
            processView(v);
        }
    }
}

