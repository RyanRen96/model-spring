package com.ryan.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ryan.common.Util.MyPasswordEncoder;
import com.ryan.common.Util.SnowflakeIdWorker;
import com.ryan.common.dao.CodeDefined;
import com.ryan.common.dao.R;
import com.ryan.db.dao.RedisCacheService;
import com.ryan.db.entity.UserEntity;
import com.ryan.db.entity.UsersEntity;
import com.ryan.db.service.UserService;
import com.ryan.db.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api("测试接口")
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisCacheService redisCacheService;

    @ApiOperation("test")
    @GetMapping("/postman")
    public R test(@RequestParam("createid") String createid) {
        List<UserEntity> list = userService.getList();
        return R.ok().data(list);
    }

    @ApiOperation("page")
    @PostMapping("/page")
    public R page() {
        Page<UserEntity> page = new Page<>(1, 2);
        IPage<UserEntity> userEntityIPage = userService.selectUserPage(page);
        return R.ok().data(userEntityIPage);
    }

    @ApiOperation("delete")
    @PostMapping("/delete")
    public R delete(@RequestBody UserEntity userEntity) {
        userService.deleteById(userEntity);
        return R.ok();
    }

    @ApiOperation("登陆")
    @PostMapping("/loginByUsername")
    public R loginByUsername(@RequestBody UsersEntity usersEntity) {
        UsersEntity user = usersService.getInfoByUsername(usersEntity);
        // 找不到用户数据 或 账号已经删除
        if (user == null) {
            return R.error(CodeDefined.ERROR_USER_OR_PASS);
        }
        if(!usersService.checkPassword(user.getPassword(),usersEntity.getPassword())){
            return R.error(CodeDefined.ERROR_USER_OR_PASS);
        }

        return R.ok();
    }

    @ApiOperation("注册")
    @PostMapping("/regist")
    public R regist(@RequestBody UsersEntity usersEntity) {
        usersService.insert(usersEntity);
        return R.ok();
    }

    @PostMapping("/test1")
    public String hello1(){
        System.out.println("00000000000000000000000");
        UsersEntity a=new UsersEntity();
        a.setId(SnowflakeIdWorker.getId().toString());
        a.setPassword("我就是我，我就是天");
        a.setUsername("天");
        //设置过期时间为5秒
        Long c=5L;
        redisCacheService.set("tian",a);
        //TimeUnit.SECONDS时间的格式为秒
        redisCacheService.setKeyByTime("tian1",a,c, TimeUnit.SECONDS );
        return "装好了";
    }

    @PostMapping("/test2")
    public String hello2(){
        System.out.println("11111111111111111");
        UsersEntity d=(UsersEntity)redisCacheService.get("tian");
        UsersEntity e=(UsersEntity)redisCacheService.get("tian1");
        String cal="";
        if(e!=null) {
            cal=d.getPassword()+e.getPassword();
            return	cal;
        }else{
            return d.getPassword()+"时间到了";
        }
    }



}
