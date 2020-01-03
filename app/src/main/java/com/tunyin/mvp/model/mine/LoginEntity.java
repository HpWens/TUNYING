package com.tunyin.mvp.model.mine;

public class LoginEntity {


    /**
     * content : {"token":"f71cf13b00bd4e7cb2629f6e44119019","userId":"da3965a525fe44dfa347965235cb57aa","username":"15659879896","phone":"15080311579","loginTime":1570837567822,"expiresTime":1573429567822}
     * code : 200
     * desc : 操作成功
     */

        /**
         * token : f71cf13b00bd4e7cb2629f6e44119019
         * userId : da3965a525fe44dfa347965235cb57aa
         * username : 15659879896
         * phone : 15080311579
         * loginTime : 1570837567822
         * expiresTime : 1573429567822
         */

        private String token;
        private String userId;
        private String username;
        private String nickName;
        private String headUrl;

        private String phone;
        private long loginTime;
        private long expiresTime;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(long loginTime) {
            this.loginTime = loginTime;
        }

        public long getExpiresTime() {
            return expiresTime;
        }

        public void setExpiresTime(long expiresTime) {
            this.expiresTime = expiresTime;
        }
}
