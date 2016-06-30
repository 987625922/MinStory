package com.example.wind.minstory.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.wind.minstory.R;
import com.example.wind.minstory.base.ZYAdapter;
import com.example.wind.minstory.base.ZYViewHolder;
import com.example.wind.minstory.content.GameModel;

import java.util.List;

/**
 * Created by wind on 2016/6/29.
 */
public class GridviewGameAdapter extends ZYAdapter<GameModel> {
    private ImageView img;
    public GridviewGameAdapter(Context mContext, List<GameModel> mDatas, int mItemLayoutId) {
        super(mContext, mDatas, mItemLayoutId);
    }

    @Override
    public void convert(ZYViewHolder helper, GameModel item, int position) {
        img = helper.getView(R.id.item_game_img);
        img.setImageResource(item.getImg());
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_conntent,item.getConntect());
        helper.setText(R.id.tv_outher,item.getOuther());
        helper.setText(R.id.tv_version,item.getVersion());
        }

}
