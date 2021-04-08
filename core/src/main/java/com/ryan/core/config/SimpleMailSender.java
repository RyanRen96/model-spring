package com.ryan.core.config;

import com.alibaba.excel.EasyExcel;
import com.ryan.common.Util.StringUtil;
import com.ryan.common.email.Dao.SendMailParam;
import com.ryan.common.email.MailSender;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 邮件发送器
 * 需要再application.yml中修改邮件发件人的配置信息
 *
 * @author RyanRen
 * @date 2021/04/08
 */
@Component
public class SimpleMailSender implements MailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(SendMailParam sendMailParam) {
        if (isEmpty(sendMailParam) != null) {
            return isEmpty(sendMailParam);
        }
        MimeMessage smm = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = getMimeMessageHelper(sendMailParam, smm);
            helper.setText(sendMailParam.getContent(), false);
            javaMailSender.send(smm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "邮件发送失败";
        }
        return "邮件发送成功";
    }

    @Override
    public String sendMail(SendMailParam sendMailParam, List<T> data, Class<T> entity) {
        if (isEmpty(sendMailParam) != null) {
            return isEmpty(sendMailParam);
        }
        MimeMessage smm = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = getMimeMessageHelper(sendMailParam, smm);
            helper.setText(sendMailParam.getContent(), false);
            addExcelFile(data, entity, helper);
            javaMailSender.send(smm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "邮件发送失败";
        }
        return "邮件发送成功";
    }

    @Override
    public String sendMailHtml(SendMailParam sendMailParam) {
        MimeMessage smm = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = getMimeMessageHelper(sendMailParam, smm);
            helper.setText(sendMailParam.getContent(), true);
            javaMailSender.send(smm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "邮件发送失败";
        }
        return "邮件发送成功";
    }

    @Override
    public String sendMailHtml(SendMailParam sendMailParam, List<T> data, Class<T> entity) {
        MimeMessage smm = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = getMimeMessageHelper(sendMailParam, smm);
            helper.setText(sendMailParam.getContent(), true);
            addExcelFile(data, entity, helper);
            javaMailSender.send(smm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "邮件发送失败";
        }
        return "邮件发送成功";
    }

    private String isEmpty(SendMailParam sendMailParam){
        if (StringUtil.isEmpty(sendMailParam.getTo())) {
            return "收件人为空";
        }
        if (StringUtil.isEmpty(sendMailParam.getTitle())) {
            return "邮件标题为空";
        }
        if (StringUtil.isEmpty(sendMailParam.getFrom())) {
            return "发件人为空";
        }
        return null;
    }

    private MimeMessageHelper getMimeMessageHelper(SendMailParam sendMailParam, MimeMessage smm) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(smm, true);
        helper.setSubject(sendMailParam.getTitle());
        helper.setFrom(sendMailParam.getFrom());
        helper.setTo(sendMailParam.getTo());
        return helper;
    }

    private void addExcelFile(List<T> data, Class<T> entity, MimeMessageHelper helper) throws MessagingException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        EasyExcel.write(bao).head(entity).sheet(0).doWrite(data);
        byte[] bytes = bao.toByteArray();
        helper.addAttachment("data.xlsx", new ByteArrayResource(bytes));
    }

}
