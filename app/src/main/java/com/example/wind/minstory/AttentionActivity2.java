package com.example.wind.minstory;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.wind.minstory.adapter.RecyclerAdapter;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.content.MainContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/5/24.
 */
public class AttentionActivity2 extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout rootLayout;
    private MainContentData content;
    private List<MainContent> mainContents;
    private RecyclerView recycler;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private int lastVisibleItem;
    private int position;
    private String[] mTitle = {"全部","精选童话","安徒生童话","格林童话","希腊神话",
            "豪夫童话","法国童话","伊索寓言","世界名著","影响世界的100本书"
            ,"中国","外国"};
    private TextView attention;
    private boolean index = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");

        initToolbar();
        initInstances();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initInstances() {
        attention = (TextView) findViewById(R.id.attention);
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index){
                    attention.setText("关注");
                    index = false;
                }else {
                    attention.setText("已关注");
                    index = true;
                }
            }
        });

        recycler = (RecyclerView)findViewById(R.id.attention_list);

        content = new MainContentData();

        mainContents = new ArrayList<>();
        for (int i = 0; i <content.getTitles().length;i++){
            MainContent mainContent = new MainContent();
            mainContent.setTitle(content.getTitles()[i]);
            mainContent.setImg(content.getImgs()[i]);
            mainContent.setIntro(content.getIntros()[i]);
            mainContents.add(mainContent);
        }



        recyclerAdapter = new RecyclerAdapter(AttentionActivity2.this,mainContents);

        recyclerAdapter.setOnItemClickLitener(new RecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(AttentionActivity2.this,ContentActivity.class));

            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        recycler.setAdapter(recyclerAdapter);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(mTitle[position]);

        toolbar.setNavigationIcon(R.mipmap.ic_back);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });


        //上拉刷新
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerAdapter.getItemCount()) {
                    List<MainContent> list = new ArrayList<MainContent>();
                    for (int i = 0; i < 2; i++) {
                        MainContent mainContent = new MainContent();
                        mainContent.setTitle(content.getTitles()[i]);
                        mainContent.setImg(content.getImgs()[i]);
                        mainContent.setIntro(content.getIntros()[i]);
                        list.add(mainContent);
                    }
                    recyclerAdapter.loadAddItem(list);


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
