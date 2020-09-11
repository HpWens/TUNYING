package com.meis.base.mei.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.meis.base.mei.entity.ErrorApi;
import com.meis.base.mei.entity.Result;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.ApiResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ParseJsonUtils {

    public static String toJson(Object object) {
        try {
            return new Gson().toJson(object);
        } catch (Exception e) {
        }
        return "";
    }

    public static <T> T parseData(String json, Class<T> t) {
        return new Gson().fromJson(json, t);
    }

    public static <T> Result<T> parseDataToResult(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return new Result<>();
        }
        try {
            Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
            return new Gson().fromJson(json, type);
        } catch (Exception e) {
            return new Result<>();
        }
    }

    public static <T> ApiResult<T> parseDataToApiResult(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(ApiResult.class, new Class[]{clazz});
        return new Gson().fromJson(json, type);
    }

    public static <T> List<T> parseListData(String json, Class<T> clazz) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        try {
            return new Gson().fromJson(json, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static <T> List<T> parseListData(String json, String field, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return new ArrayList<T>();
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(field)) {
                return parseListData(jsonObject.optString(field), clazz);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    public static <T> ArrayList<T> parseArrayListData(String json, Class<T> clazz) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        return new Gson().fromJson(json, listType);
    }

    public static String parseFieldData(String json, String fieldName) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(fieldName)) {
                return jsonObject.optString(fieldName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    // fork
    public static void handlerApiError(ApiException apiException, OnApiErrorListener listener) {
        if (apiException == null) return;
        // fork 后台接口封装的错误
        if (apiException.getCode() == 500) {
            if (apiException.getCause() != null && apiException.getCause() instanceof HttpException) {
                HttpException exception = (HttpException) apiException.getCause();
                ResponseBody responseBody = exception.response().errorBody();
                if (responseBody != null) {
                    Observable.just(responseBody.byteStream())
                            .filter(inputStream -> inputStream != null)
                            .observeOn(Schedulers.io())
                            .map(inputStream -> {
                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                int read;
                                while ((read = inputStream.read()) != -1) {
                                    bos.write(read);
                                }
                                String str = bos.toString();
                                inputStream.close();
                                bos.close();
                                return str;
                            }).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> {
                                if (!TextUtils.isEmpty(s)) {
                                    try {
                                        ErrorApi errorApi = parseData(s, ErrorApi.class);
                                        if (!TextUtils.isEmpty(errorApi.display)) {
                                            listener.onError(errorApi.display);
                                        }
                                    } catch (Exception e) {
                                        listener.onError(s);
                                    }
                                } else {
                                    listener.onError(s);
                                }
                            });
                }
            }
        } else if (apiException.getCode() == 401) {
            listener.onJumpLogin();
        } else {
            if (!TextUtils.isEmpty(apiException.getMessage())) {
                listener.onError(apiException.getMessage());
            }
        }
    }

    public interface OnApiErrorListener {
        void onError(String error);

        void onJumpLogin();
    }


}
