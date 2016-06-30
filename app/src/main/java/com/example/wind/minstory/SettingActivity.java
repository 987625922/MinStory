package com.example.wind.minstory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wind on 2016/4/27.
 */
public class SettingActivity extends FragmentActivity {
    private ImageView sendStory;
    private ImageView updata;
    private ImageView wifi;
    private RelativeLayout about;
    private boolean sendStoryIndex = false;
    private boolean updataIndex = false;
    private boolean wifiIndex = false;
    private ImageView fontbig, fontzhong, fontsmall;
    private ImageView back;
    private ProgressDialog dialog;
    private RelativeLayout clear;
    private RelativeLayout rlupdata;
    private TextView tvClear;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sendStory = (ImageView) findViewById(R.id.img_story_send);
        rlupdata = (RelativeLayout)findViewById(R.id.rl_updata);
        tvClear = (TextView)findViewById(R.id.tv_clear);
        dialog = new ProgressDialog(SettingActivity.this);
        clear = (RelativeLayout) findViewById(R.id.rl_clear);
        about = (RelativeLayout) findViewById(R.id.rl_about);
        updata = (ImageView) findViewById(R.id.updata);
        wifi = (ImageView) findViewById(R.id.wifi);
        fontbig = (ImageView) findViewById(R.id.font_big);
        fontsmall = (ImageView) findViewById(R.id.font_small);
        fontzhong = (ImageView) findViewById(R.id.font_zhong);
        back = (ImageView) findViewById(R.id.back);
        fontbig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontbig.setImageResource(R.mipmap.pressed);
                fontzhong.setImageResource(R.mipmap.press);
                fontsmall.setImageResource(R.mipmap.press);
            }
        });
        fontzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontzhong.setImageResource(R.mipmap.pressed);
                fontbig.setImageResource(R.mipmap.press);
                fontsmall.setImageResource(R.mipmap.press);
            }
        });
        fontsmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontsmall.setImageResource(R.mipmap.pressed);
                fontbig.setImageResource(R.mipmap.press);
                fontzhong.setImageResource(R.mipmap.press);
            }
        });
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifiIndex) {
                    wifi.setImageResource(R.mipmap.switch_off);
                    wifiIndex = false;

                } else {
                    wifi.setImageResource(R.mipmap.switch_on);
                    wifiIndex = true;

                }
            }
        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updataIndex) {
                    updata.setImageResource(R.mipmap.switch_off);
                    updataIndex = false;

                } else {
                    updata.setImageResource(R.mipmap.switch_on);
                    updataIndex = true;

                }
            }
        });

        sendStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendStoryIndex) {
                    sendStory.setImageResource(R.mipmap.switch_off);
                    sendStoryIndex = false;

                } else {
                    sendStory.setImageResource(R.mipmap.switch_on);

                    sendStoryIndex = true;


                }
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("正在清理...请稍后");
                dialog.setCancelable(false);
                dialog.show();
                new Handler().postDelayed(new Runnable(){
                    public void run(){
                        dialog.dismiss();
                        tvClear.setText("0M");
                    }
                },2000);
            }
        });
        rlupdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("检查更新...请稍后");
                dialog.setCancelable(false);
                dialog.show();
                new Handler().postDelayed(new Runnable(){
                    public void run(){
                        dialog.dismiss();
                        Toast.makeText(SettingActivity.this,"已是最新版本",Toast.LENGTH_SHORT).show();
                    }
                },2000);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
