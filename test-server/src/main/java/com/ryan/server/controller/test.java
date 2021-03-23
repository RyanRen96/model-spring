package com.ryan.server.controller;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

public class test {
    @Test
    public void test(){
        String s = SecureUtil.md5("123456");
        System.out.println(s);
    }
}
