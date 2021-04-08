package com.ryan.common.email;

import com.ryan.common.email.Dao.SendMailParam;
import lombok.NonNull;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * 邮件收发统一接口
 *
 * @author RyanRen
 * @date 2021/04/08
 */

public interface MailSender {

    /**
     * 发送普通邮件
     *
     */
    String sendMail(@NonNull SendMailParam sendMailParam);

    /**
     * 发送普通邮件，含附件，附件内容为List数据
     *
     * @param sendMailParam
     * @param data
     * @param entity
     */
    String sendMail(@NonNull SendMailParam sendMailParam, List<T> data, Class<T> entity);

    /**
     * 发送html的邮件
     *
     * @author RyanRen
     * @date 2021/04/08
     */
    String sendMailHtml(@NonNull SendMailParam sendMailParam);

    /**
     * 发送html邮件，含附件，附件内容为List数据
     *
     * @param sendMailParam
     * @param data
     * @param entity
     */
    String sendMailHtml(@NonNull SendMailParam sendMailParam, List<T> data, Class<T> entity);

}
