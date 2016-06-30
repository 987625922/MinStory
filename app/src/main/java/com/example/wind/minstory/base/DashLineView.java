package com.example.wind.minstory.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.example.wind.minstory.R;


/**
 * 虚线view
 * Created by deng on 16/4/18.
 */
public class DashLineView extends View {

    private final int HORIZONTAL = 0, VERTICAL = 1;

    private int mOrientation = 1;
    private int mDashColor = Color.BLACK;
    private int mDashWidth = 10;
    private int mWidth = 10;

    public DashLineView(Context context) {
        this(context, null);
    }

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DashLineView, 0, 0);
        mOrientation = a.getInt(R.styleable.DashLineView_dlv_orientation, mOrientation);
        mDashColor = a.getColor(R.styleable.DashLineView_dlv_dashColor, mDashColor);
        mDashWidth = a.getDimensionPixelSize(R.styleable.DashLineView_dlv_dashWidth, mDashWidth);
        mWidth = a.getDimensionPixelSize(R.styleable.DashLineView_dlv_width, mWidth);
        a.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mDashColor);
        paint.setStrokeWidth(mWidth);
//
//        PathEffect effects = new DashPathEffect(new float[]{mDashWidth, mDashWidth, mDashWidth, mDashWidth}, 1);
//        paint.setPathEffect(effects);
//
//        if(HORIZONTAL == mOrientation)
//            canvas.drawLine(0,0,getWidth(),0,paint);
//        else canvas.drawLine(0,0,0,getHeight(),paint);

        Path path = new Path();
        if(mOrientation == HORIZONTAL){
            path.moveTo(0, 0);
            path.lineTo(getWidth(),0);
        }else{
            path.moveTo(0,0);
            path.lineTo(0,getHeight());
        }
        PathEffect effects = new DashPathEffect(new float[]{mDashWidth,mDashWidth,mDashWidth,mDashWidth},1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
}
