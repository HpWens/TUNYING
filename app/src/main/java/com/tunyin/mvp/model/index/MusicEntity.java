package com.tunyin.mvp.model.index;

public class MusicEntity {

    /**
     * image : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/1.jpg
     * minGrade : 2
     * modifyDate : 1570500995000
     * memberPrice : 15
     * classifyId : 1
     * isDelete : 0
     * countView : 0
     * collectNum : 0
     * title : 那女孩对我说 付费
     * content : 这个是简介
     * url : https://hxzoss.oss-cn-beijing.aliyuncs.com/tunyinmp3/%E9%82%A3%E5%A5%B3%E5%AD%A9%E5%AF%B9%E6%88%91%E8%AF%B4%20-%20Uu.mp3
     * isNobleEquity : 1
     * commentNum : 0
     * isFree : 0
     * price : 20
     * id : 3
     * createDate : 1570500993000
     */

    private String image;
    private String minGrade;
    private long modifyDate;
    private String memberPrice;
    private String classifyId;
    private String isDelete;
    private String countView;
    private String collectNum;
    private String title;
    private String content;
    private String url;
    private String isNobleEquity;
    private String commentNum;
    private String isFree;
    private String price;
    private String id;
    private long createDate;
    private String listeningTime;
    private String listening_time;
    private String durationDict;
    private boolean isListen;//是否购买
    private boolean isBuyCatlog;//是否购买目录
    private String themeId;
    private String catalogPrice;
    private String isCollect;

    public String getListening_time() {
        return listening_time;
    }

    public void setListening_time(String listening_time) {
        this.listening_time = listening_time;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(String catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public boolean isBuyCatlog() {
        return isBuyCatlog;
    }

    public void setBuyCatlog(boolean buyCatlog) {
        isBuyCatlog = buyCatlog;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public boolean isListen() {
        return isListen;
    }

    public void setListen(boolean listen) {
        isListen = listen;
    }

    public String getListeningTime() {
        return listeningTime;
    }

    public void setListeningTime(String listeningTime) {
        this.listeningTime = listeningTime;
    }

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

    public String getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
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

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getCountView() {
        return countView;
    }

    public void setCountView(String countView) {
        this.countView = countView;
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

    public String getIsNobleEquity() {
        return isNobleEquity;
    }

    public void setIsNobleEquity(String isNobleEquity) {
        this.isNobleEquity = isNobleEquity;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
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
}
