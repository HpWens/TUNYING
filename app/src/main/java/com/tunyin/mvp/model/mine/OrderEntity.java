package com.tunyin.mvp.model.mine;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/8
 **/
public class OrderEntity {
    /**
     * total : 3
     * list : [{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/8dacecad-d398-4f99-8b4f-cb5914fe24d2.jpg","createDateFormat":"2019-11-07 01:01:10",
     * "orderNo":"GO2019110701010900003","totalPrice":10,"id":8,"title":"渡我不渡她 - 苏谭谭","songId":6,"createDate":"2019-11-07"},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs
     * .com/01955e29-26c5-440b-b4b1-39057ce4f756.jpg","createDateFormat":"2019-11-07 01:01:07","orderNo":"GO2019110701010600002","totalPrice":10,"id":7,"title":"别知己 -
     * 海来阿木、阿呷拉古、曲比阿且","songId":2,"createDate":"2019-11-07"},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/fda17c94-f8b9-4163-bb45-187d406cf8e6.jpg",
     * "createDateFormat":"2019-11-07 01:01:03","orderNo":"GO2019110701010300001","totalPrice":5,"id":6,"title":"百花香 - 魏新雨","songId":1,"createDate":"2019-11-07"}]
     */

    private String total;
    private List<ListBean> list = new ArrayList<>();

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/8dacecad-d398-4f99-8b4f-cb5914fe24d2.jpg
         * createDateFormat : 2019-11-07 01:01:10
         * orderNo : GO2019110701010900003
         * totalPrice : 10
         * id : 8
         * title : 渡我不渡她 - 苏谭谭
         * songId : 6
         * createDate : 2019-11-07
         */

        private String image = "";
        private String createDateFormat = "";
        private String orderNo = "";
        private String totalPrice = "";
        private String id = "";
        private String title = "";
        private String songId = "";
        private String createDate = "";

        private boolean helperStartEnable = false;

        public boolean isHelperStartEnable() {
            return helperStartEnable;
        }

        public void setHelperStartEnable(boolean helperStartEnable) {
            this.helperStartEnable = helperStartEnable;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreateDateFormat() {
            return createDateFormat;
        }

        public void setCreateDateFormat(String createDateFormat) {
            this.createDateFormat = createDateFormat;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSongId() {
            return songId;
        }

        public void setSongId(String songId) {
            this.songId = songId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
