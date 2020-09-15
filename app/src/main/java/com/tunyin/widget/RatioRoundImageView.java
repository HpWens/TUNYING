package com.tunyin.widget;

import android.content.Context;
import android.util.AttributeSet;

public class RatioRoundImageView extends CustomRoundAngleImageView {
    public RatioRoundImageView(Context context) {
        super(context);
    }

    public RatioRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (widthSize * 0.75f);
        int heightSpec=MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.getMode(widthMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
