package com.example.wind.minstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by wind on 2016/4/26.
 */
public class PersonActivity extends FragmentActivity {
    private ImageView back;
    private LinearLayout goSetting;
    private LinearLayout download;
    private Button outOfLogin;
    private LinearLayout localStory;
    private LinearLayout browse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        localStory = (LinearLayout) findViewById(R.id.ll_loacl_story);
        outOfLogin = (Button) findViewById(R.id.btn_out_of_login);
        download = (LinearLayout) findViewById(R.id.storydownload);
        back = (ImageView) findViewById(R.id.back);
        goSetting = (LinearLayout) findViewById(R.id.ll_go_setting);
        browse = (LinearLayout) findViewById(R.id.ll_browse_log);
        goSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonActivity.this, SettingActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonActivity.this, DownloadActivity.class));
            }
        });
        outOfLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonActivity.this, "退出登陆", Toast.LENGTH_SHORT).show();
            }
        });
        //本地故事的点击事件
        localStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonActivity.this,LocalStoryActivity.class));
            }
        });
        //浏览记录的点击事件
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonActivity.this,BrowseLogActivity.class));
            }
        });

    }


}
