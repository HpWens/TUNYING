package com.tunyin.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tunyin.R;
import com.tunyin.utils.ImageUtil;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/12/1
 * desc:
 */
public class BannerViewHolder implements MZViewHolder<String> {
    private CustomRoundAngleImageView mImageView;

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_banner_item, null);
        mImageView = (CustomRoundAngleImageView) view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        // 数据绑定

        ImageUtil.load(data).on(mImageView);
//        Glide.with(context)
//                .load(data)
//                .into(mImageView);


//        Glide.with(context)
//                .load(data)
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .bitmapTransform(new RoundedCornersTransformation(context, 5, 0))
//                .dontAnimate()
//                .into(mImageView);
    }
}
