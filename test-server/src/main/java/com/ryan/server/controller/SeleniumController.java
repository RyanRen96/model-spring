package com.ryan.server.controller;

import com.ryan.common.dao.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Selenium接口")
@RestController
@RequestMapping("selenium")
public class SeleniumController {

    @ApiOperation("test")
    @GetMapping("/test")
    public R test() {
        System.setProperty("webdriver.chrome.driver", "/Users/renlinxuan/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://baidu.com");

        String title = driver.getTitle();
        System.out.println(title);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();
        return R.ok();
    }
}
