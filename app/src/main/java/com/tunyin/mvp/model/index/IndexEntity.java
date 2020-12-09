package com.tunyin.mvp.model.index;

import java.util.ArrayList;
import java.util.List;

public class IndexEntity {
    /**
     * featuredRecommend : {"total":6,"list":[{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000}]}
     * freeList : {"total":1,"list":[{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","min_grade":2,"anchor_id":4,"classify_id":"1","num":1,"member_price":15,"title":"那女孩对我说 付费","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"is_noble_equity":1,"price":20,"is_free":0,"id":3,"create_date":1570500993000,"modify_date":1570500995000}]}
     * classifyList : [{"createDate":"2019-10-08 10:09:45","modifyDate":"2019-10-11 23:33:57","id":1,"name":"付费精品","type":1,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/图层610.png","sort":6,"isHome":1},{"createDate":"2019-10-08 10:10:31","modifyDate":"2019-10-08 10:10:33","id":2,"name":"广播剧","type":2,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/收音机.png","sort":5,"isHome":1},{"createDate":"2019-10-11 23:31:07","modifyDate":"2019-10-11 23:33:36","id":4,"name":"电台","type":3,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/d6e2670f-22a4-49da-90dc-a5ac021c5ca3.png","sort":3,"isHome":1},{"createDate":"2019-10-14 11:08:19","modifyDate":"2019-10-14 11:08:22","id":7,"name":"排行榜","type":4,"icon":"https://hxzoss.oss-cn-beijing.aliyuncs.com/dd9ce6c5-37dc-4adf-bc31-68c05acaac1a.png","sort":0,"isHome":1}]
     * guessLike : {"total":6,"list":[{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","min_grade":2,"anchor_id":4,"countView":1,"classify_id":"1","collectNum":0,"member_price":15,"title":"那女孩对我说 付费","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_noble_equity":1,"price":20,"is_free":0,"id":3,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":5,"countView":0,"classify_id":"4,5,6","collectNum":0,"title":"那女孩对我说 ","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":4,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/cafccec3-e9a8-4da3-82bc-4636082a1115.jpg","anchor_id":2,"countView":1,"classify_id":"4,6","collectNum":0,"title":"郭涛 - 山那边","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/ff1f6124-f9be-48ee-8006-6370ae4652b3.mp3","is_delete":0,"commentNum":0,"is_noble_equity":0,"is_free":1,"id":5,"create_date":1570809669000,"modify_date":1570809669000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/e371f7c1-e4bf-4e48-a2fc-67b827c11731.jpg","anchor_id":3,"countView":1,"classify_id":"4,3,2","collectNum":0,"title":"HITA - 赤伶.mp3","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/282df48d-5793-4c96-9a04-3fb63a579bd5.mp3","is_delete":0,"commentNum":0,"is_noble_equity":1,"is_free":1,"id":6,"create_date":1570811417000,"modify_date":1570811417000}]}
     * bannerList : [{"createDate":"2019-10-09 00:34:54","modifyDate":"2019-10-09 00:34:56","id":1,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/de394307-34d2-4e5c-b70a-336c1f061d70.jpg","type":1,"position":1,"sort":1,"contactId":"1","contactName":null},{"createDate":"2019-10-09 00:35:31","modifyDate":"2019-10-09 00:35:33","id":2,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/99de1d47-40f8-4893-b13f-0fb3819db3e3.jpg","type":2,"position":1,"sort":1,"contactId":"1","contactName":null},{"createDate":"2019-10-10 23:29:07","modifyDate":"2019-10-10 23:37:57","id":4,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/86af1b66-cc6f-44c5-96a0-676671b80750.jpg","type":1,"position":1,"sort":1,"contactId":"1","contactName":null},{"createDate":"2019-10-09 00:35:49","modifyDate":"2019-10-10 23:38:06","id":3,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/78578e90-14f1-4bb5-9734-6999d926cc39.jpg","type":1,"position":1,"sort":2,"contactId":"1","contactName":null}]
     * featuredActivity : {"total":6,"list":[{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000}]}
     * sleepTop : {"total":6,"list":[{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","min_grade":2,"anchor_id":4,"countView":1,"classify_id":"1","collectNum":0,"member_price":15,"title":"那女孩对我说 付费","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_noble_equity":1,"price":20,"is_free":0,"id":3,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":5,"countView":0,"classify_id":"4,5,6","collectNum":0,"title":"那女孩对我说 ","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":4,"create_date":1570500993000,"modify_date":1570500995000}]}
     * anchorList : [{"createDate":"2019-10-13 17:58:36","modifyDate":"2019-10-13 17:58:41","id":1,"name":"adele","headUrl":"https://hxzoss.oss-cn-beijing.aliyuncs.com/edeaa554-2461-4ba2-846c-79fadd54c2f3.jpg","isHot":"1"},{"createDate":"2019-10-13 17:59:02","modifyDate":"2019-10-13 17:59:05","id":2,"name":"小雅","headUrl":"https://hxzoss.oss-cn-beijing.aliyuncs.com/99ed6c02-5c80-4a55-b062-f31821c0265c.jpg","isHot":"1"},{"createDate":"2019-10-13 17:59:02","modifyDate":"2019-10-13 17:59:02","id":3,"name":"乔碧萝","headUrl":"https://hxzoss.oss-cn-beijing.aliyuncs.com/27ae47a6-8f6c-4b7b-a313-b1ce66d28371.jpg","isHot":"1"},{"createDate":"2019-10-13 17:59:02","modifyDate":"2019-10-13 17:59:02","id":4,"name":"苍井空","headUrl":"https://hxzoss.oss-cn-beijing.aliyuncs.com/33442b5a-b221-4bfe-a1ce-629ca4b34ba3.jpg","isHot":"1"}]
     */

