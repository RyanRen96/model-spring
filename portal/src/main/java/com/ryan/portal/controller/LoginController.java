package com.ryan.portal.controller;

import com.ryan.common.Util.CaptchaUtil;
import com.ryan.common.Util.StringUtil;
import com.ryan.common.dao.CodeDefined;
import com.ryan.common.dao.R;
import com.ryan.common.dao.RException;
import com.ryan.db.dao.RedisCacheService;
import com.ryan.db.entity.UsersEntity;
import com.ryan.db.service.UsersService;
import com.ryan.oauth.OAuth2TokenUsernamePassword;
import com.ryan.oauth.token.LoginFilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Api("登录功能接口")
@RestController
@RequestMapping("login")
public class LoginController extends AbstractController{

    @Autowired
    private UsersService usersService;

    @Autowired
    private LoginFilterService loginFilterService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取验证码
     *
     * @param httpResponse
     * @throws IOException
     */
    @ApiOperation("获取验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        // todo 暂时这样写，后续能否有更优雅的写法？
        String customSessionToken = httpRequest.getHeader("customSessionToken");
//        if (StringUtil.isEmpty(customSessionToken)) {
//            throw new RException(CodeDefined.ERROR_CAPTCHA_LACK_PARAM);
//        }

        // 禁止浏览器缓存
        httpResponse.setHeader("Expires", "-1");
        httpResponse.setHeader("Cache-Control", "no-cache");
        httpResponse.setHeader("Pragma", "-1");
        httpResponse.setContentType("image/jpeg");

        // 生成校验码
        String captcha = CaptchaUtil.getCaptcha(4);
        // 以自定义的token为key保存验证码到redis，以代替session
        loginFilterService.createCustomSession(customSessionToken, captcha);
        // 生成校验码图像
        BufferedImage captchaImage = CaptchaUtil.getCaptchaImg(captcha);

        ServletOutputStream outStream = httpResponse.getOutputStream();
        ImageIO.write(captchaImage, "jpeg", outStream);
        outStream.close();
    }


    @ApiOperation("账号密码登陆")
    @PostMapping("/loginByUsername")
    public R loginByUsername(@RequestBody UsersEntity usersEntity) {
        UsersEntity user = usersService.getInfoByUsername(usersEntity);
        // 找不到用户数据 或 账号已经删除
        if (user == null) {
            return R.error(CodeDefined.ERROR_USER_OR_PASS);
        }
        //验证密码
        if (!usersService.checkPassword(user.getPassword(), usersEntity.getPassword())) {
            return R.error(CodeDefined.ERROR_USER_OR_PASS);
        }
        // 验证码校验
        String customSessionToken = usersEntity.getCustomSessionToken();
//        String captcha = (String) redisTemplate.opsForValue().get("VerificationCode");
        if (!loginFilterService.verifyRedisCaptcha(customSessionToken, usersEntity.getCaptcha())) {
            return R.error(CodeDefined.ERROR_CAPTCHA);
        }

        OAuth2TokenUsernamePassword oAuth2TokenUsernamePassword = new OAuth2TokenUsernamePassword(user.getUsername(), user.getPassword());
        String token = oAuth2TokenUsernamePassword.getPrincipal();

        return R.ok().put("token", token);
    }

    @ApiOperation("注册")
    @PostMapping("/regist")
    public R regist(@RequestBody UsersEntity usersEntity) {
        usersService.insert(usersEntity);
        return R.ok();
    }


}
