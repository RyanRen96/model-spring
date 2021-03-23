package com.ryan.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.db.entity.UserEntity;

import java.util.List;

/**
 * @author renlinxuan
 */


public interface UserService extends IService<UserEntity> {

    List<UserEntity> getList();

    IPage<UserEntity> selectUserPage(Page<UserEntity> page);

    int deleteById(UserEntity userEntity);

}