    private FeaturedRecommendBean featuredRecommend;
    private FreeListBean freeList;
    private GuessLikeBean guessLike;
    private FeaturedActivityBean featuredActivity;
    private SleepTopBean sleepTop;
    private List<ClassifyListBean> classifyList;
    private List<BannerListBean> bannerList;
    private List<AnchorListBean> anchorList = new ArrayList<>();
    private String hotSearch;

    public String getHotSearch() {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch) {
        this.hotSearch = hotSearch;
    }

    public FeaturedRecommendBean getFeaturedRecommend() {
        return featuredRecommend;
    }

    public void setFeaturedRecommend(FeaturedRecommendBean featuredRecommend) {
        this.featuredRecommend = featuredRecommend;
    }

    public FreeListBean getFreeList() {
        return freeList;
    }

    public void setFreeList(FreeListBean freeList) {
        this.freeList = freeList;
    }

    public GuessLikeBean getGuessLike() {
        return guessLike;
    }

    public void setGuessLike(GuessLikeBean guessLike) {
        this.guessLike = guessLike;
    }

    public FeaturedActivityBean getFeaturedActivity() {
        return featuredActivity;
    }

    public void setFeaturedActivity(FeaturedActivityBean featuredActivity) {
        this.featuredActivity = featuredActivity;
    }

    public SleepTopBean getSleepTop() {
        return sleepTop;
    }

    public void setSleepTop(SleepTopBean sleepTop) {
        this.sleepTop = sleepTop;
    }

