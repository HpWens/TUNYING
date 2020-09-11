package sj.keyboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class EmotionScrollView extends ScrollView {

    private float moveOffsetY;

    public EmotionScrollView(Context context) {
        super(context);
    }

    public EmotionScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmotionScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveOffsetY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (moveOffsetY - ev.getY());
                if (Math.abs(deltaY) > 10) {
                    if (onScrollListener != null) {
                        onScrollListener.onScrollStateChange(0);
                    }
                }
                moveOffsetY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChange(1);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private OnScrollListener onScrollListener;

    public interface OnScrollListener {
        void onScrollStateChange(int state);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        onScrollListener = listener;
    }
}
