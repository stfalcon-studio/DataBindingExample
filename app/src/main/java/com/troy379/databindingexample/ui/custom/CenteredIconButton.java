package com.troy379.databindingexample.ui.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by troy379 on 16.03.16.
 */
public class CenteredIconButton extends AppCompatButton {

    private static final int LEFT = 0, TOP = 1, RIGHT = 2, BOTTOM = 3;

    private Rect textBounds = new Rect();
    private Rect drawableBounds = new Rect();

    public CenteredIconButton(Context context) {
        this(context, null);
    }

    public CenteredIconButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public CenteredIconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (!changed) return;

        final CharSequence text = getText();
        if (!TextUtils.isEmpty(text)) {
            TextPaint textPaint = getPaint();
            textPaint.getTextBounds(text.toString(), 0, text.length(), textBounds);
        } else {
            textBounds.setEmpty();
        }

        final int width = getWidth() - (getPaddingLeft() + getPaddingRight());

        final Drawable[] drawables = getCompoundDrawables();

        if (drawables[LEFT] != null) {
            drawables[LEFT].copyBounds(drawableBounds);
            int leftOffset =
                    (width - (textBounds.width() + drawableBounds.width()) + getRightPaddingOffset()) / 2 - getCompoundDrawablePadding();
            drawableBounds.offset(leftOffset, 0);
            drawables[LEFT].setBounds(drawableBounds);
        }

        if (drawables[RIGHT] != null) {
            drawables[RIGHT].copyBounds(drawableBounds);
            int rightOffset =
                    ((textBounds.width() + drawableBounds.width()) - width + getLeftPaddingOffset()) / 2 + getCompoundDrawablePadding();
            drawableBounds.offset(rightOffset, 0);
            drawables[RIGHT].setBounds(drawableBounds);
        }
    }
}
