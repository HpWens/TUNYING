package com.tunyin.mvp.model.index;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/2
 **/
public class PayStuffEntity {


    /**
     * list : [{"comment_num":0,"min_grade":2,"countView":0,"classify_id":"1","theme_id":null,"collectNum":0,"member_price":15,"listening_time":null,"collect_num":0,"title":"那女孩对我说 付费","content":"这个是简介","duration":266,"is_like":1,"is_noble_equity":1,"price":20,"count_view":0,"id":3,"create_date":"2019-10-08 10:16:33","image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":4,"sort":1,"url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"durationDict":"04:26","is_free":0,"detail":null,"modify_date":"2019-10-08 10:16:35"},{"comment_num":0,"min_grade":null,"countView":1,"classify_id":"1,3,2,4","theme_id":null,"collectNum":0,"member_price":null,"listening_time":null,"collect_num":0,"title":"包容 - 郑源","content":"包容 - 郑源","duration":263,"is_like":1,"is_noble_equity":1,"price":null,"count_view":1,"id":7,"create_date":"2019-10-21 14:46:36","image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/1a0b90aa-3218-44e4-8d11-f6ee5e7f784f.jpg","anchor_id":2,"sort":1,"url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/6732cf36-228d-4485-ac1d-ea86cc614f89.mp3","is_delete":0,"commentNum":0,"durationDict":"04:23","is_free":1,"detail":null,"modify_date":"2019-10-27 17:03:57"},{"comment_num":0,"min_grade":null,"countView":0,"classify_id":"1","theme_id":null,"collectNum":0,"member_price":null,"listening_time":null,"collect_num":0,"title":"说好不哭 (with 五月天阿信) - 周杰伦.mp3","content":"说好不哭 (with 五月天阿信) - 周杰伦.mp3","duration":222,"is_like":0,"is_noble_equity":1,"price":null,"count_view":0,"id":14,"create_date":"2019-10-21 15:20:13","image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/f4d0b825-2ab7-4883-bf8c-9a89001784ab.jpg","anchor_id":null,"sort":1,"url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/352dc87e-2d0a-4792-bd82-f19ca1b65f2b.mp3","is_delete":0,"commentNum":0,"durationDict":"03:42","is_free":1,"detail":null,"modify_date":"2019-10-22 22:15:08"}]
     * total : 3
     * noteClassify : {"createDate":"2019-10-08 10:09:45","modifyDate":"2019-10-11 23:33:57","id":1,"name":"付费精品","type":1,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/图层610.png","homeIcon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/d2a144e6-1df6-4efa-879f-67ff3cf58c66.png","sort":6,"isHome":1}
     */

    private String total;
    private NoteClassifyBean noteClassify;
    private List<ListBean> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public NoteClassifyBean getNoteClassify() {
        return noteClassify;
    }

    public void setNoteClassify(NoteClassifyBean noteClassify) {
        this.noteClassify = noteClassify;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class NoteClassifyBean {
        /**
         * createDate : 2019-10-08 10:09:45
         * modifyDate : 2019-10-11 23:33:57
         * id : 1
         * name : 付费精品
         * type : 1
         * icon : https://hxzoss.oss-cn-beijing.aliyuncs.com/图层610.png
         * homeIcon : https://hxzoss.oss-cn-beijing.aliyuncs.com/d2a144e6-1df6-4efa-879f-67ff3cf58c66.png
         * sort : 6
         * isHome : 1
         */

        private String createDate;
        private String modifyDate;
        private String id;
        private String name;
        private String type;
        private String icon;
        private String homeIcon;
        private String sort;
        private String isHome;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHomeIcon() {
            return homeIcon;
        }

        public void setHomeIcon(String homeIcon) {
            this.homeIcon = homeIcon;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIsHome() {
            return isHome;
        }

        public void setIsHome(String isHome) {
            this.isHome = isHome;
        }
    }

    public static class ListBean {
        /**
         * comment_num : 0
         * min_grade : 2
         * countView : 0
         * classify_id : 1
         * theme_id : null
         * collectNum : 0
         * member_price : 15
         * listening_time : null
         * collect_num : 0
         * title : 那女孩对我说 付费
         * content : 这个是简介
         * duration : 266
         * is_like : 1
         * is_noble_equity : 1
         * price : 20
         * count_view : 0
         * id : 3
         * create_date : 2019-10-08 10:16:33
         * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
         * anchor_id : 4
         * sort : 1
         * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3
         * is_delete : 0
         * commentNum : 0
         * durationDict : 04:26
         * is_free : 0
         * detail : null
         * modify_date : 2019-10-08 10:16:35
         */

        public String comment_num;
        public String min_grade;
        public String countView;
        public String classify_id;
        public String theme_id;
        public String collectNum;
        public String member_price;
        public String listening_time;
        public String collect_num;
        public String title;
        public String content;
        public String duration;
        public String is_like;
        public String is_noble_equity;
        public String price;
        public String count_view;
        public String id;
        public String create_date;
        public String image;
        public String anchor_id;
        public String sort;
        public String url;
        public String is_delete;
        public String commentNum;
        public String durationDict;
        public String is_free;
        public String detail;
        public String modify_date;

        public String isHot;
        public String name;
        public String headUrl;
        public String createDate;
        public String modifyDate;
        public String summary;
        public String sex;
        public String constellation;
        public int fans;

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getMin_grade() {
            return min_grade;
        }

        public void setMin_grade(String min_grade) {
            this.min_grade = min_grade;
        }

        public String getCountView() {
            return countView;
        }

        public void setCountView(String countView) {
            this.countView = countView;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public String getTheme_id() {
            return theme_id;
        }

        public void setTheme_id(String theme_id) {
            this.theme_id = theme_id;
        }

        public String getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(String collectNum) {
            this.collectNum = collectNum;
        }

        public String getMember_price() {
            return member_price;
        }

        public void setMember_price(String member_price) {
            this.member_price = member_price;
        }

        public String getListening_time() {
            return listening_time;
        }

        public void setListening_time(String listening_time) {
            this.listening_time = listening_time;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }

        public String getIs_noble_equity() {
            return is_noble_equity;
        }

        public void setIs_noble_equity(String is_noble_equity) {
            this.is_noble_equity = is_noble_equity;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCount_view() {
            return count_view;
        }

        public void setCount_view(String count_view) {
            this.count_view = count_view;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAnchor_id() {
            return anchor_id;
        }

        public void setAnchor_id(String anchor_id) {
            this.anchor_id = anchor_id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getDurationDict() {
            return durationDict;
        }

        public void setDurationDict(String durationDict) {
            this.durationDict = durationDict;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getModify_date() {
            return modify_date;
        }

        public void setModify_date(String modify_date) {
            this.modify_date = modify_date;
        }
    }
}
