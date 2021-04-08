package com.ryan.server.controller;

import com.ryan.common.dao.R;
import com.ryan.common.email.Dao.SendMailParam;
import com.ryan.server.config.SimpleMailSender;
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
    SimpleMailSender simpleMailSender;

    @ApiOperation("发送")
    @PostMapping("/send")
    public R test(@RequestBody SendMailParam sendMailParam) {
        String msg = simpleMailSender.sendMail(sendMailParam);
        if (!msg.contains("成功")) {
            return R.error(msg);
        }
        return R.ok(msg);
    }

    @ApiOperation("发送")
    @PostMapping("/sendHTML")
    public R html(@RequestBody SendMailParam sendMailParam) {
        sendMailParam.setContent(html);
        String msg = simpleMailSender.sendMailHtml(sendMailParam);
        if (!msg.contains("成功")) {
            return R.error(msg);
        }
        return R.ok(msg);
    }

    private String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title></title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"info-top\" style=\"padding: 15px 25px;\n" +
            "\t\t\t\t                                     border-top-left-radius: 10px;\n" +
            "\t\t\t\t                                     border-top-right-radius: 10px;\n" +
            "\t\t\t\t                                     background: burlywood;\n" +
            "\t\t\t\t                                     color: crimson;\n" +
            "\t\t\t\t                                     line-height: 10px;\">\n" +
            "    <div style=\"font-size: 18px\" ><b>信 息 认 证</b></div>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"info-wrap\" style=\"border-bottom-left-radius: 10px;\n" +
            "\t\t\t\t                                  border-bottom-right-radius: 10px;\n" +
            "\t\t\t\t                                  border:1px solid #ddd;\n" +
            "\t\t\t\t                                  overflow: hidden;\n" +
            "\t\t\t\t                                  padding: 15px 15px 20px;\">\n" +
            "    <div class=\"tips\" style=\"padding:15px;\">\n" +
            "        <p style=\" list-style: 160%; margin: 10px 0;\"><h3>Hi</h3>以下信息请您仔细阅读:</p>\n" +
            "        <p style=\" list-style: 160%; margin: 10px 0;\">&nbsp;&nbsp;{0}</p>\n" +
            "    </div>\n" +
            "    <div class=\"time\" style=\"text-align: right; color: #999; padding: 0 15px 15px;\">{1}</div><br>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";


}
