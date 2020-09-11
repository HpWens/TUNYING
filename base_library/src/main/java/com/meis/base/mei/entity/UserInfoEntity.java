package com.meis.base.mei.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class UserInfoEntity implements Serializable, MultiItemEntity {

    public String id;
    // userId
    public String userId;
    public String token = "";
    public String backPageUrl;
    public String headerUrl = "";
    public String nickName = "";
    public String briefIntroduction = "";
    public String sex;
    public String birthday = "";
    public String provinces = "";
    public String city = "";
    public String school;
    public String account;
    public boolean isFollow;
    public List<String> picUrls;

    // 动态 粉丝 关注 活跃
    public int post;
    public int fans;
    public int follow;
    public int active;

    public int itemType;

    public UserInfoEntity() {

    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
