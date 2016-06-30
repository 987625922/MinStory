package com.example.wind.minstory.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.wind.minstory.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wind on 2016/5/5.
 */
public class LineGressBar extends View {
    private int hight;      //整个控件的高
    private int margetXY = 0;
    private int maxWidth; //长线的宽
    private float minWidth = 0;//短线的宽
    private float maxLineWidth; //长线真实的路径，即maxWidth - margetMax
    private ProgressRunnable customProgressBarRunnable;
    //线程池
    private ExecutorService executorService;
    //进度条绘制标记位
    private boolean startDrawProgress = false;

    private Paint maxLinePaint;
    private Paint minLinePaint;
    private int paintWidth = 20; //笔的大小

    //TODO 测试
    int index = 0;
    public LineGressBar(Context context) {
        super(context);
        inti(null, 0);
    }

    public LineGressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inti(attrs, 0);

    }

    public LineGressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti(attrs, defStyleAttr);

    }
    public ProgressRunnable getCustomProgressBarRunnable() {
        return customProgressBarRunnable;
    }

    /**
     * 初始化数据
     */

    private void inti(AttributeSet attrs, int defStyle) {

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LineGressBar, defStyle, 0);
        //从attrs文件中拿属性的值


        a.recycle();

        executorService = Executors.newCachedThreadPool();

        maxLinePaint = new Paint();
        maxLinePaint.setAntiAlias(true);
        maxLinePaint.setStrokeCap(Paint.Cap.ROUND);
        maxLinePaint.setStrokeWidth(paintWidth);
        maxLinePaint.setColor(Color.parseColor("#9D9D9D"));


        minLinePaint = new Paint();
        minLinePaint.setColor(Color.parseColor("#ff99cc00"));
        minLinePaint.setStrokeWidth(paintWidth);
        minLinePaint.setAntiAlias(true);
        minLinePaint.setStrokeCap(Paint.Cap.ROUND);

    }


    /**
     * 测量数据
     */
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        maxWidth = size + getPaddingLeft() + getPaddingRight();
        hight = paintWidth + getPaddingTop() + getPaddingBottom();

        margetXY = paintWidth / 2;
        maxLineWidth = maxWidth - margetXY;
        minWidth = margetXY;
        setMeasuredDimension(maxWidth, paintWidth);

    }


    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawLine(margetXY, margetXY, maxWidth - margetXY, margetXY, maxLinePaint);
        if (startDrawProgress) {
            Log.e("onDraw", "margerXY" + margetXY + ", minWidth"+minWidth);
            canvas.drawLine(margetXY, margetXY, minWidth, margetXY, minLinePaint);

        }

    }

    /**
     * 开始进度条
     */
    public void startProgress(int goPercent) {
        customProgressBarRunnable = new ProgressRunnable(new ChangetHandler(),goPercent);
        //开启一个线程更新进度条
        customProgressBarRunnable.start();
        startDrawProgress = true;
    }

    public void hangUpThread() {
        if (customProgressBarRunnable == null) {
            return;
        }
        customProgressBarRunnable.suspend();
        Log.e("-------------------->", "挂起线程！");
    }

    public void recoverThread() {
        if (customProgressBarRunnable == null) {
            return;
        }
        customProgressBarRunnable.resume();
        Log.e("-------------------->", "恢复线程！");
    }

    public void stopThread() {
        if (customProgressBarRunnable == null) {
            return;
        }
        customProgressBarRunnable.stop();
        Log.e("-------------------->", "停止线程！");
    }

    /**
     * 关闭线程池
     */
    public void closeThreadPool() {
        executorService.shutdown();
    }


    public class ProgressRunnable implements Runnable {
        private boolean running = false;
        private boolean waitting = false;
        private Handler handler;
        private Thread thread;
        //进度条前进百分比
        private int goPercent;
        //整个进度条为百分之百，按照百分比前进
        private int allPercent = 100;
        //已经前进的百分比
        private int yetPercent = 0;

        public ProgressRunnable(Handler handler,int percent) {
            this.handler = handler;
            this.goPercent = percent;
            thread = new Thread(this);
        }

        public void isWaiting() {
            waitting = true;
        }

        @Override
        public void run() {
            while (yetPercent <= 100) {

                synchronized (this) {
                    if (!running) {
                        break;
                    }

                    if (waitting) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                if (!waitting) {
                    execute();
                    yetPercent += goPercent;
                }

            }

        }

        public void execute() {
            if (running && !waitting) {

                minWidth += maxWidth*((float)goPercent / allPercent) - margetXY*2*((float)goPercent / allPercent);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(Message.obtain(handler, 1));
            }
        }

        //开始线程
        public void start() {
            running = true;
            executorService.execute(thread);
        }

        //挂起线程
        public void suspend() {
            if (waitting) {
                return;
            }
            synchronized (this) {
                this.waitting = true;
            }
        }

        //恢复线程
        public void resume() {
            if (!waitting) {
                return;
            }

            synchronized (this) {
                this.waitting = false;
                this.notifyAll();
            }
        }

        //停止线程
        public void stop() {
            if (!running) {
                return;
            }

            synchronized (this) {
                running = false;
            }
        }


    }

    class ChangetHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            invalidate();

        }
    }


}
