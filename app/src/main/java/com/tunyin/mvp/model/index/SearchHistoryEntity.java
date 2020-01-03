package com.tunyin.mvp.model.index;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/1
 **/
public class SearchHistoryEntity {

        /**
         * total : 6
         * list : [{"createDate":"2019-10-09 21:32:18","modifyDate":"2019-10-09 21:32:20","id":1,"title":"小睡眠","userId":"da3965a525fe44dfa347965235cb57aa","type":"1","result":null,"num":1,"isDelete":0},{"createDate":"2019-10-09 21:32:18","modifyDate":"2019-10-09 21:32:20","id":2,"title":"小睡眠","userId":"1","type":"1","result":null,"num":2,"isDelete":0},{"createDate":"2019-10-09 21:32:18","modifyDate":"2019-10-09 21:32:20","id":3,"title":"睡觉","userId":"1","type":"1","result":null,"num":2,"isDelete":0},{"createDate":"2019-10-10 11:05:21","modifyDate":"2019-10-10 11:05:21","id":4,"title":"搜索","userId":"da3965a525fe44dfa347965235cb57aa","type":null,"result":null,"num":1,"isDelete":0},{"createDate":"2019-10-27 16:17:13","modifyDate":"2019-10-27 16:17:13","id":5,"title":"那个女孩","userId":"da3965a525fe44dfa347965235cb57aa","type":null,"result":null,"num":1,"isDelete":0},{"createDate":"2019-10-27 16:47:18","modifyDate":"2019-10-27 16:47:18","id":6,"title":"那女孩","userId":"da3965a525fe44dfa347965235cb57aa","type":null,"result":null,"num":1,"isDelete":0}]
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
             * createDate : 2019-10-09 21:32:18
             * modifyDate : 2019-10-09 21:32:20
             * id : 1
             * title : 小睡眠
             * userId : da3965a525fe44dfa347965235cb57aa
             * type : 1
             * result : null
             * num : 1
             * isDelete : 0
             */

            private String createDate;
            private String modifyDate;
            private String id;
            private String title;
            private String userId;
            private String type;
            private String result;
            private String num;
            private String isDelete;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }
    }
}
