package com.example.wind.minstory.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;

import com.example.wind.minstory.R;
import com.example.wind.minstory.base.ZYAdapter;
import com.example.wind.minstory.base.ZYViewHolder;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.view.IOSTaoBaoDialog;

import java.util.List;

/**
 * Created by wind on 2016/4/27.
 */
public class MainAttentionAdapter extends ZYAdapter<MainContent> {
    private SparseBooleanArray sp_collect = new SparseBooleanArray();
    private SparseBooleanArray sp_download = new SparseBooleanArray();
    private View view;

    public MainAttentionAdapter(Context mContext, List<MainContent> mDatas, int mItemLayoutId, View view) {

        super(mContext, mDatas, mItemLayoutId);
        this.view = view;

        for (int i = 0; i < mDatas.size(); i++) {
            sp_collect.put(i, false);
            sp_download.put(i, false);
        }

    }

    @Override
    public void convert(final ZYViewHolder helper, MainContent item, final int position) {
        helper.setText(R.id.tv_item_main_title, item.getTitle());
        helper.setText(R.id.tv_item_main_intro, item.getIntro());
        ImageView img = helper.getView(R.id.img_item_main);
        img.setImageResource(item.getImg());
        final ImageView collect = helper.getView(R.id.main_collect);
        if (sp_collect.get(position)){
            collect.setImageResource(R.mipmap.star);

        }else {
            collect.setImageResource(R.mipmap.star_selected);

        }
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp_collect.get(position)) {
                    collect.setImageResource(R.mipmap.star_selected);

                    sp_collect.put(position, false);
                } else {
                    collect.setImageResource(R.mipmap.star);

                    sp_collect.put(position, true);
                }
            }
        });
        ImageView share = helper.getView(R.id.main_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IOSTaoBaoDialog dialog = new IOSTaoBaoDialog(mContext, (View) view.getParent());
                dialog.show();
            }
        });
        final ImageView download = helper.getView(R.id.main_download);
        if (sp_download.get(position)){
            download.setImageResource(R.mipmap.dl_paused_i);
        }else {
            download.setImageResource(R.mipmap.download_m);
        }

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp_download.get(position)){
                    download.setImageResource(R.mipmap.download_m);
                    sp_download.put(position,false);
                }else {
                    download.setImageResource(R.mipmap.dl_paused_i);
                    sp_download.put(position,true);
                }
            }
        });




    }

}

