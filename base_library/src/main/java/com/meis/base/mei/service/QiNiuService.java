package com.meis.base.mei.service;

import com.meis.base.mei.constant.DataConstants;
import com.meis.base.mei.entity.PicEntry;
import com.meis.base.mei.utils.qiniu.Auth;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.vondear.rxtool.RxTimeTool;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class QiNiuService {

    private volatile static QiNiuService singleton = null;
    private static UploadManager uploadManager;
    private static String uploadToken;

    private QiNiuService() {
    }

    //图片名称
    public static final String getPictureName() {
        return RxTimeTool.getCurrentDateTime(DATE_FORMAT_LINK) + "_" + new Random().nextInt(1000);
    }

    //Date格式
    public static final String DATE_FORMAT_LINK = "yyyyMMddHHmmssSSS";

    public static QiNiuService getInstance() {
        if (singleton == null) {
            synchronized (QiNiuService.class) {
                if (singleton == null) {
                    singleton = new QiNiuService();
                    uploadManager = new UploadManager(new Configuration.Builder().zone(FixedZone.zone2).build());
                    uploadToken = Auth.create(DataConstants.QiNiu.AccessKey, DataConstants.QiNiu.SecretKey).uploadToken(DataConstants.QiNiu.BUCKET_NAME);
                }
            }
        }
        return singleton;
    }

    /**
     * @param path
     * @param listener
     */
    public void uploadSingleFile(String path, final OnUploadListener listener) {
        if (singleton != null) {
            File uploadFile = new File(path);
            uploadManager.put(uploadFile, getPictureName(), uploadToken, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    if (info.isOK()) {
                        if (listener != null) listener.onSuccess(DataConstants.QiNiu.DOMAIN + key);
                    } else {
                        if (listener != null) listener.onFailure(info.error);
                    }
                }
            }, null);
        }
    }

    /**
     * @param data
     * @param listener
     */
    public void uploadMultiPic(final List<Object> data, final OnPublishUploadListener listener) {
        List<PicEntry> picEntryList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) instanceof PicEntry) {
                PicEntry picEntity = (PicEntry) data.get(i);
                picEntity.position = i;
                picEntryList.add(picEntity);
            }
        }
        if (picEntryList.isEmpty()) {
            listener.onSuccess(data);
            return;
        }
        List<Integer> posList = new ArrayList<>();
        Observable.fromIterable(picEntryList)
                .concatMap((Function<PicEntry, ObservableSource<PicEntry>>) picEntity -> Observable.create((ObservableOnSubscribe<PicEntry>) emitter -> {
                    File uploadFile = new File(picEntity.path);
                    ResponseInfo responseInfo = uploadManager.syncPut(uploadFile, getPictureName(), uploadToken, null);
                    if (responseInfo.isOK()) {
                        String path = DataConstants.QiNiu.DOMAIN + responseInfo.response.optString("key");
                        picEntity.httpPath = path;
                        emitter.onNext(picEntity);
                        emitter.onComplete();
                    } else {
                        // 上传失败
                        emitter.onError(new IOException(responseInfo.error));
                    }
                }).subscribeOn(Schedulers.io())).observeOn(AndroidSchedulers.mainThread()).subscribe(picEntity -> {
            data.set(picEntity.position, picEntity);
            posList.add(picEntity.position);
            if (posList.size() == picEntryList.size()) {
                listener.onSuccess(data);
            }
        }, throwable -> listener.onFailure(throwable.getMessage()));
    }


    public interface OnUploadListener {

        void onSuccess(String key);

        void onFailure(String error);
    }

    /**
     * 发布回调接口
     */
    public interface OnPublishUploadListener {

        void onSuccess(List<Object> list);

        void onFailure(String error);
    }

}
