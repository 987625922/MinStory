package com.example.wind.minstory.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.wind.minstory.R;
import com.example.wind.minstory.base.LineGressBar;
import com.example.wind.minstory.base.ZYAdapter;
import com.example.wind.minstory.base.ZYViewHolder;

import java.util.List;

/**
 * Created by wind on 2016/5/3.
 */
public class DownloadAdapter extends ZYAdapter<String> {

    private LineGressBar line;
    private ImageView img;
    private boolean index = true;
    public DownloadAdapter(Context mContext, List<String> mDatas, int mItemLayoutId) {
        super(mContext, mDatas, mItemLayoutId);
    }
    @Override
    public void convert(ZYViewHolder helper, String item, int position) {
        line = helper.getView(R.id.progress);
        line.startProgress(10);
        img = helper.getView(R.id.img_stop);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index){
                    line.hangUpThread();
                    index = false;
                    img.setImageResource(R.mipmap.img_download_stop);
                }else{
                    line.recoverThread();
                    index = true;
                    img.setImageResource(R.mipmap.img_download_start);
                }
            }
        });
    }

}
