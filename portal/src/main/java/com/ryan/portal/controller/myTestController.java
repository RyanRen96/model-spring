package com.ryan.portal.controller;

import com.ryan.common.dao.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myTestController {

    @PostMapping("/test")
    public R loginByUsername() {
        String s = "成功";
        return R.ok(s);
    }
}
