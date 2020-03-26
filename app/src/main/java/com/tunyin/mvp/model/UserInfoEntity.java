package com.tunyin.mvp.model;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by yiang on 2018/4/29.
 */

public class UserInfoEntity {


    /**
     * birthdate :
     * education :
     * nation :
     * headUrl : http://p655k0tfe.bkt.clouddn.com/_1527529101206
     * jobDate :
     * placeId :
     * pageSize :
     * drawMoneyDay : 5000
     * cityId : 401
     * type : 0
     * password : 96e79218965eb72c92a549dd5a330112
     * cityName : 重庆
     * balance : 0
     * identity : 3
     * pageNo :
     * jobYear :
     * servicePriceRules : [{"days":"28","guidePrice":"12000","ruleId":11},{"days":"42","guidePrice":"12500","ruleId":12}]
     * homeStatus :
     * id : 10
     * rewardMoney : 0
     * createDate : 2018-05-06 21:13:49
     * wechatAccount :
     * isBusy : 0
     * servePrice :
     * serviceQuantity :
     * drawMoney : 0
     * modifyDate : 2018-05-27 14:18:30
     * referrerId : cefd99e4-f84f-4096-9934-110983554d10
     * nickName : lz
     * jobCityName :
     * userId : 3e5873cb-a290-4568-b55f-90534899d74c
     * alipayAccount : jfjjfj
     * jobCityId :
     * isLeisure :
     * thirdpartyUserId :
     * phone : 18850528382
     * idcard : 346646
     * name : 点咯
     * placeName :
     * payPassword :
     * age :
     * maritalStatus :
     * status : 1
     * username : 18850528382
     */

    private String birthdate;
    private String nation;
    private String headUrl;
    private String portraitImage;
    private String jobDate;
    private String placeId;
    private String pageSize;
    private String drawMoneyDay;
    private String cityId;
    private int type;
    private String password;
    private String cityName = "";
    private String balance;
    private String identity;
    private String pageNo;
    private String provinceId;
    private String placeProvinceName;
    private String provinceName = "";
    private String nativeAreaCode;
    private String nativeProvinceName = "";
    private String nativeCityName = "";
    private String areaCode = "";
    private String address = "";
    private String contactsName = "";
    private String contactsPhone = "";
    private String language = "";
    private String education = "-1";
    private String jobYear;
    private String homeStatus;
    private int id;
    private String rewardMoney;
    private String createDate;
    private int takecareBabies;
    private String wechatAccount;
    private String isBusy;
    private String servePrice;
    private String serviceQuantity;
    private String drawMoney;
    private String modifyDate;
    private String referrerId;
    private String nickName;
    private String jobCityName;
    private String userId;
    private String alipayAccount;
    private String jobCityId;
    private String calendar;
    private String isLeisure;
    private String thirdpartyUserId;
    private String phone;
    private String idcard = "";
    private String name = "";
    private String placeName;
    private String payPassword;
    private String age;
    private String maritalStatus;
    private int status;
    private String username;
    private String userName = "";
    private List<ServicePriceRulesBean> servicePriceRules;
    private int level;
    /**
     * education : 5
     * placeId : 502
     * cityId : 502
     * skillLevel : null
     * matronStatus : 2
     * phoneModel : google Pixel 2 XL
     * balance : 59089.0
     * areaName :
     * takecareBabies : 11
     * nativeCityCode : 1101
     * serviceQuantity : 12
     * contactsRelation : null
     * nzbbToken :
     * languageDictList : [{"code":1,"text":"普通话"},{"code":2,"text":"英语"},{"code":3,"text":"闽南语"},{"code":4,"text":"粤语"},{"code":5,"text":"四川话"}]
     * moonRegistrationId : 141fe1da9ef73e608fa
     * interviewEvaluate : null
     * servicePrice : null
     * maternal :
     * status : 1
     * nativeProvinceCode : 11
     * nzbbTokenExpires :
     * specialServiceExp : null
     * nzbbTokenCreateTime :
     * cityCode : 3502
     * jobDate : null
     * remark :
     * drawMoneyDay : 5000.0
     * motherRegistrationId :
     * educationDictList : [{"code":1,"text":"小学"},{"code":2,"text":"初中"},{"code":3,"text":"高中"},{"code":4,"text":"大学"},{"code":5,"text":"硕士"},{"code":6,"text":"博士"}]
     * speciality : null
     * identity : 3
     * jobYear : null
     * homeStatus : null
     * rewardMoney : 0.0
     * calendar : null
     * jobProvinceName :
     * drawMoney : 0.0
     * serviceContents : null
     * provinceCode : 35
     * zodiacName :
     * firstOrderId :
     * isLeisure : 0
     * areaId :
     * phoneOs : android 9
     * age : 21
     */

