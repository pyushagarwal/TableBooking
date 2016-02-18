package com.example.piyush.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by piyush on 7/23/2015.
 */
public class SeatLayout extends View {

    private  Drawable back;
    public SeatLayout(Context context) {
        super(context);
    }

    public SeatLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public SeatLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int width, int height)
    {
        back=getResources().getDrawable(R.drawable.restaurantmapfinalempty);
        int finalheight=(back.getIntrinsicHeight()/back.getIntrinsicWidth())*width;
        setMeasuredDimension(width, finalheight);
        setBackgroundDrawable(back);
    }

}