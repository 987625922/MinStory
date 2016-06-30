package com.example.wind.minstory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.wind.minstory.adapter.AttentionAdapter;
import com.example.wind.minstory.base.LoadMoreListView;
import com.example.wind.minstory.base.RefreshAndLoadMoreView;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.content.MainContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/5/30.
 */
public class BrowseLogActivity extends Activity {
    private ImageView back;
    private LoadMoreListView load;
    private RefreshAndLoadMoreView refresh;
    private AttentionAdapter attentionAdapter;
    private MainContentData content;
    private List<MainContent> mainContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refresh = (RefreshAndLoadMoreView)findViewById(R.id.refresh);
        load = (LoadMoreListView)findViewById(R.id.load);

        refresh.setLoadMoreListView(load);
        load.setRefreshAndLoadMoreView(refresh);


        content = new MainContentData();
        mainContents = new ArrayList<>();
        for (int i = 0; i <content.getTitles().length;i++){
            MainContent mainContent = new MainContent();
            mainContent.setTitle(content.getTitles()[i]);
            mainContent.setImg(content.getImgs()[i]);
            mainContent.setIntro(content.getIntros()[i]);
            mainContents.add(mainContent);
        }

        attentionAdapter = new AttentionAdapter(this,mainContents,R.layout.item_main,load);
        load.setAdapter(attentionAdapter);


        load.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(BrowseLogActivity.this, ContentActivity.class));
            }
        });

        //底部加载更多
        load.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                for (int i = 0; i < 2; i++) {
                    MainContent mainContent = new MainContent();
                    mainContent.setTitle(content.getTitles()[i]);
                    mainContent.setImg(content.getImgs()[i]);
                    mainContent.setIntro(content.getIntros()[i]);
                    mainContents.add(mainContent);
                }


                load.isNextData(true);
                attentionAdapter.notifyDataSetChanged();

                load.onLoadComplete();


            }
        });
        //下拉加载事件
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refresh.setRefreshing(false);

            }
        });


    }
}
