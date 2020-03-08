package com.tunyin.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tunyin.R;

public class RankProgressView extends FrameLayout {

    private TextView childView0;
    private TextView childView1;
    private TextView childView2;

    int margin;

    Paint paint;

    float progress = 0.5f;

    Bitmap pointBitmap;

    int childWidth;
    int childHeight;

    int child1Width;
    boolean isLeft = true;

    int offsetHeight;

    public RankProgressView(Context context) {
        this(context, null);
    }

    public RankProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RankProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        margin = dip2px(32);

        pointBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_point);

        childView0 = new TextView(context);
        childView0.setTextColor(Color.parseColor("#fe9031"));
        childView0.getPaint().setFakeBoldText(true);
        childView0.setBackgroundResource(R.mipmap.ic_kuang_bg);
        childView0.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        int padding = dip2px(4);
        childView0.setTextSize(14);
        childView0.setText("0.00");
        childView0.setGravity(Gravity.CENTER);
        childView0.setPadding(padding, padding, padding, 2 * padding);
        addView(childView0);

        childView2 = new TextView(context);
        childView2.setTextColor(Color.parseColor("#6FFFFFFF"));
        childView2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        childView2.setTextSize(12);
        childView2.setText("距离下一级还差0元");
        addView(childView2);

        childView1 = new TextView(context);
        childView1.setTextColor(Color.parseColor("#FFFFFF"));
        childView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        childView1.setTextSize(18);
        childView1.getPaint().setFakeBoldText(true);
        childView1.setText("财富值");
        addView(childView1);
        childView1.post(new Runnable() {
            @Override
            public void run() {
                child1Width = childView1.getWidth();
                childWidth = childView0.getWidth();
                childHeight = childView0.getHeight();
                requestLayout();
            }
        });

        offsetHeight = dp2px(8);
    }

    public void refreshProgress(int current, int total, int grade) {
        if (current == 0 || total == 0) {
            isLeft = false;
            progress = 0;
        } else {
            progress = (float) current / total;
            isLeft = progress > 0.5;
        }
        if (grade < 15) {
            childView0.setTextColor(Color.parseColor("#fe9031"));
        } else if (grade < 30) {
            childView0.setTextColor(Color.parseColor("#558ba5"));
        } else if (grade < 45) {
            childView0.setTextColor(Color.parseColor("#d058c4"));
        } else if (grade < 100) {
            childView0.setTextColor(Color.parseColor("#3f48cc"));
        }
        childView0.setText("" + current);
        childView2.setText("距离下一级还差" + total + "元");
        childView2.post(new Runnable() {
            @Override
            public void run() {
                childWidth = childView0.getWidth();
                childHeight = childView0.getHeight();
                requestLayout();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();

        int left = dip2px(32);
        int top = height / 2 - dip2px(4) + offsetHeight;
        int right = width - dip2px(32);
        int bottom = height / 2 + dip2px(4) + offsetHeight;

        RectF rectF = new RectF(left, top, right, bottom);
        paint.setColor(Color.parseColor("#36000000"));
        canvas.drawRoundRect(rectF, dip2px(4), dip2px(4), paint);

        int total = width - 2 * dip2px(32);

        int currentWidth = (int) (total * progress);
        RectF currentRectF = new RectF(left, top, left + currentWidth, bottom);
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawRoundRect(currentRectF, dip2px(4), dip2px(4), paint);

        int x = left + currentWidth - pointBitmap.getWidth() / 2;
        int y = bottom - dip2px(4) - pointBitmap.getHeight() / 2;

        canvas.drawBitmap(pointBitmap, x, y, paint);

        int circleX = x + pointBitmap.getWidth() / 2;
        int circleY = y + pointBitmap.getHeight() / 2;

        int childX = circleX - childWidth / 2;
        int childY = circleY - childHeight - pointBitmap.getHeight() / 2 + dip2px(4);

        childView0.setX(childX);
        childView0.setY(childY);

        childView2.setX(left);
        childView2.setY(top + dip2px(16));

        childView1.setX(isLeft ? left : right - child1Width);
        childView1.setY(top - dip2px(32));
    }

    /**
     * dip转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dip2px(float dpValue) {
        return dp2px(dpValue);
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
