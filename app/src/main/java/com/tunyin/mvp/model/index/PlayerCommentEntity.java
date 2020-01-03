package com.tunyin.mvp.model.index;

import java.util.List;

public class PlayerCommentEntity {

        /**
         * total : 2
         * list : [{"modifyDate":"2019-10-27 21:44:55","userGrade":1,"nickName":"2","headUrl":"https://hxzoss.oss-cn-beijing.aliyuncs.com/90a0251f-7484-42ff-b58f-5e8baa4c17d8.jpg","praiseNum":2,"isPraise":1,"id":1,"content":"很好听，不错","createDate":"2019-10-22 23:07:02"},{"modifyDate":"2019-10-27 21:37:47","userGrade":1,"nickName":"2","headUrl":"https://hxzoss.oss-cn-beijing.aliyuncs.com/90a0251f-7484-42ff-b58f-5e8baa4c17d8.jpg","praiseNum":0,"isPraise":0,"id":2,"content":"很好听，不错43257","createDate":"2019-10-22 23:07:45"}]
         */

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
             * modifyDate : 2019-10-27 21:44:55
             * userGrade : 1
             * nickName : 2
             * headUrl : https://hxzoss.oss-cn-beijing.aliyuncs.com/90a0251f-7484-42ff-b58f-5e8baa4c17d8.jpg
             * praiseNum : 2
             * isPraise : 1
             * id : 1
             * content : 很好听，不错
             * createDate : 2019-10-22 23:07:02
             */

            private String modifyDate;
            private String userGrade;
            private String nickName;
            private String headUrl;
            private String praiseNum;
            private String isPraise;
            private String id;
            private String content;
            private String createDate;

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getUserGrade() {
                return userGrade;
            }

            public void setUserGrade(String userGrade) {
                this.userGrade = userGrade;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }

            public String getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(String isPraise) {
                this.isPraise = isPraise;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
    }
}
