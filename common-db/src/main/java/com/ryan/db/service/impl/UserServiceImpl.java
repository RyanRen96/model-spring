package com.ryan.db.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.db.entity.UserEntity;
import com.ryan.db.mapper.UserMapper;
import com.ryan.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author renlinxuan
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserEntity> getList() {
        Wrapper wp = new QueryWrapper();
        List<UserEntity> userEntities = userMapper.selectList(wp);
        return userEntities;
    }

    @Override
    public IPage<UserEntity> selectUserPage(Page<UserEntity> page) {
        return userMapper.selectUserPage(page);
    }

    @Override
    public int deleteById(UserEntity userEntity) {
        return userMapper.deleteById(userEntity);
    }

}
