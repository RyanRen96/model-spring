package com.ryan.portal.controller;


import com.ryan.oauth.token.UserContextInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected UserContextInfo getUser() {
        return (UserContextInfo) SecurityUtils.getSubject().getPrincipal();
    }

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
