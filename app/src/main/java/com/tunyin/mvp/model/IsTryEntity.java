package com.tunyin.mvp.model;

/**
 * create by wangrongchao
 * on 2019/12/15
 **/
public class IsTryEntity {
    public IsTryEntity(boolean isTry, boolean isPlaying,String imgUrl) {
        this.isTry = isTry;
        this.isPlaying = isPlaying;
        this.imageUrl=imgUrl;
    }

    private boolean isTry;
    private boolean isPlaying;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isTry() {
        return isTry;
    }

    public void setTry(boolean aTry) {
        isTry = aTry;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
