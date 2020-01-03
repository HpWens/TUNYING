package com.tunyin.mvp.model.mine;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/10
 **/
public class VoucherEntity {


    private String total;
    private List<ListBean> list;

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
         * isUse : 1
         * money : 50
         * startTime : 2019-11-09
         * endTime : 2020-01-01
         * title : 新用户礼包
         * valve : 500
         * type :
         * createDate : 2019-11-09
         */

        private String isUse;
        private String money;
        private String startTime;
        private String endTime;
        private String title;
        private String valve;
        private String type;
        private String createDate;

        public String getIsUse() {
            return isUse;
        }

        public void setIsUse(String isUse) {
            this.isUse = isUse;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValve() {
            return valve;
        }

        public void setValve(String valve) {
            this.valve = valve;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
