package com.example.wind.minstory.view;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wind.minstory.R;
import com.example.wind.minstory.base.BaseAlertDialog;
import com.example.wind.minstory.utils.CornerUtils;

/**
 * Created by wind on 2016/4/22.
 */
public class LoginDialog extends BaseAlertDialog<LoginDialog> {
    /**
     * title underline
     */
    private View mVLineTitle;
    /**
     * vertical line between btns
     */
    private View mVLineVertical;
    /**
     * vertical line between btns
     */
    private View mVLineVertical2;
    /**
     * horizontal line above btns
     */
    private View mVLineHorizontal;
    /**
     * title underline color(标题下划线颜色)
     */
    private int mTitleLineColor = Color.parseColor("#61AEDC");
    /**
     * title underline height(标题下划线高度)
     */
    private float mTitleLineHeight = 1f;
    /**
     * btn divider line color(对话框之间的分割线颜色(水平+垂直))
     */
    private int mDividerColor = Color.parseColor("#DCDCDC");

    public static final int STYLE_ONE = 0;
    public static final int STYLE_TWO = 1;
    private int mStyle = STYLE_ONE;
    private Context context;

    public LoginDialog(Context context) {
        super(context);
        this.context = context;
        /** default value*/
        mTitleTextColor = Color.parseColor("#61AEDC");
        mTitleTextSize = 22f;
        mContentTextColor = Color.parseColor("#383838");
        mContentTextSize = 17f;
        mLeftBtnTextColor = Color.parseColor("#8a000000");
        mRightBtnTextColor = Color.parseColor("#8a000000");
        mMiddleBtnTextColor = Color.parseColor("#8a000000");
        /** default value*/
    }

    @Override
    public View onCreateView() {
        /** title */
        LinearLayout titlell = new LinearLayout(context);
        titlell.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        titlell.setOrientation(LinearLayout.HORIZONTAL);
        mTvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ImageView imgtitle = new ImageView(context);
        imgtitle.setLayoutParams(new ViewGroup.LayoutParams(dip2px(context, 35), LinearLayout.LayoutParams.MATCH_PARENT));
        imgtitle.setImageResource(R.mipmap.ic_person);
        titlell.addView(imgtitle);
        titlell.addView(mTvTitle);
        mLlContainer.addView(titlell);

        /** title underline */
        mVLineTitle = new View(mContext);
        mLlContainer.addView(mVLineTitle);

        //线
        mVLineHorizontal = new View(mContext);
        mVLineHorizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));



        /** content */
        LinearLayout mEditLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams mEditParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dip2px(context, 35));
        mEditParams.setMargins(20, 10, 20, 10);
        mEditLinearLayout.setLayoutParams(mEditParams);
        mEditLinearLayout.setOrientation(LinearLayout.HORIZONTAL);


        TextView account = new TextView(context);
        ViewGroup.LayoutParams accountparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        account.setLayoutParams(accountparams);
        account.setText("账号:");

        LinearLayout.LayoutParams mIDparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                dip2px(context, 40));

        mID.setLayoutParams(mIDparams);
        account.setTextSize(18);
        mEditLinearLayout.addView(account);
        mEditLinearLayout.addView(mID);

        mLlContainer.addView(mEditLinearLayout);



        LinearLayout passLinearLayout = new LinearLayout(context);
        passLinearLayout.setLayoutParams(mEditParams);

        mPassword.setLayoutParams(mIDparams);
        TextView passTV = new TextView(context);
        passTV.setLayoutParams(accountparams);

        passTV.setText("密码:");
        passTV.setTextSize(18);
        passLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        passLinearLayout.addView(passTV);
        passLinearLayout.addView(mPassword);
        mLlContainer.addView(passLinearLayout);



        mLlContainer.addView(mVLineHorizontal);

        /** btns */
        mTvBtnLeft.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLlBtns.addView(mTvBtnLeft);

        mVLineVertical = new View(mContext);
        mVLineVertical.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        mLlBtns.addView(mVLineVertical);

        mTvBtnMiddle.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLlBtns.addView(mTvBtnMiddle);

        mVLineVertical2 = new View(mContext);
        mVLineVertical2.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        mLlBtns.addView(mVLineVertical2);

        mTvBtnRight.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLlBtns.addView(mTvBtnRight);

        mLlContainer.addView(mLlBtns);

        return mLlContainer;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();

        /** title */
        if (mStyle == STYLE_ONE) {
            mTvTitle.setMinHeight(dp2px(48));
            mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
            mTvTitle.setPadding(dp2px(15), dp2px(5), dp2px(0), dp2px(5));
            mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);
        } else if (mStyle == STYLE_TWO) {
            mTvTitle.setGravity(Gravity.CENTER);
            mTvTitle.setPadding(dp2px(0), dp2px(15), dp2px(0), dp2px(0));
        }

        /** title underline */
        mVLineTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                dp2px(mTitleLineHeight)));
        mVLineTitle.setBackgroundColor(mTitleLineColor);
        mVLineTitle.setVisibility(mIsTitleShow && mStyle == STYLE_ONE ? View.VISIBLE : View.GONE);

        /** content */
        if (mStyle == STYLE_ONE) {
            mID.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
            mID.setMinHeight(dp2px(68));
            mID.setGravity(mContentGravity);

            mPassword.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
            mPassword.setMinHeight(dp2px(68));
            mPassword.setGravity(mContentGravity);
        } else if (mStyle == STYLE_TWO) {
            mID.setPadding(dp2px(15), dp2px(7), dp2px(15), dp2px(20));
            mID.setMinHeight(dp2px(56));
            mID.setGravity(Gravity.CENTER);

            mPassword.setPadding(dp2px(15), dp2px(7), dp2px(15), dp2px(20));
            mPassword.setMinHeight(dp2px(56));
            mPassword.setGravity(Gravity.CENTER);
        }

        /** btns */
        mVLineHorizontal.setBackgroundColor(mDividerColor);
        mVLineVertical.setBackgroundColor(mDividerColor);
        mVLineVertical2.setBackgroundColor(mDividerColor);

        if (mBtnNum == 1) {
            mTvBtnLeft.setVisibility(View.GONE);
            mTvBtnRight.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
            mVLineVertical2.setVisibility(View.GONE);
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
        }

        /**set background color and corner radius */
        float radius = dp2px(mCornerRadius);
        mLlContainer.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));
        mTvBtnLeft.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, 0));
        mTvBtnRight.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, 1));
        mTvBtnMiddle.setBackgroundDrawable(CornerUtils.btnSelector(mBtnNum == 1 ? radius : 0, mBgColor, mBtnPressColor, -1));
    }

    // --->属性设置

    /**
     * set style(设置style)
     */
    public LoginDialog style(int style) {
        this.mStyle = style;
        return this;
    }

    /**
     * set title underline color(设置标题下划线颜色)
     */
    public LoginDialog titleLineColor(int titleLineColor) {
        this.mTitleLineColor = titleLineColor;
        return this;
    }

    /**
     * set title underline height(设置标题下划线高度)
     */
    public LoginDialog titleLineHeight(float titleLineHeight_DP) {
        this.mTitleLineHeight = titleLineHeight_DP;
        return this;
    }

    /**
     * set divider color between btns(设置btn分割线的颜色)
     */
    public LoginDialog dividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        return this;
    }

    //DP转px
    public static int dip2px(Context context, float dipValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dipValue * scale + 0.5f);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
