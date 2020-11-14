package com.tunyin.mvp.model.mine;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangrongchao
 * on 2019/11/10
 **/
public class CollectEntity {

    /**
     * total : 3
     * list : [{"isLike":1,"minGrade":null,"classifyId":"1,2,3,4,5,6","countView":2,"pageSize":null,"collectNum":1,"themeId":2,"title":"百花香 - 魏新雨","content":"百花香 - 魏新雨 付费音频 试听10s 价格5","duration":167,"isNobleEquity":1,"isFree":"0","pageNo":null,"price":5,"id":1,"createDate":1572877079000,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/fda17c94-f8b9-4163-bb45-187d406cf8e6.jpg","modifyDate":1573372966000,"memberPrice":null,"isDelete":0,"noteId":1,"minGradeDict":null,"sort":1,"userId":"2d09be3c-db42-439b-91ac-cbc4af23b82d","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/6f15e521-2a94-42b4-95a7-da5db4edec9a.mp3","commentNum":0,"listeningTime":10,"detail":null},{"isLike":1,"minGrade":null,"classifyId":"1","countView":0,"pageSize":null,"collectNum":1,"themeId":1,"title":"别知己 - 海来阿木、阿呷拉古、曲比阿且","content":"别知己 - 海来阿木、阿呷拉古、曲比阿且 付费","duration":280,"isNobleEquity":0,"isFree":"0","pageNo":null,"price":10,"id":2,"createDate":1572877319000,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/01955e29-26c5-440b-b4b1-39057ce4f756.jpg","modifyDate":1573372966000,"memberPrice":null,"isDelete":0,"noteId":2,"minGradeDict":null,"sort":1,"userId":"2d09be3c-db42-439b-91ac-cbc4af23b82d","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/73e0705f-cb0c-4eb3-9129-f0a1e2144f99.mp3","commentNum":0,"listeningTime":null,"detail":null},{"isLike":1,"minGrade":null,"classifyId":"1","countView":0,"pageSize":null,"collectNum":1,"themeId":null,"title":"不爱我就别伤害我 - 杨顺高、凯小晴","content":"不爱我就别伤害我 - 杨顺高、凯小晴","duration":253,"isNobleEquity":1,"isFree":"0","pageNo":null,"price":10,"id":3,"createDate":1572877352000,"image":"https://hxzoss.oss-cn-beijing.aliyuncs.com/161e4997-c9af-40a1-ac32-441096eb027c.jpg","modifyDate":1573372966000,"memberPrice":null,"isDelete":0,"noteId":3,"minGradeDict":null,"sort":1,"userId":"2d09be3c-db42-439b-91ac-cbc4af23b82d","url":"https://hxzoss.oss-cn-beijing.aliyuncs.com/f3cf4d81-609b-4ced-b8f5-c230754075c4.mp3","commentNum":0,"listeningTime":15,"detail":null}]
     */

    private String total;
    private List<ListBean> list=new ArrayList<>();

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
         * isLike : 1
         * minGrade : null
         * classifyId : 1,2,3,4,5,6
         * countView : 2
         * pageSize : null
         * collectNum : 1
         * themeId : 2
         * title : 百花香 - 魏新雨
         * content : 百花香 - 魏新雨 付费音频 试听10s 价格5
         * duration : 167
         * isNobleEquity : 1
         * isFree : 0
         * pageNo : null
         * price : 5
         * id : 1
         * createDate : 1572877079000
         * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/fda17c94-f8b9-4163-bb45-187d406cf8e6.jpg
         * modifyDate : 1573372966000
         * memberPrice : null
         * isDelete : 0
         * noteId : 1
         * minGradeDict : null
         * sort : 1
         * userId : 2d09be3c-db42-439b-91ac-cbc4af23b82d
         * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/6f15e521-2a94-42b4-95a7-da5db4edec9a.mp3
         * commentNum : 0
         * listeningTime : 10
         * detail : null
         */

        private String isLike;
        private String minGrade;
        private String classifyId;
        private String countView;
        private String pageSize;
        private String collectNum;
        private String themeId;
        private String title;
        private String content;
        private String duration;
        private String isNobleEquity;
        private String isFree;
        private String pageNo;
        private String price;
        private String id;
        private long createDate;
        private String image;
        private long modifyDate;
        private String memberPrice;
        private String isDelete;
        private String noteId;
        private String minGradeDict;
        private String sort;
        private String userId;
        private String url;
        private String commentNum;
        private String listeningTime;
        private String detail;

        // 辅助收藏字段
        private boolean isHelperCollect = true;

        public boolean isHelperCollect() {
            return isHelperCollect;
        }

        public void setHelperCollect(boolean helperCollect) {
            isHelperCollect = helperCollect;
        }

        public String getIsLike() {
            return isLike;
        }

        public void setIsLike(String isLike) {
            this.isLike = isLike;
        }

        public String getMinGrade() {
            return minGrade;
        }

        public void setMinGrade(String minGrade) {
            this.minGrade = minGrade;
        }

        public String getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(String classifyId) {
            this.classifyId = classifyId;
        }

        public String getCountView() {
            return countView;
        }

        public void setCountView(String countView) {
            this.countView = countView;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(String collectNum) {
            this.collectNum = collectNum;
        }

        public String getThemeId() {
            return themeId;
        }

        public void setThemeId(String themeId) {
            this.themeId = themeId;
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

        public String getIsNobleEquity() {
            return isNobleEquity;
        }

        public void setIsNobleEquity(String isNobleEquity) {
            this.isNobleEquity = isNobleEquity;
        }

        public String getIsFree() {
            return isFree;
        }

        public void setIsFree(String isFree) {
            this.isFree = isFree;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(long modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(String memberPrice) {
            this.memberPrice = memberPrice;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getNoteId() {
            return noteId;
        }

        public void setNoteId(String noteId) {
            this.noteId = noteId;
        }

        public String getMinGradeDict() {
            return minGradeDict;
        }

        public void setMinGradeDict(String minGradeDict) {
            this.minGradeDict = minGradeDict;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getListeningTime() {
            return listeningTime;
        }

        public void setListeningTime(String listeningTime) {
            this.listeningTime = listeningTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
