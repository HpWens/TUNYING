package com.tunyin.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by yiang on 2018/4/9.
 */

public class ImageUtil {

    private RequestBuilder<Bitmap> bitmapBuilder;
    private RequestBuilder<Drawable> requestBuilder;
    private RequestOptions options;


    private ImageUtil(String url, boolean asBitmap) {
        bitmapBuilder = Glide.with(AppContext.getContext()).asBitmap().load(url);
        options = new RequestOptions();
    }

    private ImageUtil(String url) {
        requestBuilder = Glide.with(AppContext.getContext()).load(url);
        options = new RequestOptions();
    }

    private ImageUtil(int resId) {
        requestBuilder = Glide.with(AppContext.getContext()).load(resId);
        options = new RequestOptions();
    }

    public static ImageUtil load(String url) {
//        if (url != null) {
//            url = url.replace("http://p655k0tfe.bkt.clouddn.com", "http://image.topyuezi.cn");
//        }
        return new ImageUtil(url);
    }

    public static ImageUtil load(String url, boolean asBitmap) {
        if (url != null) {
            url = url.replace("http://p655k0tfe.bkt.clouddn.com", "http://image.topyuezi.cn");
        }
        return new ImageUtil(url, true);
    }

    public static ImageUtil load(int resId) {
        return new ImageUtil(resId);
    }


    /**
     * 设置原型图片
     *
     * @return
     */
    public ImageUtil isCircle() {
        options = options.circleCrop();
        return this;
    }


    /**
     * 设置原型图片
     *
     * @return
     */
    public ImageUtil withoutCache() {
        options = options.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        return this;
    }

    public ImageUtil resize(int width, int hight) {
        options = options.override(width, hight);
        return this;
    }

    /**
     * 设置占位图
     *
     * @param resourceId 图片资源
     * @return
     */
    public ImageUtil placeholder(@DrawableRes int resourceId) {
        options = options.placeholder(resourceId);
        options = options.error(resourceId);
        return this;
    }

    /**
     * 设置加载失败的图
     *
     * @param resourceId 图片资源
     * @return
     */
    public ImageUtil error(@DrawableRes int resourceId) {
        options = options.error(resourceId);
        return this;
    }

    public void getBitmap(SimpleTarget<Bitmap> simpleTarget) {
        bitmapBuilder.apply(options).into(simpleTarget);
    }

    public void horizontalAndInto(final ImageView imageView) {
//        options = options.transforms(new RotateTransformation(rotateRotationAngle));
        bitmapBuilder.apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        Bitmap bitmap;
                        if (width < height) {
                            Matrix matrix = new Matrix();
                            matrix.postRotate(-90f);
                            bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
                        } else {
                            bitmap = resource;
                        }
                        imageView.setImageBitmap(bitmap);
                    }
                });
    }

    public void on(ImageView imageView) {
        if (imageView == null) return;
        requestBuilder.apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }
}
