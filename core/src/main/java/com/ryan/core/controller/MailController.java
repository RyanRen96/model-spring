package com.ryan.core.controller;

import com.ryan.common.dao.R;
import com.ryan.common.email.Dao.SendMailParam;
import com.ryan.core.config.SimpleMailSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("发送邮件接口")
@RestController
@RequestMapping("email")
public class MailController {

    @Autowired
    private SimpleMailSender simpleMailSender;

    @ApiOperation("发送")
    @PostMapping("/send")
    public R test(@RequestBody SendMailParam sendMailParam) {
        String msg = simpleMailSender.sendMail(sendMailParam);
        if(!msg.contains("成功")){
            return R.error(msg);
        }
        return R.ok(msg);
    }

    @ApiOperation("发送")
    @PostMapping("/sendHTML")
    public R html(@RequestBody SendMailParam sendMailParam) {
        String msg = simpleMailSender.sendMailHtml(sendMailParam);
        if(!msg.contains("成功")){
            return R.error(msg);
        }
        return R.ok(msg);
    }
}
