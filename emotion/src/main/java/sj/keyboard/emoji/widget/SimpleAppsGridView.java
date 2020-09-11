package sj.keyboard.emoji.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.keyboard.view.R;

import java.util.ArrayList;

import sj.keyboard.adpater.AppsAdapter;
import sj.keyboard.emoji.common.data.AppBean;

public class SimpleAppsGridView extends RelativeLayout {

    protected View view;

    public SimpleAppsGridView(Context context) {
        this(context, null);
    }

    public SimpleAppsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.emotion_view_apps, this);
        init();
    }

    protected void init() {
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        ArrayList<AppBean> mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.mipmap.mei_input_emoji, "emoji"));
        AppsAdapter adapter = new AppsAdapter(getContext(), mAppBeanList);
        gv_apps.setAdapter(adapter);
    }
}
