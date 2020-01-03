package com.tunyin.mvp.model.mine;

/**
 * create by wangrongchao
 * on 2019/11/21
 **/
public class PayInfoEntity {
        /**
         * recordId : 47
         * payInfoData : {"alipayData":"app_id=2019040263764257&biz_content=%7B%22out_trade_no%22%3A%22WR201911222208000029%22%2C%22total_amount%22%3A%2210.00%22%2C%22subject%22%3A%22%E9%9B%B6%E9%92%B1%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%221d%22%2C%22body%22%3A%22%E9%9B%B6%E9%92%B1%E5%85%85%E5%80%BC%22%7D&charset=UTF-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Ftyapi.xmhxz.net%2Fapi%2Fcommon%2FaliPay%2Fpay%2FwalletSuccessCallbackAsy.do&sign_type=RSA2&timestamp=2019-11-22+22%3A08%3A38&version=1.0&sign=FoKXEWf0hYEXY1MpRxGayrXrH1lbyYV1MrxIl7a7yjF1oIjK2%2BrauuwHnEWy497f6%2FajSeo0dEg%2BBjl87oOYkGBDoxLVZZ5BpXnr%2FL6Bfb%2FsAcKF%2FBpqOOPoJPMCe06%2B%2BvGAsYZ9OKulwO7iyHXDpHRUhU0bVWWdqDOXUSfLDwKxJLN7QqBaIO4LQqVUjw0KMlIEM9tQgJQxVxl3f6S91K%2FNysf5x3374v1WAS9TWeJcDEmNLgR6IrEhMfLv60S7BwHg6Cmdrej6vtNPMYaCSo1cWd8Nu6NCNejF1VJbZOovKpxY8vj6592p5LtAc9Cw5xe2y%2FGoE0erspIU6bHW6g%3D%3D","appid":null,"sign":null,"partnerid":null,"prepayid":null,"packages":null,"noncestr":null,"timestamp":null}
         */

        private int recordId;
        private PayInfoDataBean payInfoData;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public PayInfoDataBean getPayInfoData() {
            return payInfoData;
        }

        public void setPayInfoData(PayInfoDataBean payInfoData) {
            this.payInfoData = payInfoData;
        }

        public static class PayInfoDataBean {
            /**
             * alipayData : app_id=2019040263764257&biz_content=%7B%22out_trade_no%22%3A%22WR201911222208000029%22%2C%22total_amount%22%3A%2210.00%22%2C%22subject%22%3A%22%E9%9B%B6%E9%92%B1%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%221d%22%2C%22body%22%3A%22%E9%9B%B6%E9%92%B1%E5%85%85%E5%80%BC%22%7D&charset=UTF-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Ftyapi.xmhxz.net%2Fapi%2Fcommon%2FaliPay%2Fpay%2FwalletSuccessCallbackAsy.do&sign_type=RSA2&timestamp=2019-11-22+22%3A08%3A38&version=1.0&sign=FoKXEWf0hYEXY1MpRxGayrXrH1lbyYV1MrxIl7a7yjF1oIjK2%2BrauuwHnEWy497f6%2FajSeo0dEg%2BBjl87oOYkGBDoxLVZZ5BpXnr%2FL6Bfb%2FsAcKF%2FBpqOOPoJPMCe06%2B%2BvGAsYZ9OKulwO7iyHXDpHRUhU0bVWWdqDOXUSfLDwKxJLN7QqBaIO4LQqVUjw0KMlIEM9tQgJQxVxl3f6S91K%2FNysf5x3374v1WAS9TWeJcDEmNLgR6IrEhMfLv60S7BwHg6Cmdrej6vtNPMYaCSo1cWd8Nu6NCNejF1VJbZOovKpxY8vj6592p5LtAc9Cw5xe2y%2FGoE0erspIU6bHW6g%3D%3D
             * appid : null
             * sign : null
             * partnerid : null
             * prepayid : null
             * packages : null
             * noncestr : null
             * timestamp : null
             */

            private String alipayData;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String packages;
            private String noncestr;
            private String timestamp;

            public String getAlipayData() {
                return alipayData;
            }

            public void setAlipayData(String alipayData) {
                this.alipayData = alipayData;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackages() {
                return packages;
            }

            public void setPackages(String packages) {
                this.packages = packages;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
    }
}
