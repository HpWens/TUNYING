package com.meis.base.mei.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class HomeViewpager extends ViewPager {

    private float x1;

    private boolean isScroll = true;

    public HomeViewpager(@NonNull Context context) {
        super(context);
    }

    public HomeViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                x1 = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int curPosition = getCurrentItem();
                float x2 = ev.getX();
                if (curPosition == 0) {
                    if ((x2 - x1) > 50) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else if (curPosition == (getAdapter().getCount() - 1)) {
                    if ((x1 - x2) > 50) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}
