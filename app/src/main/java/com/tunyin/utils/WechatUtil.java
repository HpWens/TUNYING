package com.tunyin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tunyin.App;
import com.tunyin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 描述：微信分享、支付、登录授权的操作
 * 创建作者： hrh
 * 创建时间： 2018/11/6 15:49
 **/
public class WechatUtil {

    private LoadImageUrl loadImageUrl;
    private static IWXAPI iwxapi;

    private static WechatUtil ourInstance = new WechatUtil();

    public static WechatUtil getInstance() {
        return ourInstance;
    }

    void regiestWechat(String appid) {
        iwxapi = WXAPIFactory.createWXAPI(App.instance, appid, false);
        iwxapi.registerApp(appid);
    }

    /**
     * 微信支付
     *
     * @param partnerId    1
     * @param prepayId     1
     * @param packageValue 包名
     * @param nonceStr     16位随机数
     * @param timeStamp    时间戳
     * @param sign         签名
     */
    public void payOrder(Context context, String appid, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) {
        regiestWechat(appid);
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(context, "未安装微信，支付失败", Toast.LENGTH_SHORT).show();
        } else {
            PayReq request = new PayReq();

            request.appId = appid;

            request.partnerId = partnerId;

            request.prepayId = prepayId;

            request.packageValue = packageValue;

            request.nonceStr = nonceStr;

            request.timeStamp = timeStamp;

            request.sign = sign;

            iwxapi.sendReq(request);
        }
    }

    /**
     * 微信登录，监听到数据后通过eventbus返回到对应的界面
     */
    public boolean loginWechat(String appid) {
        regiestWechat(appid);
        if (iwxapi.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";//获取用户信息的授权域
            req.state = "wechat_sdk_login" + System.currentTimeMillis();
            iwxapi.sendReq(req);
            return true;
        }
        return false;
    }

    /**
     * 分享本地图片---好友
     *
     * @param shareTitle       分享标题
     * @param shareDescription 分享描述
     * @param shareUrl         分享URL网络路径
     */
    public void shareWechat(Context context, String appid, String shareTitle, String shareDescription, String shareUrl) {
        if (loadImageUrl == null || loadImageUrl.isCancelled()) {
            regiestWechat(appid);
            loadImageUrl = new LoadImageUrl(context, SendMessageToWX.Req.WXSceneSession, shareTitle, shareDescription, shareUrl);
            loadImageUrl.execute();
        }
    }

    /**
     * 分享网络图片---好友
     *
     * @param shareTitle       分享标题
     * @param shareDescription 分享描述
     * @param shareUrl         分享网络路径
     * @param sharePicUrl      分享图片地址
     */
    public void shareWechat(Context context, String appid, String shareTitle, String shareDescription, String shareUrl, String sharePicUrl) {
        if (loadImageUrl == null || loadImageUrl.isCancelled()) {
            regiestWechat(appid);
            loadImageUrl = new LoadImageUrl(context, SendMessageToWX.Req.WXSceneSession, shareTitle, shareDescription, shareUrl);
            loadImageUrl.execute(sharePicUrl);
        }
    }


    /**
     * 分享本地图片---朋友圈
     *
     * @param shareTitle       分享标题
     * @param shareDescription 分享描述
     * @param shareUrl         分享URL网络路径
     */
    public void shareWechatComment(Context context, String appid, String shareTitle, String shareDescription, String shareUrl) {
        if (loadImageUrl == null || loadImageUrl.isCancelled()) {
            regiestWechat(appid);
            if (!iwxapi.isWXAppInstalled()) {
                Toast.makeText(context, "请先安装微信", Toast.LENGTH_SHORT).show();
                return;
            }

            //shareWebPage(context, shareUrl, appid);

            // 初始化一个 WXTextObject 对象
//            WXTextObject textObj = new WXTextObject();
//            textObj.text = "你好";
//
//// 用 WXTextObject 对象初始化一个 WXMediaMessage 对象
//            WXMediaMessage msg = new WXMediaMessage(textObj);
//            msg.description = "你好";
//
//// 构造一个Resp
//            GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
//// 将req的transaction设置到resp对象中，其中bundle为微信传递过来的Intent所带的内容，通过getExtras()方法获取
//            Bundle bundle = new Bundle();
//            resp.transaction = new GetMessageFromWX.Req(bundle).transaction;
//            resp.message = msg;
//
////调用api接口，发送数据到微信
//            iwxapi.sendResp(resp);

            loadImageUrl = new LoadImageUrl(context, SendMessageToWX.Req.WXSceneTimeline, shareTitle, shareDescription, shareUrl);
            loadImageUrl.execute();
        }
    }

    /*
     * 分享链接
     */
    private void shareWebPage(Context context, String url, String openId) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "微说聊天";
        msg.description = "我正在使用\"微说\"，来找我聊天吧";

        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        msg.thumbData = bmpToByteArray(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = 0;
        req.userOpenId = openId;
        iwxapi.sendReq(req);
    }

    public static byte[] bmpToByteArray(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 分享网络图片---朋友圈
     *
     * @param shareTitle       分享标题
     * @param shareDescription 分享描述
     * @param shareUrl         分享网络路径
     * @param sharePicUrl      分享图片地址
     */
    public void shareWechatComment(Context context, String appid, String shareTitle, String shareDescription, String shareUrl, String sharePicUrl) {
        if (loadImageUrl == null || loadImageUrl.isCancelled()) {
            regiestWechat(appid);
            loadImageUrl = new LoadImageUrl(context, SendMessageToWX.Req.WXSceneTimeline, shareTitle, shareDescription, shareUrl);
            loadImageUrl.execute(sharePicUrl);
        }
    }

    /**
     * 分享小程序---分享本地图片
     *
     * @param shareTitle       分享标题
     * @param shareDescription 分享描述
     * @param shareUrl         网页链接
     * @param userName         小程序原始id
     * @param sharePath        小程序页面路径
     * @param sharePicUrl      分享的网络图片
     */
    public void shareWechatMini(Context context, String appid, String shareTitle, String shareDescription, String shareUrl, String userName,
                                String sharePath, String sharePicUrl) {
        if (loadImageUrl == null || loadImageUrl.isCancelled()) {
            regiestWechat(appid);
            loadImageUrl = new LoadImageUrl(context, SendMessageToWX.Req.WXSceneSession, shareTitle, shareDescription, shareUrl, userName, sharePath);
            loadImageUrl.execute(sharePicUrl);
        }
    }

    /**
     * 分享小程序---分享网络图片
     *
     * @param shareTitle       分享标题
     * @param shareDescription 分享描述
     * @param shareUrl         网页链接
     * @param userName         小程序原始id
     * @param sharePath        小程序页面路径
     */
    public void shareWechatMini(Context context, String appid, String shareTitle, String shareDescription, String shareUrl, String userName,
                                String sharePath) {
        if (loadImageUrl == null || loadImageUrl.isCancelled()) {
            regiestWechat(appid);
            loadImageUrl = new LoadImageUrl(context, SendMessageToWX.Req.WXSceneSession, shareTitle, shareDescription, shareUrl, userName, sharePath);
            loadImageUrl.execute();
        }
    }

    static class LoadImageUrl extends AsyncTask<String, Void, byte[]> {

        int reqscene = SendMessageToWX.Req.WXSceneSession;
        WeakReference<Context> baseActivityWeakReference;
        String shareTitle, shareDescription, shareUrl, userName, sharePath;

        public LoadImageUrl(Context baseActivity, int _reqscene, String _shareTitle,
                            String _shareDescription, String _shareUrl) {
            baseActivityWeakReference = new WeakReference<>(baseActivity);
            reqscene = _reqscene;
            shareTitle = _shareTitle;
            shareDescription = _shareDescription;
            shareUrl = _shareUrl;
        }

        public LoadImageUrl(Context baseActivity, int _reqscene, String _shareTitle,
                            String _shareDescription, String _shareUrl, String _userName,
                            String _sharePath) {
            baseActivityWeakReference = new WeakReference<>(baseActivity);
            reqscene = _reqscene;
            shareTitle = _shareTitle;
            shareDescription = _shareDescription;
            shareUrl = _shareUrl;
            userName = _userName;
            sharePath = _sharePath;
        }

        @Override
        protected byte[] doInBackground(String... strings) {
            byte[] datasByte = null;
            if (strings.length != 0 && !TextUtils.isEmpty(strings[0])) {
                //加载网络图片
                try {
                    Bitmap thumb = BitmapFactory.decodeStream(new URL(strings[0]).openStream());
                    //注意下面的这句压缩，120，150是长宽。
                    //一定要压缩，不然会分享失败
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(thumb, 120, 150, true);
                    //Bitmap回收
                    thumb.recycle();
                    datasByte = getByte(thumbBmp);
                    thumbBmp.recycle();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (datasByte == null) {
                try {
                    Context baseActivity = baseActivityWeakReference.get();
                    Bitmap bmp = BitmapFactory.decodeResource(baseActivity.getResources(), R.mipmap.ic_launcher);
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                    bmp.recycle();
                    datasByte = getByte(thumbBmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return datasByte;
        }

        @Override
        protected void onPostExecute(byte[] shareUrlByte) {
            super.onPostExecute(shareUrlByte);
            if (TextUtils.isEmpty(sharePath)) {
                //分享好友或朋友圈
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = shareUrl;
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = shareTitle;
                msg.description = shareDescription;
                msg.thumbData = shareUrlByte;
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = (ischeck() && reqscene == SendMessageToWX.Req.WXSceneSession) ?
                        SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
                iwxapi.sendReq(req);
            } else {
                //分享小程序
                WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                miniProgramObj.webpageUrl = shareUrl; // 兼容低版本的网页链接
                miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                miniProgramObj.userName = userName;     // 小程序原始id
                miniProgramObj.path = sharePath;            //小程序页面路径
                WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                msg.title = shareTitle;                    // 小程序消息title
                msg.description = shareDescription;               // 小程序消息desc
                msg.thumbData = shareUrlByte;                  // 小程序消息封面图片，小于128k
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前支持会话
                iwxapi.sendReq(req);
            }
            this.cancel(true);
        }
    }

    private static boolean ischeck() {
        if (iwxapi.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT) {
            return true;
        }
        return false;
    }

    private static byte[] getByte(Bitmap bitmap) {
        byte[] datas = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            datas = baos.toByteArray();
            baos.close();
            bitmap.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }


    /**
     * 当退出activity后，需要调用此方法，防止内存泄漏
     */
    public void cancleLoadingTask() {
        if (loadImageUrl != null) {
            loadImageUrl.cancel(true);
            loadImageUrl = null;
        }
        iwxapi = null;
    }
}
