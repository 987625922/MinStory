package com.example.wind.minstory.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ZYAdapter<T> extends BaseAdapter {

    public final String TAG = this.getClass().getSimpleName();

    protected final int mItemLayoutId;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;

    public ZYAdapter(Context mContext, List<T> mDatas, int mItemLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mItemLayoutId = mItemLayoutId;
    }

    @Override
    public int getCount() {
        if (mDatas.size() != 0)
            return mDatas.size();
        else return 0;
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ZYViewHolder viewHolder = ZYViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    /**
     * 更新数据
     */
    public void update(List<T> mDatas) {
        if (mDatas.size() == 0) {
            return;
        }
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    public abstract void convert(ZYViewHolder helper, T item, int position);

}
