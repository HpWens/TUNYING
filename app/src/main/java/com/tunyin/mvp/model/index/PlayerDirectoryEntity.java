package com.tunyin.mvp.model.index;

import java.util.List;

public class PlayerDirectoryEntity {

    private List<ListBean> list;
    private String currentEpisode;

    public String getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(String currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * comment_num : 0
         * min_grade : null
         * countView : 1
         * classify_id : 1,3,2,4
         * theme_id : null
         * collectNum : 0
         * member_price : null
         * listening_time : null
         * collect_num : 0
         * title : 包容 - 郑源
         * content : 包容 - 郑源
         * duration : 263
         * is_like : 1
         * is_noble_equity : 1
         * price : null
         * count_view : 1
         * id : 7
         * create_date : 2019-10-21 14:46:36
         * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/1a0b90aa-3218-44e4-8d11-f6ee5e7f784f.jpg
         * anchor_id : 2
         * sort : 1
         * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/6732cf36-228d-4485-ac1d-ea86cc614f89.mp3
         * is_delete : 0
         * commentNum : 0
         * durationDict : 4:23
         * is_free : 1
         * detail : null
         * modify_date : 2019-10-27 17:03:57
         */

        private String comment_num;
        private String min_grade;
        private String countView;
        private String classify_id;
        private String theme_id;
        private String collectNum;
        private String member_price;
        private String listening_time;
        private String collect_num;
        private String title;
        private String content;
        private String duration;
        private String is_like;
        private String is_noble_equity;
        private String price;
        private String count_view;
        private String id;
        private String create_date;
        private String image;
        private String anchor_id;
        private String sort;
        private String url;
        private String is_delete;
        private String commentNum;
        private String durationDict;
        private String is_free;
        private String detail;
        private String modify_date;

        private boolean isBuy;

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

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
