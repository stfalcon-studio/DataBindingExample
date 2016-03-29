package com.troy379.databindingexample.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by troy379 on 16.03.16.
 */
public class SquareRelativeLayout extends RelativeLayout {

    public SquareRelativeLayout(Context context) {
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
