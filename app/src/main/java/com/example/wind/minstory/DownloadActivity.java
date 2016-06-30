package com.example.wind.minstory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.wind.minstory.adapter.DownloadAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/5/3.
 */
public class DownloadActivity extends FragmentActivity {
    private ListView listView;
    private ImageView back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadstory);
        listView = (ListView)findViewById(R.id.listview);
        back = (ImageView)findViewById(R.id.back);

        List<String> list = new ArrayList<>();
        list.add("aa");
        DownloadAdapter downloadAdapter = new DownloadAdapter(this,list,R.layout.item_download);
        listView.setAdapter(downloadAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
