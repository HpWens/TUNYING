package com.luck.picture.lib.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author：luck
 * @date：2020-01-15 18:22
 * @describe：BitmapUtils
 */
public class BitmapUtils {

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 可以根据图片的平移缩放获取裁剪后的bitmap
     *
     * @param context
     * @param path
     * @return
     */
    public static Bitmap getFixedBitmap(Context context, int viewWidth, int viewHeight, String path) {
        path = FileUtilsToQ.Companion.getRealPath(context, path);
        Bitmap originBitmap = getCompressBitmap(context, path);
        if (originBitmap == null) {
            return Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
        }

        // 图片的固定宽度  高度
        int drawableWidth = originBitmap.getWidth();
        int drawableHeight = originBitmap.getHeight();

        // 将图片移动到屏幕的中点位置
        float dx = (viewWidth - drawableWidth) / 2;
        float dy = (viewHeight - drawableHeight) / 2;

        Matrix bitmapMatrix = new Matrix();
        bitmapMatrix.postTranslate(dx, dy);

        float scaleSize = 1.0F;
        if (drawableWidth >= viewWidth && drawableHeight >= viewHeight) {
            scaleSize = Math.max(viewHeight * 1.0f / drawableHeight, viewWidth * 1.0f / drawableWidth);
        } else if (drawableWidth > viewWidth && drawableHeight < viewHeight) {
            scaleSize = viewHeight * 1.0f / drawableHeight;
        } else if (drawableWidth < viewWidth && drawableHeight > viewHeight) {
            scaleSize = viewWidth * 1.0f / drawableWidth;
        } else {
            float sw = viewWidth * 1.0f / drawableWidth;
            float sh = viewHeight * 1.0f / drawableHeight;
            scaleSize = Math.max(sw, sh);
        }

        bitmapMatrix.postScale(scaleSize, scaleSize, viewWidth / 2, viewHeight / 2);

        Bitmap rectBitmap = Bitmap.createBitmap(originBitmap, 0, 0, drawableWidth, drawableHeight, bitmapMatrix, false);

        int rectWidth = rectBitmap.getWidth();
        int rectHeight = rectBitmap.getHeight();

        int x = 0;
        int y = 0;

        if (rectWidth > viewWidth) {
            x = (rectWidth - viewWidth) / 2;
        } else if (rectHeight > viewHeight) {
            y = (rectHeight - viewHeight) / 2;
        }

        return Bitmap.createBitmap(rectBitmap, x, y, rectWidth - 2 * x, rectHeight - 2 * y);
    }


    public static Bitmap getCompressBitmap(Context context, String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不加载到内存中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // 判定是否是横竖图
        boolean verEnable = options.outWidth < options.outHeight;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        options.inSampleSize = BitmapUtils.calculateInSampleSize(options, verEnable ? screenWidth : screenHeight, verEnable ? screenHeight : screenWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        int simple = 1;
        if (outHeight > maxHeight || outWidth > maxWidth) {
            int tempHeght = outHeight / 2;
            for (int tempWidth = outWidth / 2; tempHeght / simple > maxHeight && tempWidth / simple > maxWidth; simple *= 2) {
            }
        }
        return simple;
    }

    /**
     * 旋转Bitmap
     *
     * @param bitmap
     * @param angle
     * @return
     */
    public static Bitmap rotatingImage(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();

        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 判断拍照 图片是否旋转
     *
     * @param degree
     * @param path
     */
    public static void rotateImage(int degree, String path) {
        if (degree > 0) {
            try {
                // 针对相片有旋转问题的处理方式
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2;
                File file = new File(path);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
                bitmap = rotatingImage(bitmap, degree);
                if (bitmap != null) {
                    saveBitmapFile(bitmap, file);
                    bitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存Bitmap至本地
     *
     * @param bitmap
     * @param file
     */
    public static void saveBitmapFile(Bitmap bitmap, File file) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取旋转角度
     *
     * @param orientation
     * @return
     */
    public static int getRotationAngle(int orientation) {
        switch (orientation) {
            case 1:
                return 0;
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
        }
        return 0;
    }
}
