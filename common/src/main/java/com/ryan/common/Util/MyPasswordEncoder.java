package com.ryan.common.Util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * PasswordEncoder 加密解密
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    /**
     * 加密
     *
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    /**
     * 匹配是否一致
     *
     * @param charSequence    用户输入的密码
     * @param encodedPassword 数据库查询到的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
    }
}
