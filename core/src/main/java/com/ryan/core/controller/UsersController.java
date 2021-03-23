package com.ryan.core.controller;

import com.ryan.common.dao.R;
import com.ryan.db.entity.UsersEntity;
import com.ryan.db.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("用户信息接口")
@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation("新建用户")
    @PostMapping("/insert")
    public R insertUser(@RequestBody UsersEntity usersEntity) {
        int i = usersService.insert(usersEntity);
        return R.ok();
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public R deleteUser(@RequestBody UsersEntity usersEntity) {
        return R.ok();
    }

    @ApiOperation("更新用户")
    @PostMapping("/update")
    public R updateUser(@RequestBody UsersEntity usersEntity) {
        return R.ok();
    }

    @ApiOperation("用户")
    @PostMapping("/UserById")
    public R getUserById(@RequestBody UsersEntity usersEntity) {
        return R.ok().data(usersService.getById(usersEntity));
    }

    @ApiOperation("分页条件查询用户")
    @PostMapping("/page")
    public R pageUser(@RequestBody UsersEntity usersEntity) {

        return R.ok();
    }

    @ApiOperation("列表条件查询用户")
    @PostMapping("/list")
    public R listUser(@RequestBody UsersEntity usersEntity) {
        List<UsersEntity> usersEntityList = usersService.getList(usersEntity);
        return R.ok();
    }
}
