package com.example.wind.minstory.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wind.minstory.R;


/**
 * 配合RefreshAndLoadMoreView 完成下拉刷新、滑到底部自动加载更多
 *
 * @author zeda
 */
public class LoadMoreListView extends ListView implements OnScrollListener {

    private View rooterView, lineView;
    private boolean isHaveMoreData = true;// 是否有更多数据(默认为有)
    private ProgressBar progressBar;
    private TextView tipContext;

    private RefreshAndLoadMoreView mRefreshAndLoadMoreView;
    private boolean isLoading = false;// 是否正在加载

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rooterView = LayoutInflater.from(context).inflate(R.layout.pull_to_load_footer, null);
        lineView = rooterView.findViewById(R.id.line_layout);
        progressBar = (ProgressBar) rooterView.findViewById(R.id.pull_to_load_footer_progressbar);
        tipContext = (TextView) rooterView.findViewById(R.id.pull_to_load_footer_hint_textview);
        addFooterView(rooterView, null, false);
        setOnScrollListener(this);
    }

    public void setRefreshAndLoadMoreView(RefreshAndLoadMoreView mRefreshAndLoadMoreView) {
        this.mRefreshAndLoadMoreView = mRefreshAndLoadMoreView;
    }

//    /**
//     * 设置是否还有更多数据
//     *
//     * @param isHaveMoreData
//     */
//    public void setHaveMoreData(boolean isHaveMoreData, int size) {
//        this.isHaveMoreData = isHaveMoreData;
//        if (!isHaveMoreData) {
//            if (size == 0)
//                tipContext.setText("暂没有数据");
//            else
//                tipContext.setText("只有这么多啦");
//            progressBar.setVisibility(View.GONE);
//        } else {
//            tipContext.setText("正在加载");
//            progressBar.setVisibility(View.VISIBLE);
//        }
//    }

    /**
     * 没有更多数据的时候
     *
     * @param size 当前列表总数量
     */
    public void onNotMoreData(int size) {
        this.isHaveMoreData = false;
        if (size == 0)
            tipContext.setText("暂没有数据");
        else
            tipContext.setText("只有这么多啦");
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 是否有下一页信息
     *
     * @param isNextData
     */
    public void isNextData(boolean isNextData) {
        if (!isNextData) {
            this.isHaveMoreData = false;
            tipContext.setText("只有这么多啦");
            progressBar.setVisibility(GONE);
        } else {
            this.isHaveMoreData = true;
            tipContext.setText("正在加载中");
            progressBar.setVisibility(VISIBLE);
        }
    }

    /**
     * 有更多数据的时候
     */
    public void onHaveMoreData() {
        this.isHaveMoreData = true;
        tipContext.setText("正在加载");
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 加载失败的时候
     */
    public void onLoadFailure() {
        this.isHaveMoreData = true;
        tipContext.setText("加载失败");
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 加载成功的时候
     *
     * @param count 新增加数据的数量
     * @param size  总数据的数量
     */
    public void onLoadSuccess(int count, int size) {
        if (count < 15) {
            if (size == 0) {
                tipContext.setText("暂没有数据");
            } else {
                tipContext.setText("只有这么多啦");
            }
            this.isHaveMoreData = false;
            progressBar.setVisibility(View.GONE);
        } else {
            tipContext.setText("正在加载");
            progressBar.setVisibility(View.VISIBLE);
            this.isHaveMoreData = true;
        }
    }

    /**
     * 加载完成
     */
    public void onLoadComplete() {
        isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            if (view.getLastVisiblePosition() == view.getCount() - 1
                    && (mRefreshAndLoadMoreView != null && !mRefreshAndLoadMoreView
                    .isRefreshing()) && !isLoading
                    && mOnLoadMoreListener != null && isHaveMoreData) {
                isLoading = true;
                mOnLoadMoreListener.onLoadMore();
            }
            else if(view.getLastVisiblePosition() == view.getCount() - 1
                    && !isLoading && mOnLoadMoreListener != null && isHaveMoreData){
                isLoading = true;
                mOnLoadMoreListener.onLoadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
    }

    /**
     * 加载更多时间监听
     *
     * @author zeda
     */
    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    /**
     * 显示底部加载View和listView分割线
     */
    public void showLineView() {
        if (lineView != null)
            lineView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏底部加载View和listView分割线
     */
    public void hiddenLineView() {
        if (lineView != null)
            lineView.setVisibility(View.GONE);
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }



}
