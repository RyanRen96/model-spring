package com.ryan.oauth.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 登录过滤（验证码及密码错误锁定）相关实现
 * 密码错误锁定以用户名作为隔离，不管单设备还是多设备，只要错误总次数达到n次，就锁定该账户
 */
@Service
public class LoginFilterService {

    // 用户登录状态2小时后过期
    private final static int EXPIRE = 60 * 2;
    // 验证码2分钟后过期
    private final static int CAPTCHA_EXPIRE = 2;
    // 账户锁定30分钟后解锁
    private final static int LOCK_EXPIRE = 30;
    private final String USER_PREFIX = "USERNAME-TOKEN:";
    private final String CUSTOM_SESSION_PREFIX = "CUSTOM-SESSION:";

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 生成一个token并存入redis，作为本次页面请求的标记，以代替session
     *
     * @param captcha
     */
    public void createCustomSession(String customSessionToken, String captcha) {
        refreshRedisCaptcha(customSessionToken, captcha);
    }

    /**
     * 以customSessionToken为key，验证码为value保存一条记录
     *
     * @param customSessionToken
     * @param captcha
     */
    public void refreshRedisCaptcha(String customSessionToken, String captcha) {
        setEntity2Redis(CUSTOM_SESSION_PREFIX + customSessionToken, captcha, CAPTCHA_EXPIRE);
    }

    /**
     * 比对验证码是否正确，若正确，则清除该customSessionToken的记录
     *
     * @param customSessionToken
     * @param captcha
     * @return
     */
    public boolean verifyRedisCaptcha(String customSessionToken, String captcha) {
        String captchaInRedis = (String) getEntity4Redis(CUSTOM_SESSION_PREFIX + customSessionToken);
        if (captchaInRedis != null && captcha.toUpperCase().equals(captchaInRedis.toUpperCase())) {
            cleanRedisCaptcha(customSessionToken);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除redis中的customSessionToken为key的记录
     *
     * @param customSessionToken
     */
    public void cleanRedisCaptcha(String customSessionToken) {
        removeEntity4Redis(CUSTOM_SESSION_PREFIX + customSessionToken);
    }

    /* ---------- LoginContextInfo相关 ---------- */

    /**
     * 根据username，判断LoginContextInfo是否存在
     *
     * @param username
     * @return
     */
    public boolean isLoginContextInfoExist(String username) {
        if (redisTemplate.hasKey(USER_PREFIX + str2Lowercase(username))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据username获取LoginContextInfo
     *
     * @param username
     * @return
     */
    public LoginContextInfo getLoginContextInfo(String username) {
        return (LoginContextInfo) getEntity4Redis(USER_PREFIX + str2Lowercase(username));
    }

    /**
     * 刷新LoginContextInfo的登录次数和状态，第一次登录时，次数为1，状态为1
     * 当尝试再次登录时，次数和状态继承上一次的次数和状态
     *
     * @param username
     * @param token
     * @param loginToken
     */
    public void refreshLoginContextInfo(String username, String token, String loginToken) {
        String usernameLowercase = str2Lowercase(username);
        LoginContextInfo loginContextInfo = new LoginContextInfo();
        loginContextInfo.setToken(token);
        loginContextInfo.setLoginToken(loginToken);
        if (!redisTemplate.hasKey(USER_PREFIX + usernameLowercase)) {
            loginContextInfo.setLoginCount(1);
            loginContextInfo.setStatus("1");
        } else {
            loginContextInfo.setLoginCount(getLoginCount(usernameLowercase));
            loginContextInfo.setStatus(getLoginContextInfo(usernameLowercase).getStatus());
        }
        setEntity2Redis(USER_PREFIX + usernameLowercase, loginContextInfo, EXPIRE);
    }

    /**
     * 根据username获取登录次数
     *
     * @param username
     * @return
     */
    public int getLoginCount(String username) {
        LoginContextInfo loginContextInfo = (LoginContextInfo) getEntity4Redis(USER_PREFIX + str2Lowercase(username));
        return loginContextInfo.getLoginCount();
    }

    /**
     * 根据username刷新登录次数，每错误一次，次数+1（登录错误时调用，redis中以username作为隔离）
     *
     * @param username
     */
    public void refreshLoginCount(String username) {
        String usernameLowercase = str2Lowercase(username);
        LoginContextInfo loginContextInfo = getLoginContextInfo(usernameLowercase);
        loginContextInfo.setLoginCount(loginContextInfo.getLoginCount() + 1);
        setEntity2Redis(USER_PREFIX + usernameLowercase, loginContextInfo);
    }

    /**
     * 根据username刷新LoginContextInfo的状态为1（登录成功时调用）
     *
     * @param username
     */
    public void refreshLoginContextInfoStatus(String username) {
        String usernameLowercase = str2Lowercase(username);
        LoginContextInfo loginContextInfo = getLoginContextInfo(usernameLowercase);
        loginContextInfo.setLoginCount(1);
        loginContextInfo.setStatus("1");
        setEntity2Redis(USER_PREFIX + usernameLowercase, loginContextInfo, EXPIRE);
    }

    /**
     * 根据username锁定账号，更新账号的状态，并重置登录次数（登录失败到达n次调用）
     *
     * @param username
     */
    public void lockLogin(String username) {
        String usernameLowercase = str2Lowercase(username);
        LoginContextInfo loginContextInfo = getLoginContextInfo(usernameLowercase);
        loginContextInfo.setStatus("0");
        loginContextInfo.setLoginCount(1);
        // todo 这样设置锁定时间有问题吗？
        setEntity2Redis(USER_PREFIX + usernameLowercase, loginContextInfo, LOCK_EXPIRE);
    }

    /**
     * 根据username清除LoginContextInfo（登出时调用）
     *
     * @param username
     */
    public void cleanLoginContextInfo(String username) {
        removeEntity4Redis(USER_PREFIX + str2Lowercase(username));
    }

    /**
     * 获取redis中，所有loginFilter前缀的key的数量
     *
     * @param
     * @return
     */
    public int getKeyCount() {
        return redisTemplate.keys(USER_PREFIX + "*").size();
    }


    private void setEntity2Redis(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    private void setEntity2Redis(String key, Object value, int minute) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, minute, TimeUnit.MINUTES);
    }

    private Object getEntity4Redis(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    private void removeEntity4Redis(String key) {
        redisTemplate.delete(key);
    }

    private String str2Lowercase(String str) {
        return str.toLowerCase();
    }

}
