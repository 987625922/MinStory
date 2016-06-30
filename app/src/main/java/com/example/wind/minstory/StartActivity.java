package com.example.wind.minstory;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

/**
 * Created by wind on 2016/4/25.
 */
public class StartActivity extends FragmentActivity {

    /*启动延迟1000毫秒*/
    private final int sleepTime = 1000;

    Handler mHandler;
    TextView tv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏
        setContentView(R.layout.activity_start);

        Animator animator = AnimatorInflater.loadAnimator(this,R.anim.anim_start);

        tv = (TextView)findViewById(R.id.tv_start);
        animator.setInterpolator(new BounceInterpolator());
        animator.setTarget(tv);
        animator.start();

        mHandler = new Handler();
        mHandler.postDelayed(runMain, sleepTime);



    }


    Runnable runMain = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();
        }
    };


}
