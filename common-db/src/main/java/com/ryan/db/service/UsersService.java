package com.ryan.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.db.entity.UsersEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ryan Ren
 * @since 2021-01-28
 */
public interface UsersService extends IService<UsersEntity> {

    UsersEntity getInfoByUsername(UsersEntity usersEntity);

    List<UsersEntity> getList(UsersEntity usersEntity);

    int insert(UsersEntity usersEntity);

    boolean checkPassword(String password, String inputPassword);
}