    private Object skillLevel;
    private int matronStatus;
    private String phoneModel;
    private String areaName;
    private String nativeCityCode;
    private Object contactsRelation;
    private String nzbbToken;
    private String moonRegistrationId;
    private Object interviewEvaluate;
    private Object servicePrice;
    private String maternal;
    private String nativeProvinceCode;
    private String nzbbTokenExpires;
    private Object specialServiceExp;
    private String nzbbTokenCreateTime;
    private String cityCode;
    private String remark;
    private String motherRegistrationId;
    private Object speciality;
    private String jobProvinceName;
    private Object serviceContents;
    private String provinceCode = "";
    private String zodiacName;
    private String firstOrderId;
    private String areaId;
    private String phoneOs;
//    private List<LanguageDictListBean> languageDictList;
//    private List<EducationDictListBean> educationDictList;

    private int cardStatus;




    public int getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(int cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBirthdate() {
        if (birthdate == null) return "";
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getPlaceProvinceName() {
        return placeProvinceName;
    }

    public void setPlaceProvinceName(String placeProvinceName) {
        this.placeProvinceName = placeProvinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getNativeAreaCode() {
        if (nativeAreaCode == null) return "";
        return nativeAreaCode;
    }

    public void setNativeAreaCode(String nativeAreaCode) {
        this.nativeAreaCode = nativeAreaCode;
    }

    public String getNativeProvinceName() {
        return nativeProvinceName;
    }

    public void setNativeProvinceName(String nativeProvinceName) {
        this.nativeProvinceName = nativeProvinceName;
    }

    public String getNativeCityName() {
        return nativeCityName;
    }

    public void setNativeCityName(String nativeCityName) {
        this.nativeCityName = nativeCityName;
    }

    public String getAreaCode() {
        if (areaCode == null) return "";
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getLanguage() {
        if (language == null) return "";
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getEducation() {
        return education;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public String getPortraitImage() {
        return portraitImage;
    }

    public void setPortraitImage(String portraitImage) {
        this.portraitImage = portraitImage;
    }

    public int getTakecareBabies() {
        return takecareBabies;
    }

    public void setTakecareBabies(int takecareBabies) {
        this.takecareBabies = takecareBabies;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getPlaceId() {
        if (TextUtils.isEmpty(placeId)) {
            return areaCode;
        }
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getDrawMoneyDay() {
        return drawMoneyDay;
    }

    public void setDrawMoneyDay(String drawMoneyDay) {
        this.drawMoneyDay = drawMoneyDay;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getJobYear() {
        return jobYear;
    }

    public void setJobYear(String jobYear) {
        this.jobYear = jobYear;
    }

    public String getHomeStatus() {
        return homeStatus;
    }

    public void setHomeStatus(String homeStatus) {
        this.homeStatus = homeStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(String isBusy) {
        this.isBusy = isBusy;
    }

    public String getServePrice() {
        return servePrice;
    }

    public void setServePrice(String servePrice) {
        this.servePrice = servePrice;
    }

    public String getServiceQuantity() {
        return serviceQuantity;
    }

    public void setServiceQuantity(String serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }

    public String getDrawMoney() {
        return drawMoney;
    }

    public void setDrawMoney(String drawMoney) {
        this.drawMoney = drawMoney;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getJobCityName() {
        return jobCityName;
    }

    public void setJobCityName(String jobCityName) {
        this.jobCityName = jobCityName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getJobCityId() {
        return jobCityId;
    }

    public void setJobCityId(String jobCityId) {
        this.jobCityId = jobCityId;
    }

    public String getIsLeisure() {
        return isLeisure;
    }

    public void setIsLeisure(String isLeisure) {
        this.isLeisure = isLeisure;
    }

    public String getThirdpartyUserId() {
        return thirdpartyUserId;
    }

    public void setThirdpartyUserId(String thirdpartyUserId) {
        this.thirdpartyUserId = thirdpartyUserId;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        if (name == null) {
            if (userName == null) {
                if (username == null) {
                    if (nickName == null) {
                        return "";
                    }
                    return username;
                }
                return username;
            }
            return userName;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ServicePriceRulesBean> getServicePriceRules() {
        return servicePriceRules;
    }

    public void setServicePriceRules(List<ServicePriceRulesBean> servicePriceRules) {
        this.servicePriceRules = servicePriceRules;
    }

    public Object getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Object skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getMatronStatus() {
        return matronStatus;
    }

    public void setMatronStatus(int matronStatus) {
        this.matronStatus = matronStatus;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getNativeCityCode() {
        return nativeCityCode;
    }

    public void setNativeCityCode(String nativeCityCode) {
        this.nativeCityCode = nativeCityCode;
    }

    public Object getContactsRelation() {
        return contactsRelation;
    }

    public void setContactsRelation(Object contactsRelation) {
        this.contactsRelation = contactsRelation;
    }

    public String getNzbbToken() {
        return nzbbToken;
    }

    public void setNzbbToken(String nzbbToken) {
        this.nzbbToken = nzbbToken;
    }

    public String getMoonRegistrationId() {
        return moonRegistrationId;
    }

    public void setMoonRegistrationId(String moonRegistrationId) {
        this.moonRegistrationId = moonRegistrationId;
    }

    public Object getInterviewEvaluate() {
        return interviewEvaluate;
    }

    public void setInterviewEvaluate(Object interviewEvaluate) {
        this.interviewEvaluate = interviewEvaluate;
    }

    public Object getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Object servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getMaternal() {
        return maternal;
    }

    public void setMaternal(String maternal) {
        this.maternal = maternal;
    }

    public String getNativeProvinceCode() {
        return nativeProvinceCode;
    }

    public void setNativeProvinceCode(String nativeProvinceCode) {
        this.nativeProvinceCode = nativeProvinceCode;
    }

    public String getNzbbTokenExpires() {
        return nzbbTokenExpires;
    }

    public void setNzbbTokenExpires(String nzbbTokenExpires) {
        this.nzbbTokenExpires = nzbbTokenExpires;
    }

    public Object getSpecialServiceExp() {
        return specialServiceExp;
    }

    public void setSpecialServiceExp(Object specialServiceExp) {
        this.specialServiceExp = specialServiceExp;
    }

    public String getNzbbTokenCreateTime() {
        return nzbbTokenCreateTime;
    }

    public void setNzbbTokenCreateTime(String nzbbTokenCreateTime) {
        this.nzbbTokenCreateTime = nzbbTokenCreateTime;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMotherRegistrationId() {
        return motherRegistrationId;
    }

    public void setMotherRegistrationId(String motherRegistrationId) {
        this.motherRegistrationId = motherRegistrationId;
    }

    public Object getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Object speciality) {
        this.speciality = speciality;
    }

    public String getJobProvinceName() {
        return jobProvinceName;
    }

    public void setJobProvinceName(String jobProvinceName) {
        this.jobProvinceName = jobProvinceName;
    }

    public Object getServiceContents() {
        return serviceContents;
    }

    public void setServiceContents(Object serviceContents) {
        this.serviceContents = serviceContents;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getZodiacName() {
        return zodiacName;
    }

    public void setZodiacName(String zodiacName) {
        this.zodiacName = zodiacName;
    }

    public String getFirstOrderId() {
        return firstOrderId;
    }

    public void setFirstOrderId(String firstOrderId) {
        this.firstOrderId = firstOrderId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPhoneOs() {
        return phoneOs;
    }

    public void setPhoneOs(String phoneOs) {
        this.phoneOs = phoneOs;
    }

//    public List<LanguageDictListBean> getLanguageDictList() {
//        return languageDictList;
//    }

//    public void setLanguageDictList(List<LanguageDictListBean> languageDictList) {
//        this.languageDictList = languageDictList;
//    }

//    public List<EducationDictListBean> getEducationDictList() {
//        return educationDictList;
//    }

//    public void setEducationDictList(List<EducationDictListBean> educationDictList) {
//        this.educationDictList = educationDictList;
//    }

    public static class ServicePriceRulesBean {
        /**
         * days : 28
         * guidePrice : 12000
         * ruleId : 11
         */

        private String days;
        private String guidePrice;
        private int ruleId;
        private String totalMoney;
        private String deposit;

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getGuidePrice() {
            return guidePrice;
        }

        public void setGuidePrice(String guidePrice) {
            this.guidePrice = guidePrice;
        }

        public int getRuleId() {
            return ruleId;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public void setRuleId(int ruleId) {
            this.ruleId = ruleId;
        }
    }

//    public static class LanguageDictListBean {
//        /**
//         * code : 1
//         * text : 普通话
//         */
//
//        private int code;
//        private String text;
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public String getText() {
//            return text;
//        }
//
//        public void setText(String text) {
//            this.text = text;
//        }
//    }

//    public static class EducationDictListBean {
//        /**
//         * code : 1
//         * text : 小学
//         */
//
//        private int code;
//        private String text;
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public String getText() {
//            return text;
//        }
//
//        public void setText(String text) {
//            this.text = text;
//        }
//    }
}
