package com.luck.picture.lib.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.luck.picture.lib.tools.BitmapUtils;

import java.util.ArrayList;

public class EditService {

    private volatile static EditService singleton = null;

    private SparseArray<Bitmap> editBitmapArray = new SparseArray<>();

    private EditService() {
    }

    public static EditService getInstance() {
        if (singleton == null) {
            synchronized (EditService.class) {
                if (singleton == null) {
                    singleton = new EditService();
                }
            }
        }
        return singleton;
    }

    public void clearData() {
        editBitmapArray.clear();
    }

    /**
     * @param key
     * @param path
     */
    public void addEditBitmap(Context context, int key, String path, int scaleMode) {
        int width = BitmapUtils.getScreenWidth(context);
        int height;
        switch (scaleMode) {
            default:
            case 0:
                height = width * 4 / 3;
                break;
            case 1:
                height = width * 9 / 16;
                break;
            case 2:
                height = width;
                break;
        }
        try {
            Bitmap editBitmap = BitmapUtils.getFixedBitmap(context, width, height, path);
            editBitmapArray.put(key, editBitmap);
        } catch (Exception e) {
        }
    }

    public SparseArray<Bitmap> getEditBitmapArray() {
        return editBitmapArray;
    }

}
