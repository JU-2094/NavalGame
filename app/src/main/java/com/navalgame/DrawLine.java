package com.navalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by urb on 1/06/17.
 */

public class DrawLine extends View {

    Paint paint;
    final int CONST_GRID = 10;

    public DrawLine(Context context, AttributeSet attrs) {
        super(context,attrs);
        paint = new Paint();
    }

    @Override
    public void onDraw(Canvas canvas) {

        Log.e("SIZE","Width "+getWidth());
        Log.e("SIZE","Height "+getHeight());


        paint.setColor(Color.CYAN);
        paint.setAlpha(125);
        paint.setStrokeWidth(5f);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        int dx = getWidth()/CONST_GRID;
        int dy = getHeight()/CONST_GRID;

        for(int drawX=0;drawX<getWidth();drawX+=dx){
            canvas.drawLine(drawX,0,drawX,getHeight(),paint);
        }
        for(int drawY=0;drawY<getHeight();drawY+=dy){
            canvas.drawLine(0,drawY,getWidth(),drawY,paint);
        }
    }
}
