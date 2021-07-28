package com.wind.addressdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ItemView extends LinearLayout {
    private ViewGroup mcontentView;
    private ViewGroup mRightView;
    private int rightViewWidth;
    private static final int RIGHT_VIEW_WIDTH = 100;
    public ItemView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        float density = getResources().getDisplayMetrics().density;
        rightViewWidth = (int) (RIGHT_VIEW_WIDTH * density);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mcontentView = (ViewGroup) getChildAt(0);
        mRightView = (ViewGroup) getChildAt(1);
        //设置contentview宽度
        int width = getMeasuredWidth();
        mcontentView.getLayoutParams().width = width;
        //设置右侧view的宽度
        MarginLayoutParams lp = (MarginLayoutParams) mRightView.getLayoutParams();
        switch (lp.width)
        {
            case ViewGroup.LayoutParams.MATCH_PARENT:
            case ViewGroup.LayoutParams.WRAP_CONTENT:
                int count = mRightView.getChildCount();
                if(count > 1)
                    rightViewWidth = rightViewWidth * count;
                lp.width = rightViewWidth;
                break;
        }
        super.onLayout(changed, l, t, r, b);
    }
}
