package com.example.wind.minstory.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

;import com.example.wind.minstory.R;

/**
 * 配合LoadMoreListView 完成下拉刷新、滑到底部自动加载更多
 *
 * @author zeda
 */
public class RefreshAndLoadMoreView extends SwipeRefreshLayout {

    private LoadMoreListView mLoadMoreListView;

    public RefreshAndLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = getResources();
        setColorSchemeColors(res.getColor(R.color.refresh_color_1),
                res.getColor(R.color.refresh_color_2),
                res.getColor(R.color.refresh_color_3),
                res.getColor(R.color.refresh_color_4));
    }

    public void setLoadMoreListView(LoadMoreListView mLoadMoreListView) {
        this.mLoadMoreListView = mLoadMoreListView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mLoadMoreListView != null && mLoadMoreListView.isLoading())
            return false;
        return super.onTouchEvent(event);
    }
}
