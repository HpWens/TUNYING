package com.tunyin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by yiang on 2018/4/9.
 */

public class DataCacheUtil {


    public final String SP_NAME = "pretty_mom";

    private SharedPreferences prefs;



    private DataCacheUtil() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        prefs = AppContext.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

    }
    public void clean() {
        prefs.edit().clear().apply();
    }

    private static class DataCacheHolder {
        private static final DataCacheUtil INSTANCE = new DataCacheUtil();
    }

    /**
     * 获取当前类单例
     *
     * @return
     */
    public static DataCacheUtil getInstance() {
        return DataCacheHolder.INSTANCE;
    }

    public void setBooleanValue(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public void setStringValue(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public void setIntVale(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }

    public void setFloatVale(String key, float value) {
        prefs.edit().putFloat(key, value).apply();
    }

    public void setLongVale(String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }

    public boolean getBooleanValue(String key) {
        return prefs.getBoolean(key, false);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public String getStringValue(String key) {
        return prefs.getString(key, "");
    }

    public String getStringValue(String key, String defaultStr) {
        return prefs.getString(key, defaultStr);
    }


    public int getIntVale(String key) {
        return prefs.getInt(key, -1);
    }

    public int getIntVale(String key, int defaultInd) {
        return prefs.getInt(key, defaultInd);
    }

    public float getFloatVale(String key) {
        return prefs.getFloat(key, -1f);
    }

    public Long getLongVale(String key) {
        return prefs.getLong(key, -1l);
    }


    public void setObject(String key , Object obj){
        try {
            // 保存对象
            SharedPreferences.Editor sharedata = prefs.edit();
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream os=new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(obj);
            //将序列化的数据转为16进制保存
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            sharedata.putString(key, bytesToHexString);
            sharedata.commit();
        } catch (IOException e) {
            e.printStackTrace();
//            Log.e("", "保存obj失败");
        }
    }

    /**
     * desc:获取保存的Object对象
     * @param key
     * @return
     * modified:
     */
    public Object getObject(String key ){
        try {
            SharedPreferences sharedata = prefs;
            if (sharedata.contains(key)) {
                String string = sharedata.getString(key, "");
                if(TextUtils.isEmpty(string)){
                    return null;
                }else{
                    //将16进制的数据转为数组，准备反序列化
                    byte[] stringToBytes = StringToBytes(string);
                    ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
                    ObjectInputStream is=new ObjectInputStream(bis);
                    //返回反序列化得到的对象
                    Object readObject = is.readObject();
                    return readObject;
                }
            }
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return null;

    }

    /**
     * desc:将数组转为16进制
     * @param bArray
     * @return
     * modified:
     */
    private static String bytesToHexString(byte[] bArray) {
        if(bArray == null){
            return null;
        }
        if(bArray.length == 0){
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * desc:将16进制的数据转为数组

     * @param data
     * @return
     * modified:
     */
    private static byte[] StringToBytes(String data){
        String hexString=data.toUpperCase().trim();
        if (hexString.length()%2!=0) {
            return null;
        }
        byte[] retData=new byte[hexString.length()/2];
        for(int i=0;i<hexString.length();i++)
        {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
            else if(hex_char2 >= 'A' && hex_char2 <='F')
                int_ch2 = hex_char2-55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1+int_ch2;
            retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }
}
