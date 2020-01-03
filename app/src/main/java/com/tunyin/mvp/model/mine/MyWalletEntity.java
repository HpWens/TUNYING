package com.tunyin.mvp.model.mine;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/20
 **/
public class MyWalletEntity {

        /**
         * balance : 26325
         * rechargeList : [{"createDate":"2019-09-01 04:27:00","modifyDate":"2019-09-01 04:27:00","id":1,"money":10,"note":100,"sort":1,"status":1,"statusDict":null},{"createDate":"2019-09-01 04:27:16","modifyDate":"2019-09-01 04:27:16","id":2,"money":20,"note":200,"sort":1,"status":1,"statusDict":null},{"createDate":"2019-09-01 04:27:27","modifyDate":"2019-09-01 04:27:27","id":3,"money":50,"note":500,"sort":1,"status":1,"statusDict":null},{"createDate":"2019-09-01 04:27:34","modifyDate":"2019-09-01 04:27:34","id":4,"money":0.01,"note":1000,"sort":1,"status":1,"statusDict":null},{"createDate":"2019-09-01 04:27:40","modifyDate":"2019-09-01 04:27:40","id":5,"money":200,"note":2000,"sort":1,"status":1,"statusDict":null},{"createDate":"2019-09-01 05:00:29","modifyDate":"2019-09-01 05:00:29","id":7,"money":500,"note":5000,"sort":1,"status":1,"statusDict":null}]
         */

        private String balance;
        private List<RechargeListBean> rechargeList;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public List<RechargeListBean> getRechargeList() {
            return rechargeList;
        }

        public void setRechargeList(List<RechargeListBean> rechargeList) {
            this.rechargeList = rechargeList;
        }

        public static class RechargeListBean {
            /**
             * createDate : 2019-09-01 04:27:00
             * modifyDate : 2019-09-01 04:27:00
             * id : 1
             * money : 10
             * note : 100
             * sort : 1
             * status : 1
             * statusDict : null
             */

            private String createDate;
            private String modifyDate;
            private String id;
            private String money;
            private String note;
            private String sort;
            private String status;
            private String statusDict;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatusDict() {
                return statusDict;
            }

            public void setStatusDict(String statusDict) {
                this.statusDict = statusDict;
            }
        }
}
