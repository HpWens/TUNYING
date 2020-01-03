package com.tunyin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.tunyin.R;
import com.tunyin.listener.OnBannerClickListener;
import com.tunyin.listener.OnPageSelectedListener;
import com.tunyin.utils.ImageUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

/**
 * Created by yiang on 2018/4/8.
 */

public class BannerView extends Banner {

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void showBanner(List<String> list) {
        setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        setImageLoader(imageLoad).setImages(list).start();
    }

    public void setItemClickListener(OnBannerClickListener listener) {
        if (listener == null) return;
        setOnBannerListener(listener);
    }


    ImageLoader imageLoad = new ImageLoader() {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageUtil.load((String) path).on(imageView);
        }
    };

    public void release() {
        stopAutoPlay();
        setOnPageChangeListener(null);
        releaseBanner();
    }

    public void setPageSelectedListener(final OnPageSelectedListener listener){
        setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listener.onSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
