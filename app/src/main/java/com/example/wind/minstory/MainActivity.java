package com.example.wind.minstory;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

import com.example.wind.minstory.animation.BounceTopEnter;
import com.example.wind.minstory.animation.ZoomOutBottomExit;
import com.example.wind.minstory.fragment.ClassFragment;
import com.example.wind.minstory.fragment.MainFragment;
import com.example.wind.minstory.view.LoginDialog;

public class MainActivity extends FragmentActivity {

    private FloatingActionButton mainBtn;
    private FloatingActionButton mainSetting;
    private FloatingActionButton mainPerson;
    private FloatingActionButton mainGame;
    private boolean viewIndex = false;
    private ViewPager vpContent;
    private TabLayout tabLayout;
    private LinearLayout login;
    private String[] mTitle = {"内容", "分类"};
    private Fragment[] fragments = new Fragment[mTitle.length];
    private MyOrderAdapter adapter;
    private Context context;
    private FloatingActionButton[] floatingActionButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpContent = (ViewPager) findViewById(R.id.vp_content);
        tabLayout = (TabLayout) findViewById(R.id.main_tab);
        login = (LinearLayout) findViewById(R.id.login);
        mainBtn = (FloatingActionButton) findViewById(R.id.main_btn);
        mainSetting = (FloatingActionButton) findViewById(R.id.main_setting);
        mainPerson = (FloatingActionButton) findViewById(R.id.main_person);
        mainGame = (FloatingActionButton) findViewById(R.id.main_game);
        context = this;
        floatingActionButtons = new FloatingActionButton[]{
                mainBtn, mainPerson, mainSetting, mainGame
        };
        fragments[0] = new MainFragment();
        fragments[1] = new ClassFragment();

        adapter = new MyOrderAdapter(getSupportFragmentManager());
        vpContent.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContent);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        vpContent.setCurrentItem(0);


        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!viewIndex) {
//                    mainGame.setVisibility(View.VISIBLE);
//                    mainSetting.setVisibility(View.VISIBLE);
//                    mainPerson.setVisibility(View.VISIBLE);
//                    viewIndex = true;
//                } else {
//                    mainGame.setVisibility(View.GONE);
//                    mainSetting.setVisibility(View.GONE);
//                    mainPerson.setVisibility(View.GONE);
//                    viewIndex = false;
//
//                }
                if (!viewIndex) {
                    startBtnAnim();
                } else {
                    closeBtnAnim();
                }

            }
        });
        mainPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PersonActivity.class));
            }
        });
        mainSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserAttentionActivity.class));


            }
        });
        mainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog loginDialog = new LoginDialog(context);
//                NewsPaperEnter newsPaperEnter = new NewsPaperEnter();
                BounceTopEnter bounceTopEnter = new BounceTopEnter();
                loginDialog.showAnim(bounceTopEnter).isTitleShow(true);
                loginDialog.dismissAnim(new ZoomOutBottomExit());
                loginDialog.show();

            }
        });
    }

    class MyOrderAdapter extends FragmentPagerAdapter {

        public MyOrderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }


    private void startBtnAnim() {

        ObjectAnimator mainBtnAnim = ObjectAnimator.ofFloat(floatingActionButtons[0], "alpha", 1F, 0.5F);
        ObjectAnimator leftBtnAnim = ObjectAnimator.ofFloat(floatingActionButtons[1], "translationX", -200F);
        ObjectAnimator oqBtnAnimX = ObjectAnimator.ofFloat(floatingActionButtons[2], "translationX", -150F);
        ObjectAnimator oqBtnAnimY = ObjectAnimator.ofFloat(floatingActionButtons[2], "translationY", -150F);
        AnimatorSet oqSet = new AnimatorSet();
        oqSet.playTogether(oqBtnAnimX, oqBtnAnimY);

        ObjectAnimator topBtnAnim = ObjectAnimator.ofFloat(floatingActionButtons[3], "translationY", -200F);
        AnimatorSet as = new AnimatorSet();
        as.setDuration(500);
        as.setInterpolator(new BounceInterpolator());
        as.playTogether(mainBtnAnim,
                leftBtnAnim,
                oqSet,
                topBtnAnim);
        as.start();
        viewIndex = true;
    }

    private void closeBtnAnim() {
        ObjectAnimator mainBtnAnim = ObjectAnimator.ofFloat(floatingActionButtons[0], "alpha", 0.5F, 1F);
        ObjectAnimator leftBtnAnim = ObjectAnimator.ofFloat(floatingActionButtons[1], "translationX", 0F);
        ObjectAnimator oqBtnAnimX = ObjectAnimator.ofFloat(floatingActionButtons[2], "translationX", 0F);
        ObjectAnimator oqBtnAnimY = ObjectAnimator.ofFloat(floatingActionButtons[2], "translationY", 0F);
        AnimatorSet oqSet = new AnimatorSet();
        oqSet.playTogether(oqBtnAnimX, oqBtnAnimY);

        ObjectAnimator topBtnAnim = ObjectAnimator.ofFloat(floatingActionButtons[3], "translationY", 0F);
        AnimatorSet as = new AnimatorSet();
        as.setDuration(500);
        as.setInterpolator(new BounceInterpolator());
        as.playTogether(mainBtnAnim,
                leftBtnAnim,
                oqSet,
                topBtnAnim);
        as.start();
        viewIndex = false;
    }


}