    public List<ClassifyListBean> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<ClassifyListBean> classifyList) {
        this.classifyList = classifyList;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<AnchorListBean> getAnchorList() {
        return anchorList;
    }

    public void setAnchorList(List<AnchorListBean> anchorList) {
        this.anchorList = anchorList;
    }

    public static class FeaturedRecommendBean {
        /**
         * total : 6
         * list : [{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000}]
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
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
             * anchor_id : 1
             * countView : 4
             * classify_id : 2,3
             * collectNum : 0
             * title : 孤芳自赏
             * content : 这个是简介
             * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3
             * is_delete : 0
             * commentNum : 0
             * is_free : 1
             * id : 1
             * create_date : 1570500993000
             * modify_date : 1570500995000
             */

            private String image;
            private String anchor_id;
            private String countView;
            private String classify_id;
            private String collectNum;
            private String title;
            private String content;
            private String url;
            private String is_delete;
            private String commentNum;
            private String is_free;
            private String id;
            private String create_date;
            private String modify_date;
            private String durationDict = "";

            public String getDurationDict() {
                return durationDict;
            }

            public void setDurationDict(String durationDict) {
                this.durationDict = durationDict;
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

            public String getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(String collectNum) {
                this.collectNum = collectNum;
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

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
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

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }
        }
    }

    public static class FreeListBean {
        /**
         * total : 1
         * list : [{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","min_grade":2,"anchor_id":4,"classify_id":"1","num":1,"member_price":15,"title":"那女孩对我说 付费","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"is_noble_equity":1,"price":20,"is_free":0,"id":3,"create_date":1570500993000,"modify_date":1570500995000}]
         */

        private String total;
        private List<ListBeanX> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
             * min_grade : 2
             * anchor_id : 4
             * classify_id : 1
             * num : 1
             * member_price : 15
             * title : 那女孩对我说 付费
             * content : 这个是简介
             * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3
             * is_delete : 0
             * is_noble_equity : 1
             * price : 20
             * is_free : 0
             * id : 3
             * create_date : 1570500993000
             * modify_date : 1570500995000
             */

            private String image;
            private String min_grade;
            private String anchor_id;
            private String classify_id;
            private String num;
            private String member_price;
            private String title;
            private String content;
            private String url;
            private String is_delete;
            private String is_noble_equity;
            private String price;
            private String is_free;
            private String id;
            private String create_date;
            private String modify_date;
            public String count_view;

            public String getCount_view() {
                return count_view;
            }

            public void setCount_view(String count_view) {
                this.count_view = count_view;
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

            public String getAnchor_id() {
                return anchor_id;
            }

            public void setAnchor_id(String anchor_id) {
                this.anchor_id = anchor_id;
            }

            public String getClassify_id() {
                return classify_id;
            }

            public void setClassify_id(String classify_id) {
                this.classify_id = classify_id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getMember_price() {
                return member_price;
            }

            public void setMember_price(String member_price) {
                this.member_price = member_price;
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

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
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

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }
        }
    }

    public static class GuessLikeBean {
        /**
         * total : 6
         * list : [{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","min_grade":2,"anchor_id":4,"countView":1,"classify_id":"1","collectNum":0,"member_price":15,"title":"那女孩对我说 付费","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_noble_equity":1,"price":20,"is_free":0,"id":3,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":5,"countView":0,"classify_id":"4,5,6","collectNum":0,"title":"那女孩对我说 ","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":4,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/cafccec3-e9a8-4da3-82bc-4636082a1115.jpg","anchor_id":2,"countView":1,"classify_id":"4,6","collectNum":0,"title":"郭涛 - 山那边","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/ff1f6124-f9be-48ee-8006-6370ae4652b3.mp3","is_delete":0,"commentNum":0,"is_noble_equity":0,"is_free":1,"id":5,"create_date":1570809669000,"modify_date":1570809669000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/e371f7c1-e4bf-4e48-a2fc-67b827c11731.jpg","anchor_id":3,"countView":1,"classify_id":"4,3,2","collectNum":0,"title":"HITA - 赤伶.mp3","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/282df48d-5793-4c96-9a04-3fb63a579bd5.mp3","is_delete":0,"commentNum":0,"is_noble_equity":1,"is_free":1,"id":6,"create_date":1570811417000,"modify_date":1570811417000}]
         */

        private String total;
        private List<ListBeanXX> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBeanXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXX> list) {
            this.list = list;
        }

        public static class ListBeanXX {
            /**
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
             * anchor_id : 1
             * countView : 4
             * classify_id : 2,3
             * collectNum : 0
             * title : 孤芳自赏
             * content : 这个是简介
             * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3
             * is_delete : 0
             * commentNum : 0
             * is_free : 1
             * id : 1
             * create_date : 1570500993000
             * modify_date : 1570500995000
             * min_grade : 2
             * member_price : 15
             * is_noble_equity : 1
             * price : 20
             */

            private String image;
            private String anchor_id;
            private String countView;
            private String classify_id;
            private String collectNum;
            private String title;
            private String content;
            private String url;
            private String is_delete;
            private String commentNum;
            private String is_free;
            private String id;
            private String create_date;
            private String modify_date;
            private String min_grade;
            private String member_price;
            private String is_noble_equity;
            private String price;

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

            public String getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(String collectNum) {
                this.collectNum = collectNum;
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

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
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

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }

            public String getMin_grade() {
                return min_grade;
            }

            public void setMin_grade(String min_grade) {
                this.min_grade = min_grade;
            }

            public String getMember_price() {
                return member_price;
            }

            public void setMember_price(String member_price) {
                this.member_price = member_price;
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
        }
    }

    public static class FeaturedActivityBean {
        /**
         * total : 6
         * list : [{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000}]
         */

        private String total;
        private List<ListBeanXXX> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBeanXXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXXX> list) {
            this.list = list;
        }

        public static class ListBeanXXX {
            /**
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
             * anchor_id : 1
             * countView : 4
             * classify_id : 2,3
             * collectNum : 0
             * title : 孤芳自赏
             * content : 这个是简介
             * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3
             * is_delete : 0
             * commentNum : 0
             * is_free : 1
             * id : 1
             * create_date : 1570500993000
             * modify_date : 1570500995000
             */

            private String image;
            private String anchor_id;
            private String countView;
            private String classify_id;
            private String collectNum;
            private String title;
            private String content;
            private String url;
            private String is_delete;
            private String commentNum;
            private String is_free;
            private String id;
            private String create_date;
            private String modify_date;

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

            public String getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(String collectNum) {
                this.collectNum = collectNum;
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

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
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

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }
        }
    }

    public static class SleepTopBean {
        /**
         * total : 6
         * list : [{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":1,"countView":4,"classify_id":"2,3","collectNum":0,"title":"孤芳自赏","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":1,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":2,"countView":2,"classify_id":"2,4","collectNum":0,"title":"最远的你是我最近的爱","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E6%9C%80%E8%BF%9C%E7%9A%84%E4%BD%A0%E6%98%AF%E6%88%91%E6%9C%80%E8%BF%91%E7%9A%84%E7%88%B1%20-%20%E5%B0%8F%E9%98%BF%E6%9E%AB.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":2,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","min_grade":2,"anchor_id":4,"countView":1,"classify_id":"1","collectNum":0,"member_price":15,"title":"那女孩对我说 付费","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_noble_equity":1,"price":20,"is_free":0,"id":3,"create_date":1570500993000,"modify_date":1570500995000},{"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg","anchor_id":5,"countView":0,"classify_id":"4,5,6","collectNum":0,"title":"那女孩对我说 ","content":"这个是简介","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3","is_delete":0,"commentNum":0,"is_free":1,"id":4,"create_date":1570500993000,"modify_date":1570500995000}]
         */

        private String total;
        private List<ListBeanXXXX> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBeanXXXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXXXX> list) {
            this.list = list;
        }

        public static class ListBeanXXXX {
            /**
             * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
             * anchor_id : 1
             * countView : 4
             * classify_id : 2,3
             * collectNum : 0
             * title : 孤芳自赏
             * content : 这个是简介
             * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E5%AD%A4%E8%8A%B3%E8%87%AA%E8%B5%8F%20-%20%E9%A3%8E%E5%BA%A6.mp3
             * is_delete : 0
             * commentNum : 0
             * is_free : 1
             * id : 1
             * create_date : 1570500993000
             * modify_date : 1570500995000
             * min_grade : 2
             * member_price : 15
             * is_noble_equity : 1
             * price : 20
             */

            private String image;
            private String anchor_id;
            private String countView;
            private String classify_id;
            private String collectNum;
            private String title;
            private String content;
            private String url;
            private String is_delete;
            private String commentNum;
            private String is_free;
            private String id;
            private String create_date;
            private String modify_date;
            private String min_grade;
            private String member_price;
            private String is_noble_equity;
            private String price;

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

            public String getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(String collectNum) {
                this.collectNum = collectNum;
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

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
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

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }

            public String getMin_grade() {
                return min_grade;
            }

            public void setMin_grade(String min_grade) {
                this.min_grade = min_grade;
            }

            public String getMember_price() {
                return member_price;
            }

            public void setMember_price(String member_price) {
                this.member_price = member_price;
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
        }
    }

    public static class ClassifyListBean {
        /**
         * createDate : 2019-10-08 10:09:45
         * modifyDate : 2019-10-11 23:33:57
         * id : 1
         * name : 付费精品
         * type : 1
         * icon : https://hxzoss.oss-cn-beijing.aliyuncs.com/图层610.png
         * sort : 6
         * isHome : 1
         */

        private String createDate;
        private String modifyDate;
        private String id;
        private String name;
        private String type;
        private String icon;
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

    public static class BannerListBean {
        /**
         * createDate : 2019-10-09 00:34:54
         * modifyDate : 2019-10-09 00:34:56
         * id : 1
         * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/de394307-34d2-4e5c-b70a-336c1f061d70.jpg
         * type : 1
         * position : 1
         * sort : 1
         * contactId : 1
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
        private Object contactName;

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

        public Object getContactName() {
            return contactName;
        }

        public void setContactName(Object contactName) {
            this.contactName = contactName;
        }
    }

    public static class AnchorListBean {
        /**
         * createDate : 2019-10-13 17:58:36
         * modifyDate : 2019-10-13 17:58:41
         * id : 1
         * name : adele
         * headUrl : https://hxzoss.oss-cn-beijing.aliyuncs.com/edeaa554-2461-4ba2-846c-79fadd54c2f3.jpg
         * isHot : 1
         */

        private String createDate;
        private String modifyDate;
        private String id;
        private String name;
        private String headUrl;
        private String isHot;

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

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }
    }


    public String formattime(long time) {
        String min = (time / (1000 * 60)) + "";
        String second = (time % (1000 * 60) / 1000) + "";
        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return min + ":" + second;
    }
}
