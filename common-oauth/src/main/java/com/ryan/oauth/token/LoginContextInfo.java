package com.ryan.oauth.token;

/**
 * 登录上下文信息
 */
public class LoginContextInfo {

    /**
     * 用户token
     */
    private String token;
    /**
     * 登录标记 token
     */
    private String loginToken;
    /**
     * 登录次数，超过n次时，锁定账户n小时
     */
    private int loginCount;
    /**
     * 登录状态，0-锁定，1-非锁定
     */
    // todo 当一个用户已登录，另一个用户登录同一账户导致锁定后，已登录的用户仍能使用，直到他退出此次登录。单设备的情况下，不用考虑这个问题，因为他都会被挤掉
    private String status;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
