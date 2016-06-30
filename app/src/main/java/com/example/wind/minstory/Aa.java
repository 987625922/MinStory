package com.example.wind.minstory;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * 学习Objector中对照网上的学习写下的一个笔记类
 *
 * 本类主要是对Property Animation动画的一些常用的学习
 * 并没有全部包含,但可以作为基础的学习.
 *
 * 主要是ObjectAnimator,ValueAnimator,AnimatorSet
 *
 * ObjectAnimator:
 * ValueAnimator的子类，允许您设置一个目标对象和对象属性的动画。
 *方法：
 * setDuration(long duration) 设置动画长度
 *
 *
 * AnimatorSet:
 * 提供了一种机制，以组的动画一起，使它们相对于运行到彼此。
 * 您可以设置动画一起运行，按顺序，或在指定的延迟之后。
 *
 * ValueAnimator:
 *  还不懂0.0
 *
 * AnimatorInflater:
 * 用户加载属性动画的xml文件
 *
 *
 * translate、scale、alpha、rotate
 *
 * Created by wind on 2016/5/6.
 */
public class Aa extends FragmentActivity {
    ImageView img;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa);
        //动画布局文件调用
        final Animator anim = AnimatorInflater.loadAnimator(this, R.anim.aa_set);


        img = (ImageView)findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                rotateyAnimRun(v); //使用ObjectAnimator动画并且实现多动画属性并行
                propertyValuesHolder(v); //使用ObjectAnimator结合propertyValuesHolder实现多动画，比上面简单和有用


                /**
                 * setPivotX(float) 属性说明: 水平方向偏转量
                 * 动画作用的X和Y轴位置，不写就是中间,下面是左上角
                 *
                 * 动画布局文件使用
                 *
                 */
                v.setPivotX(50);
                v.setPivotY(50);
                //显示的调用invalidate
                v.invalidate();
                anim.setTarget(v);
                anim.start();

            }
        });




    }

    /**ObjectAnimator
     *
     * 主要是对Object一些基本的应用
     *
     * 重写了ValueAnimator里面的AnimatorUpdateListener方法实现多动画(不建议使用)
     *
     *
     *
      */
    public void rotateyAnimRun(final View view)
    {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "zhy", 1.0F,  0.0F);
        anim.setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);

            }
        });

    }



    /**ObjectAnimator使用PropertyValuesHolder多动画并行
     *
     * @param view
     */
    public void propertyValuesHolder(View view)
    {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }




    /**
     *ObjectAnimator并重写animatorListener方法实现动画完了之后，删除控件
     *
     *
     * @param view
     */
    public void outView(final View view)
    {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0.5f);



        anim.addListener(new Animator.AnimatorListener()
        {

            @Override
            public void onAnimationStart(Animator animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null)
                    parent.removeView(view);
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
            }
        });



        /**
         * 效果同上只是不写多代码
         * */
//        anim.addListener(new AnimatorListenerAdapter()
//        {
//            @Override
//            public void onAnimationEnd(Animator animation)
//            {
//                Log.e(TAG, "onAnimationEnd");
//                ViewGroup parent = (ViewGroup) mBlueBall.getParent();
//                if (parent != null)
//                    parent.removeView(mBlueBall);
//            }
//        });

        anim.start();
    }


    /**
     * ObjectAnimator配合AnimatorSet
     *
     * 多个动画并行
     *
     * */

    public void viewTogetherRun(View view)
    {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY",
                1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }


    /**ObjectAnimator配合AnimatorSet
     *
     * 实现动画按先后顺序执行
     *
     * @param view
     */

    public void playWithAfter(View view)
    {
        float cx = view.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY",
                1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view,
                "x",  cx ,  0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view,
                "x", cx);

        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         * after是接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }



}
