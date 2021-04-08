package com.ryan.oauth;

import com.ryan.oauth.utils.TokenGenerator;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 */
public class OAuth2TokenUsernamePassword implements AuthenticationToken {

    private String token;

    private String username;

    private String password;

    public OAuth2TokenUsernamePassword(String username, String password) {


        this.token = TokenGenerator.generateValue();

        this.username = username;
        this.password = password;
    }

    public OAuth2TokenUsernamePassword(String token) {

        this.token = token;

    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



    /**
     * 判断是否是登陆时创建
     *
     * @return
     * @ result eg:   dbPassword  和 password 不为空 : [true]      //第一次正常流程登陆
     * dbPassword  和 password 都为空 ：[false]     //TOKEN验证流程
     */
    public boolean IsInitFlag() {

        return password != null;
    }
}
