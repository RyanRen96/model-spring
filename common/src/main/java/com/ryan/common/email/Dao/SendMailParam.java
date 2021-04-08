package com.ryan.common.email.Dao;

import lombok.Data;
import java.io.File;

/**
 * 邮件参数
 *
 * @author RyanRen
 * @date 2021/04/08
 */
@Data
public class SendMailParam {

    /**
     * 发件人邮箱
     */
    private String from;

    /**
     * 收件人邮箱
     */
    private String to;

    /**
     * 抄送邮箱
     */
    private String cc;

    /**
     * 邮件标题
     */
    private String title;

    /**
     * 邮件内容
     */
    private String content;

}
