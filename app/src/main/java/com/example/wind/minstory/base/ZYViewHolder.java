package com.example.wind.minstory.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * ZYAdapter适配器的视图所有者
 */
public class ZYViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ZYViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取实例化
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ZYViewHolder get(Context context, View convertView,
                                   ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ZYViewHolder(context, parent, layoutId, position);
        }
        return (ZYViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    public ZYViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

}
