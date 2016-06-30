package com.example.wind.minstory;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 此Demo博客地址：http://blog.csdn.net/u012814441/article/details/49253761
 * <p/>
 * description:自定义SwipeRefreshLayout(进入页面自动刷新，下拉刷新，点击加载更多)
 * <p/>
 * 在底部加一个Button按钮，来点击“加载更多”
 * <p/>
 * 此demo在Android2.3.7版本运行通过
 * <p/>
 * author:Edward
 * <p/>
 * 2015/10/17
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private ListView listView;
    //“加载更多”监听事件
    private CustomLoadMoreListener customLoadMoreListener;
    //下拉刷新监听事件
    private CustomOnRefreshListener customOnRefreshListener;
    //标记当前正在执行“加载更多”操作,（false表示没有正在加载，true表示正在加载）
    private boolean isLoadMore = false;
    //底部“加载更多”容器
    private RelativeLayout container;
    //“加载更多”按钮TextView
    private TextView loadMoreTextView;
    //“加载更多”进度框
    private ProgressBar progressBar;
    //此Model用来设置整个CustomSwipeRefreshLayout自定义控件的参数
    private SwipeRefreshParamsModel swipeRefreshParamsModel;
    //是否开启Toast提示,默认为不开启
    private boolean isOpenToastHint = false;
    //
    private RelativeLayout.LayoutParams lp, lp1;

    //设置开启Toast
    public void setIsOpenToastHint(boolean isOpenToastHint) {
        this.isOpenToastHint = isOpenToastHint;
    }

    /**
     * 设置底部容器的参数
     *
     * @param swipeRefreshParamsModel
     */
    public void setSwipeRefreshParamsModel(SwipeRefreshParamsModel swipeRefreshParamsModel) {
        this.swipeRefreshParamsModel = swipeRefreshParamsModel;
    }

    /**
     * 设置刷新回调监听器
     *
     * @param customOnRefreshListener
     */
    public void setCustomOnRefreshListener(CustomOnRefreshListener customOnRefreshListener) {
        this.customOnRefreshListener = customOnRefreshListener;
    }

    /**
     * 设置加载更多回调监听器
     *
     * @param customLoadMoreListener
     */
    public void setCustomLoadMoreListener(CustomLoadMoreListener customLoadMoreListener) {
        this.customLoadMoreListener = customLoadMoreListener;
    }


    public CustomSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        setOnRefreshListener(this);

        //如果没有设置SwipeRefreshParamsModel，则实例化初始值
        if (this.swipeRefreshParamsModel == null) {
            this.swipeRefreshParamsModel = new SwipeRefreshParamsModel();
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //了解一下控件绘制流程
        if (listView == null) {
            //获取当前布局孩子的总数
            int count = getChildCount();

            //获取子类布局控件
            if (count > 0) {
                View view = getChildAt(0);
                if (view instanceof ListView) {
                    listView = (ListView) view;

                    //判断用户是否设置过“加载更多”监听事件
                    if (this.customLoadMoreListener != null && listView != null) {
                        setClickLoadMore();
                    }
                }
            }
        }
    }


    /**
     * 设置加载更多按钮
     */
    public void setClickLoadMore() {
        //创建一个container容器
        container = new RelativeLayout(mContext);
        container.setOnClickListener(new loadMoreClickListener());
        //设置间距
        container.setPadding(swipeRefreshParamsModel.getLeftPadding(), swipeRefreshParamsModel.getTopPadding(), swipeRefreshParamsModel.getRightPadding(), swipeRefreshParamsModel.getBottomPadding());
        container.setBackgroundColor(swipeRefreshParamsModel.getSetContainerBackgroundColor());

        loadMoreTextView = new TextView(mContext);
        loadMoreTextView.setTextColor(swipeRefreshParamsModel.getSetTextColor());
        loadMoreTextView.setTextSize(swipeRefreshParamsModel.getTextSize());
        loadMoreTextView.setText(swipeRefreshParamsModel.getFinishRefreshText());
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        loadMoreTextView.setId(Integer.valueOf(1));
        loadMoreTextView.setLayoutParams(lp);
        container.addView(loadMoreTextView);

        //默认为隐藏状态
        progressBar = new ProgressBar(mContext);
        //设置进度框的大小
        lp1 = new RelativeLayout.LayoutParams(swipeRefreshParamsModel.getProgressBarWidthSize(), swipeRefreshParamsModel.getProgressBarHeightSize());
        lp1.addRule(RelativeLayout.LEFT_OF, 1);
        lp1.addRule(RelativeLayout.CENTER_VERTICAL);
        progressBar.setLayoutParams(lp1);
        container.addView(progressBar);
        progressBar.setVisibility(View.GONE);

        if (listView != null) {
            //将整个container容器添加到ListView的底部
            listView.addFooterView(container);
        }
    }

    /**
     * 设置结束加载文本
     */
    public void setFinishLoadMoreText() {
        if (loadMoreTextView != null && progressBar != null) {
            loadMoreTextView.setText(swipeRefreshParamsModel.getFinishRefreshText());
            //重新隐藏进度框
            progressBar.setVisibility(View.GONE);
        }
        //将加载更多的标记设为false（加载完毕）
        isLoadMore = false;
    }


    /**
     * 加载按钮点击事件
     */
    public class loadMoreClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            //没有执行下拉刷新，加载更多的监听事件不为空。
            if (!isRefreshing() && customLoadMoreListener != null) {
                isLoadMore = true;
                loadMoreTextView.setText(swipeRefreshParamsModel.getStartRefreshText());
                //将隐藏的ProGressBar显示出来
                progressBar.setVisibility(View.VISIBLE);
                customLoadMoreListener.loadMore();
            } else {
                if (isOpenToastHint)
                    Toast.makeText(mContext, swipeRefreshParamsModel.getPullUpText(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 设置进入页面自动刷新
     */
    public void setAutoRefresh() {
        //判断是否已经设置了setRefreshing(true)
        if (isRefreshing()) {
            //如果已经设置将其改变为false
            setRefreshing(false);
        }
        post(new Thread(new Runnable() {
            @Override
            public void run() {
                setRefreshing(true);
            }
        }));
        onRefresh();
    }

    @Override
    public void onRefresh() {
        //回调刷新控件，如果监听器为空并且正在处于加载更多的过程中，则不刷新
        if (customOnRefreshListener != null && !isLoadMore) {
            customOnRefreshListener.onRefresh();
        } else {
            if (isOpenToastHint) {
                Toast.makeText(mContext, swipeRefreshParamsModel.getPullDownText(), Toast.LENGTH_SHORT).show();
            }
            setRefreshing(false);
        }
    }

    /**
     * 下拉刷新回调监听事件
     */
    public interface CustomOnRefreshListener {
        void onRefresh();
    }

    /**
     * 加载更多回调监听事件
     */
    public interface CustomLoadMoreListener {
        void loadMore();
    }


    /**
     * 用来设置整个SwipeRefreshLayout控件参数的Model，可提供给用户直接修改。
     */
    public static class SwipeRefreshParamsModel {
        private float textSize = 14;
        private String finishRefreshText = "加载更多";
        private String startRefreshText = "正在加载...";
        //设置进度框的大小
        private int progressBarWidthSize = 50, progressBarHeightSize = 50;
        private int setContainerBackgroundColor = Color.parseColor("#e8ebee");
        private int setTextColor = Color.GRAY;
        private int leftPadding = 20, topPadding = 20, rightPadding = 0, bottomPadding = 20;
        private String pullDownText = "正在加载中，请稍后再试...";
        private String pullUpText = "正在刷新中，请稍后再试...";


        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
        }

        public String getFinishRefreshText() {
            return finishRefreshText;
        }

        public void setFinishRefreshText(String finishRefreshText) {
            this.finishRefreshText = finishRefreshText;
        }

        public String getStartRefreshText() {
            return startRefreshText;
        }

        public void setStartRefreshText(String startRefreshText) {
            this.startRefreshText = startRefreshText;
        }

        public int getProgressBarWidthSize() {
            return progressBarWidthSize;
        }

        public void setProgressBarWidthSize(int progressBarWidthSize) {
            this.progressBarWidthSize = progressBarWidthSize;
        }

        public int getProgressBarHeightSize() {
            return progressBarHeightSize;
        }

        public void setProgressBarHeightSize(int progressBarHeightSize) {
            this.progressBarHeightSize = progressBarHeightSize;
        }

        public int getSetContainerBackgroundColor() {
            return setContainerBackgroundColor;
        }

        public void setSetContainerBackgroundColor(int setContainerBackgroundColor) {
            this.setContainerBackgroundColor = setContainerBackgroundColor;
        }

        public int getSetTextColor() {
            return setTextColor;
        }

        public void setSetTextColor(int setTextColor) {
            this.setTextColor = setTextColor;
        }

        public void setLeftPadding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
            this.leftPadding = leftPadding;
            this.topPadding = topPadding;
            this.rightPadding = rightPadding;
            this.bottomPadding = bottomPadding;
        }

        public int getLeftPadding() {
            return leftPadding;
        }

        public int getTopPadding() {
            return topPadding;
        }

        public int getRightPadding() {
            return rightPadding;
        }

        public int getBottomPadding() {
            return bottomPadding;
        }

        public String getPullDownText() {
            return pullDownText;
        }

        public void setPullDownText(String pullDownText) {
            this.pullDownText = pullDownText;
        }

        public String getPullUpText() {
            return pullUpText;
        }

        public void setPullUpText(String pullUpText) {
            this.pullUpText = pullUpText;
        }
    }
}
