package com.example.wind.minstory.base;

/**
 * Created by caption on 2015/8/18.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自定义listView，解决ScrollView嵌套listview只显示一行半
 *
 * @author Caption
 *
 */
public class ZYListView extends ListView {

    public ZYListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ZYListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZYListView(Context context) {
        super(context);
    }

    /**
     * 其中onMeasure函数决定了组件显示的高度与宽度；
     * makeMeasureSpec函数中第一个函数决定布局空间的大小，第二个参数是布局模式
     * MeasureSpec.AT_MOST的意思就是子控件需要多大的控件就扩展到多大的空间
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}