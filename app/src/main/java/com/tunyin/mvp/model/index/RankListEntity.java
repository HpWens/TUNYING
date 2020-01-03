package com.tunyin.mvp.model.index;

import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/7
 **/
public class RankListEntity {

        /**
         * total : 5
         * list : [{"comment_num":0,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/fda17c94-f8b9-4163-bb45-187d406cf8e6.jpg","min_grade":null,"classify_id":"1,2,3,4,5,6","theme_id":2,"member_price":null,"listening_time":10,"collect_num":0,"sort":1,"title":"百花香 - 魏新雨","content":"百花香 - 魏新雨 付费音频 试听10s 价格5","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/6f15e521-2a94-42b4-95a7-da5db4edec9a.mp3","is_delete":0,"duration":167,"is_like":1,"price":5,"is_noble_equity":1,"is_free":0,"count_view":1,"id":1,"detail":null,"create_date":1572877079000,"modify_date":1573060717000},{"comment_num":0,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/01955e29-26c5-440b-b4b1-39057ce4f756.jpg","min_grade":null,"classify_id":"1","theme_id":1,"member_price":null,"listening_time":null,"collect_num":0,"sort":1,"title":"别知己 - 海来阿木、阿呷拉古、曲比阿且","content":"别知己 - 海来阿木、阿呷拉古、曲比阿且 付费","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/73e0705f-cb0c-4eb3-9129-f0a1e2144f99.mp3","is_delete":0,"duration":280,"is_like":1,"price":10,"is_noble_equity":0,"is_free":0,"count_view":0,"id":2,"detail":null,"create_date":1572877319000,"modify_date":1572878118000},{"comment_num":0,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/161e4997-c9af-40a1-ac32-441096eb027c.jpg","min_grade":null,"classify_id":"1","theme_id":null,"member_price":null,"listening_time":15,"collect_num":0,"sort":1,"title":"不爱我就别伤害我 - 杨顺高、凯小晴","content":"不爱我就别伤害我 - 杨顺高、凯小晴","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/f3cf4d81-609b-4ced-b8f5-c230754075c4.mp3","is_delete":0,"duration":253,"is_like":1,"price":10,"is_noble_equity":1,"is_free":0,"count_view":0,"id":3,"detail":null,"create_date":1572877352000,"modify_date":1572878139000},{"comment_num":0,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/8dacecad-d398-4f99-8b4f-cb5914fe24d2.jpg","min_grade":null,"classify_id":"4,5","theme_id":1,"member_price":null,"listening_time":50,"collect_num":0,"sort":1,"title":"渡我不渡她 - 苏谭谭","content":"渡我不渡她 - 苏谭谭","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/e1a0d201-e7f6-4d47-8ffa-6ace0ea01a00.mp3","is_delete":0,"duration":138,"is_like":1,"price":10,"is_noble_equity":1,"is_free":0,"count_view":0,"id":6,"detail":null,"create_date":1572877460000,"modify_date":1572878119000},{"comment_num":0,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/178d2d64-a6d7-47b3-94cb-b202302df8ae.jpg","min_grade":null,"classify_id":"1,3,4","theme_id":null,"member_price":null,"listening_time":2,"collect_num":0,"sort":1,"title":"年少有为 - 李荣浩","content":"年少有为 - 李荣浩","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/c3ed6477-a31f-456f-905c-7814f9bec318.mp3","is_delete":0,"duration":279,"is_like":null,"price":21,"is_noble_equity":1,"is_free":0,"count_view":0,"id":16,"detail":null,"create_date":1572877729000,"modify_date":1572877729000}]
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
             * comment_num : 0
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/fda17c94-f8b9-4163-bb45-187d406cf8e6.jpg
             * min_grade : null
             * classify_id : 1,2,3,4,5,6
             * theme_id : 2
             * member_price : null
             * listening_time : 10
             * collect_num : 0
             * sort : 1
             * title : 百花香 - 魏新雨
             * content : 百花香 - 魏新雨 付费音频 试听10s 价格5
             * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/6f15e521-2a94-42b4-95a7-da5db4edec9a.mp3
             * is_delete : 0
             * duration : 167
             * is_like : 1
             * price : 5
             * is_noble_equity : 1
             * is_free : 0
             * count_view : 1
             * id : 1
             * detail : null
             * create_date : 1572877079000
             * modify_date : 1573060717000
             */

            private String comment_num;
            private String image;
            private String min_grade;
            private String classify_id;
            private String theme_id;
            private String member_price;
            private String listening_time;
            private String collect_num;
            private String sort;
            private String title;
            private String content;
            private String url;
            private String is_delete;
            private String duration;
            private String is_like;
            private String price;
            private String is_noble_equity;
            private String is_free;
            private String count_view;
            private String id;
            private String detail;
            private long create_date;
            private long modify_date;

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getMin_grade() {
                return min_grade;
            }

            public void setMin_grade(String min_grade) {
                this.min_grade = min_grade;
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

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIs_noble_equity() {
                return is_noble_equity;
            }

            public void setIs_noble_equity(String is_noble_equity) {
                this.is_noble_equity = is_noble_equity;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public long getModify_date() {
                return modify_date;
            }

            public void setModify_date(long modify_date) {
                this.modify_date = modify_date;
            }
    }
}
