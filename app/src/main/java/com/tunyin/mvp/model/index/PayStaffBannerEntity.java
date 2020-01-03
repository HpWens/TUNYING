package com.tunyin.mvp.model.index;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/9
 **/
public class PayStaffBannerEntity {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * createDate : 2019-11-04 22:38:40
             * modifyDate : 2019-11-04 22:38:40
             * id : 3
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/4ed07f7d-3e45-433d-ba1f-4836937491e8.jpg
             * type : 1
             * position : 2
             * sort : 1
             * contactId : 2
             * contactName : null
             */

            private String createDate;
            private String modifyDate;
            private String id;
            private String image;
            private String type;
            private String position;
            private String sort;
            private String contactId;
            private String contactName;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getContactId() {
                return contactId;
            }

            public void setContactId(String contactId) {
                this.contactId = contactId;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }
        }
}
