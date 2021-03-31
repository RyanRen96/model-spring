package com.ryan.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class DynamicConfig {

    @Value("${oauth-url}")
    private String oauthUrl;

    @Value("${logout-url.address}")
    private String logoutUrl;

    @Value("${captcha.is-open}")
    private boolean isOpenCaptcha;

    @Value("${lock-login.is-open}")
    private boolean isOpenLockLogin;

    @Value("${single-device-login.is-open}")
    private boolean isOpenSingleDeviceLogin;

    @Value("${file-auth.is-open}")
    private boolean isOpenFileAuth;

    @Value("${sms.send-type}")
    private String smsSendType;


    public String getOauthUrl() {
        return oauthUrl;
    }

    public void setOauthUrl(String oauthUrl) {
        this.oauthUrl = oauthUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public boolean isOpenCaptcha() {
        return isOpenCaptcha;
    }

    public void setOpenCaptcha(boolean openCaptcha) {
        isOpenCaptcha = openCaptcha;
    }

    public boolean isOpenLockLogin() {
        return isOpenLockLogin;
    }

    public void setOpenLockLogin(boolean openLockLogin) {
        isOpenLockLogin = openLockLogin;
    }

    public boolean isOpenSingleDeviceLogin() {
        return isOpenSingleDeviceLogin;
    }

    public void setOpenSingleDeviceLogin(boolean openSingleDeviceLogin) {
        isOpenSingleDeviceLogin = openSingleDeviceLogin;
    }

    public boolean isOpenFileAuth() {
        return isOpenFileAuth;
    }

    public void setOpenFileAuth(boolean openFileAuth) {
        isOpenFileAuth = openFileAuth;
    }

    public String getSmsSendType() {
        return smsSendType;
    }

    public void setSmsSendType(String smsSendType) {
        this.smsSendType = smsSendType;
    }

}
