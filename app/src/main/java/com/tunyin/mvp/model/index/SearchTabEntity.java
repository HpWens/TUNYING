package com.tunyin.mvp.model.index;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/2
 **/
public class SearchTabEntity {


    /**
     * content : [{"createDate":null,"modifyDate":null,"id":0,"name":"推荐","type":null,"icon":null,"homeIcon":null,"sort":null,"isHome":null},{"createDate":"2019-10-08 10:09:45","modifyDate":"2019-10-11 23:33:57","id":1,"name":"付费精品","type":1,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/图层610.png","homeIcon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/d2a144e6-1df6-4efa-879f-67ff3cf58c66.png","sort":6,"isHome":1},{"createDate":"2019-10-08 10:10:31","modifyDate":"2019-10-08 10:10:33","id":2,"name":"广播剧","type":2,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/收音机.png","homeIcon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/ac028821-2d8b-466b-8cad-f4552bcc06f6.png","sort":5,"isHome":1},{"createDate":"2019-10-08 10:11:28","modifyDate":"2019-10-08 10:11:29","id":3,"name":"主题哄睡","type":2,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/月亮.png","homeIcon":null,"sort":4,"isHome":0},{"createDate":"2019-10-11 23:31:07","modifyDate":"2019-10-11 23:33:36","id":4,"name":"电台","type":3,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/d6e2670f-22a4-49da-90dc-a5ac021c5ca3.png","homeIcon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/0d4e688b-1d32-4dc5-97e3-1dea17f754b0.png","sort":3,"isHome":1},{"createDate":"2019-10-13 19:56:30","modifyDate":"2019-10-13 19:56:30","id":5,"name":"自然声","type":4,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/a033d2a7-ad19-40b5-8943-05e3398ec71d.png","homeIcon":null,"sort":2,"isHome":0},{"createDate":"2019-10-13 19:57:20","modifyDate":"2019-10-13 19:57:20","id":6,"name":"无人声","type":4,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/b51af0fd-eeb8-4db1-97d4-d8fc54a068a7.png","homeIcon":null,"sort":1,"isHome":0}]
     * code : 200
     * desc : 操作成功
     */

    private String code;
    private String desc;
    private List<ContentBean> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * createDate : null
         * modifyDate : null
         * id : 0
         * name : 推荐
         * type : null
         * icon : null
         * homeIcon : null
         * sort : null
         * isHome : null
         */

        private Object createDate;
        private Object modifyDate;
        private int id;
        private String name;
        private Object type;
        private Object icon;
        private Object homeIcon;
        private Object sort;
        private Object isHome;

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public Object getHomeIcon() {
            return homeIcon;
        }

        public void setHomeIcon(Object homeIcon) {
            this.homeIcon = homeIcon;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getIsHome() {
            return isHome;
        }

        public void setIsHome(Object isHome) {
            this.isHome = isHome;
        }
    }
}
