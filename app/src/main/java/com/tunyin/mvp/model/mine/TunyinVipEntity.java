package com.tunyin.mvp.model.mine;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/12/15
 **/
public class TunyinVipEntity {

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * createDate : 2019-11-07 00:19:34
             * modifyDate : 2019-11-07 00:19:34
             * id : 1
             * name : 贵族·骑士
             * icon : null
             * sort : 1
             * continuousMonth : 10
             * oneMonth : 10
             * sixMonth : 60
             * oneYear : 120
             * remark : null
             * status : 1
             */

            private String createDate;
            private String modifyDate;
            private String id;
            private String name;
            private String icon;
            private String sort;
            private String continuousMonth;
            private String oneMonth;
            private String sixMonth;
            private String oneYear;
            private String remark;
            private String status;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getContinuousMonth() {
                return continuousMonth;
            }

            public void setContinuousMonth(String continuousMonth) {
                this.continuousMonth = continuousMonth;
            }

            public String getOneMonth() {
                return oneMonth;
            }

            public void setOneMonth(String oneMonth) {
                this.oneMonth = oneMonth;
            }

            public String getSixMonth() {
                return sixMonth;
            }

            public void setSixMonth(String sixMonth) {
                this.sixMonth = sixMonth;
            }

            public String getOneYear() {
                return oneYear;
            }

            public void setOneYear(String oneYear) {
                this.oneYear = oneYear;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
    }
}
