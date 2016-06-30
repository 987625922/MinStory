package com.example.wind.minstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wind.minstory.adapter.AttentionAdapter;
import com.example.wind.minstory.base.LoadMoreListView;
import com.example.wind.minstory.base.RefreshAndLoadMoreView;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.content.MainContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/4/22.
 */
public class AttentionActivity extends FragmentActivity {
    private ImageView back;
    private int position;
    private TextView title;
    private TextView attention;
    private boolean attentionIndex = false;
    private LoadMoreListView listView;
    private RefreshAndLoadMoreView refreshAndLoadMoreView;
    private MainContentData content;
    private List<MainContent> mainContents;
    private AttentionAdapter attentionAdapter;
    private String[] mTitle = {"全部","精选童话","安徒生童话","格林童话","希腊神话",
            "豪夫童话","法国童话","伊索寓言","世界名著","影响世界的100本书"
            ,"中国","外国"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        title = (TextView)findViewById(R.id.attention_title);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        back = (ImageView)findViewById(R.id.back);
        attention = (TextView)findViewById(R.id.attention);
        listView = (LoadMoreListView)findViewById(R.id.attention_list);
        refreshAndLoadMoreView = (RefreshAndLoadMoreView)findViewById(R.id.rl_attention);

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

        attentionAdapter = new AttentionAdapter(this,mainContents,R.layout.item_main,listView);
        listView.setAdapter(attentionAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(AttentionActivity.this, ContentActivity.class));
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


        title.setText(mTitle[position]);

        //关注按钮
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attentionIndex) {
                    attentionIndex = false;
                    attention.setText("关注");

                } else {
                    attentionIndex = true;
                    attention.setText("已关注");

                }
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
