package com.tunyin.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

/**
 * create by wangrongchao
 * on 2019/11/14
 **/
public class ImagePickHelper {

    public static final int REQUEST_PICK_IMAGE = 0x0001;
    public final String FILEPROVIDER = "com.tunyin.fileprovider";

    private FragmentActivity context;
    private Fragment fragment;
    private PermissionHelper permissionHelper;

    private MediaStoreCompat mMediaStoreCompat;
    private static final int REQUEST_CODE_CAPTURE = 24;

    private ImagePickHelper(FragmentActivity activity) {
        context = activity;
        permissionHelper = new PermissionHelper();
    }

    private ImagePickHelper(Fragment fragment) {
        this.fragment = fragment;
        context = fragment.getActivity();
        permissionHelper = new PermissionHelper();
    }

    public static ImagePickHelper with(FragmentActivity activity) {
        return new ImagePickHelper(activity);
    }

    public static ImagePickHelper with(Fragment fragment) {
        return new ImagePickHelper(fragment);
    }

    /**
     * 选择图片，首先判断下是否有访问内存卡的权限
     *
     * @param num
     */
    private void pickPicture(final int num, final boolean takePhotoDirect) {

        permissionHelper.requestSDAndCameraPermissions(context, new PermissionHelper.OnRequestPermissionResult() {
            @Override
            public void onResult(boolean isSuccess) {
                if (isSuccess) {
                    doPick(num, takePhotoDirect);
                }
            }
        });
    }

    private void doPick(int num, boolean takePhotoDirect) {

//        Matisse.from(context)
//             .setStatusIsDark(true)   // 按需设置状态栏文字颜色
//                .theme(R.style.Matisse_Default)    // 设置成所需主题
        Matisse.from(context)
                .choose(MimeType.ofImage())
//                .showSingleMediaType(true)
                .countable(true)
                .maxSelectable(num)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, FILEPROVIDER))
                .gridExpectedSize(DimensionUtil.dip2px(120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.75f)
                .imageEngine(new MyGlideEngine())
//                            .theme(R.style.Matisse_Dracula)
                .forResult(REQUEST_PICK_IMAGE);

//        Matisse.from(context)
//                .choose(MimeType.ofImage())
//                .countable(true)
//                .maxSelectable(num)
//                .gridExpectedSize(DimensionUtil.dip2px(120))
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                .thumbnailScale(0.85f)
//                .imageEngine(new GlideEngine())
//                .forResult(REQUEST_PICK_IMAGE);
    }

    private void doPickVideo(int num, boolean takePhotoDirect) {
        Matisse.from(context)
                .choose(MimeType.ofVideo())
                .showSingleMediaType(true)
                .countable(true)
                .maxSelectable(num)
                .capture(false)
//                .takePhotoDirect(takePhotoDirect)
                .captureStrategy(
                        new CaptureStrategy(true, FILEPROVIDER))
                .gridExpectedSize(DimensionUtil.dip2px(120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.75f)
                .imageEngine(new MyGlideEngine())
//                            .theme(R.style.Matisse_Dracula)
                .forResult(REQUEST_PICK_IMAGE);
    }

    /**
     * 选择图片，首先判断下是否有访问内存卡的权限
     *
     * @param num
     */
    public void pickVideo(final int num) {
        permissionHelper.requestSDAndCameraPermissions(context, new PermissionHelper.OnRequestPermissionResult() {
            @Override
            public void onResult(boolean isSuccess) {
                if (isSuccess) {
                    doPickVideo(num, false);
                }

            }
        });
    }


    /**
     * 选择图片，首先判断下是否有访问内存卡的权限
     *
     * @param num
     */
    private void pickPictureInFragment(final int num) {
        permissionHelper.requestSDAndCameraPermissions(fragment.getActivity(), new PermissionHelper.OnRequestPermissionResult() {
            @Override
            public void onResult(boolean isSuccess) {
                if (isSuccess) {
                    Matisse.from(fragment)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(num)
                            .capture(true)
//                            .takePhotoDirect(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, FILEPROVIDER))
                            .gridExpectedSize(DimensionUtil.dip2px(120))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.75f)
                            .imageEngine(new MyGlideEngine())
//                            .theme(R.style.Matisse_Dracula)
                            .forResult(REQUEST_PICK_IMAGE);
                }
            }
        });
    }


    public void takePhoto() {
        pickPicture(1, true);
    }

    /**
     * 选择单张图片
     */
    public void pickSinglePicture() {
        pickPicture(1, false);
    }

    /**
     * 选择单张图片
     */
    public void pickSinglePictureInFragment() {
        pickPictureInFragment(1);
    }

    /**
     * 选择多张图片
     *
     * @param maxNum 设置做多可选择几张图片
     */
    public void pickMulPicture(int maxNum) {
        pickPicture(maxNum, false);
    }

    /**
     * 选择多张图片
     *
     * @param maxNum 设置做多可选择几张图片
     */
    public void pickMulPictureInFragment(int maxNum) {
        pickPictureInFragment(maxNum);
    }
}

