package com.example.wind.minstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.wind.minstory.adapter.MainAttentionAdapter;
import com.example.wind.minstory.base.LoadMoreListView;
import com.example.wind.minstory.base.RefreshAndLoadMoreView;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.content.MainContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/4/26.
 */
public class UserAttentionActivity extends FragmentActivity {
    private ImageView back;
    private LoadMoreListView listView;
    private RefreshAndLoadMoreView refreshAndLoadMoreView;
    private MainContentData content;
    private List<MainContent> mainContents;
    private MainAttentionAdapter attentionAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_user);
        back = (ImageView) findViewById(R.id.back);
        listView = (LoadMoreListView)findViewById(R.id.attention_list_user);
        refreshAndLoadMoreView = (RefreshAndLoadMoreView)findViewById(R.id.rl_attention_user);




        refreshAndLoadMoreView.setLoadMoreListView(listView);
        listView.setRefreshAndLoadMoreView(refreshAndLoadMoreView);


        content = new MainContentData();
        mainContents = new ArrayList<>();
        for (int i = 0; i <content.getTitles().length;i++){
            MainContent mainContent = new MainContent();
            mainContent.setTitle(content.getTitles()[i]);
            mainContent.setImg(content.getImgs()[i]);
            mainContent.setIntro(content.getIntros()[i]);
            mainContents.add(mainContent);
        }

        attentionAdapter = new MainAttentionAdapter(this,mainContents,R.layout.item_main,listView);
        listView.setAdapter(attentionAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(UserAttentionActivity.this, ContentActivity.class));
            }
        });

        //底部加载更多
        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                for (int i = 0; i < 2; i++) {
                    MainContent mainContent = new MainContent();
                    mainContent.setTitle(content.getTitles()[i]);
                    mainContent.setImg(content.getImgs()[i]);
                    mainContent.setIntro(content.getIntros()[i]);
                    mainContents.add(mainContent);
                }


                listView.isNextData(true);
                attentionAdapter.notifyDataSetChanged();

                listView.onLoadComplete();


            }
        });
        //下拉加载事件
        refreshAndLoadMoreView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshAndLoadMoreView.setRefreshing(false);

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
