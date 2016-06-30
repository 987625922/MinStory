package com.example.wind.minstory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wind.minstory.base.LoadMoreListView;
import com.example.wind.minstory.base.RefreshAndLoadMoreView;

/**
 * Created by wind on 2016/5/30.
 */
public class LocalStoryActivity extends Activity {
    private RefreshAndLoadMoreView refresh;
    private LoadMoreListView load;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localstory);
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





    }
}
