package com.meis.base.mei.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.meis.base.R;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {

    private ImageView mIcon;
    private Context mContext;
    private int mTabPosition = -1;

    private ValueAnimator scaleAnim;

    public BottomBarTab(Context context, @DrawableRes int icon, boolean isCenter) {
        this(context, null, icon, isCenter);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, boolean isCenter) {
        this(context, attrs, 0, icon, isCenter);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, boolean isCenter) {
        super(context, attrs, defStyleAttr);
        init(context, icon, isCenter);
    }

    private void init(Context context, int icon, boolean isCenter) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, isCenter ? 40 : 27, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        mIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        // mIcon.setColorFilter(ContextCompat.getColor(context, R.color.bottom_tab_unselect));
        addView(mIcon);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            if (mTabPosition == 0) {
                mIcon.setImageResource(R.mipmap.nav_bottom_home_hl);
            } else if (mTabPosition == 1) {
                mIcon.setImageResource(R.mipmap.nav_bottom_more_normal);
            } else if (mTabPosition == 2) {
                mIcon.setImageResource(R.mipmap.nav_bottom_mine_hl);
            }
        } else {
            if (mTabPosition == 0) {
                mIcon.setImageResource(R.mipmap.nav_bottom_home_normal);
            } else if (mTabPosition == 1) {
                mIcon.setImageResource(R.mipmap.nav_bottom_more_normal);
            } else if (mTabPosition == 2) {
                mIcon.setImageResource(R.mipmap.nav_bottom_mine_normal);
            }
        }
//        if (selected) {
//            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.bottom_tab_select));
//        } else {
//            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.bottom_tab_unselect));
//        }

        if (selected) {
            scaleAnim = ValueAnimator.ofFloat(1.0f, 1.2f, 1.0f);
            scaleAnim.setInterpolator(new DecelerateInterpolator());
            scaleAnim.setDuration(400);
            scaleAnim.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                mIcon.setScaleX(value);
                mIcon.setScaleY(value);
            });
            scaleAnim.start();
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
