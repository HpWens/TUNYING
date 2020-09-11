package com.meis.base.mei.widget.tag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vondear.rxtool.RxDeviceTool;

/**
 * Created by wenshi on 2019/3/12.
 * Description
 */
public class RandomDragTagLayout extends FrameLayout {

    public RandomDragTagLayout(Context context) {
        this(context, null);
    }

    public RandomDragTagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RandomDragTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = RxDeviceTool.getScreenWidth(getContext()) * 4 / 3;
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSpec = MeasureSpec.makeMeasureSpec(size, heightMode);
        super.onMeasure(widthMeasureSpec, hSpec);
    }

    /**
     * 添加标签
     *
     * @param text           标签文本
     * @param x              相对于父控件的x坐标百分比
     * @param y              相对于父控件的y坐标百分比
     * @param isShowLeftView 是否显示左侧标签
     */
    public boolean addTagView(String text, final float x, final float y, boolean isShowLeftView, RandomDragTagView.OnRandomDragListener listener) {
        if (text == null || text.equals("")) return false;
        RandomDragTagView tagView = new RandomDragTagView(getContext());
        addView(tagView, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tagView.initTagView(text, x * getWidth(), y * getHeight(), isShowLeftView);
        tagView.setOnRandomDragListener(listener);
        return true;
    }


}
