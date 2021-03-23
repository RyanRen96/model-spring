package com.ryan.db.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ryan.common.Util.MyPasswordEncoder;
import com.ryan.common.Util.SnowflakeIdWorker;
import com.ryan.common.Util.StringUtil;
import com.ryan.db.entity.UsersEntity;
import com.ryan.db.mapper.UsersMapper;
import com.ryan.db.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ryan Ren
 * @since 2021-01-28
 */
@Service("UsersService")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    MyPasswordEncoder myPasswordEncoder;

    @Override
    public int insert(UsersEntity usersEntity) {
        usersEntity.setPassword(myPasswordEncoder.encode(usersEntity.getPassword()));
        usersEntity.setId(SnowflakeIdWorker.getId().toString());
        if (usersEntity.getRole() == null) {
            usersEntity.setRole(1);
        }
        if (usersEntity.getTitle() == null) {
            usersEntity.setTitle(1);
        }
        usersEntity.setIsDelete(0);
        usersEntity.setCreateTime(new Date());
        usersEntity.setModifyTime(new Date());
        return usersMapper.insert(usersEntity);
    }

    @Override
    public boolean checkPassword(String password, String inputPassword) {
        return myPasswordEncoder.matches(inputPassword, password);
    }

    @Override
    public UsersEntity getInfoByUsername(UsersEntity usersEntity) {
        return usersMapper.getInfoByUsername(usersEntity.getUsername());
    }

    @Override
    public List<UsersEntity> getList(UsersEntity usersEntity) {
        return baseMapper.selectList(
                Wrappers.<UsersEntity>lambdaQuery()
                        .like(!StringUtil.isEmpty(usersEntity.getNickname()), UsersEntity::getNickname, usersEntity.getNickname())
                        .like(!StringUtil.isEmpty(usersEntity.getGender()), UsersEntity::getGender, usersEntity.getGender())
                        .like(!StringUtil.isEmpty(usersEntity.getRole()), UsersEntity::getRole, usersEntity.getRole())
                        .like(!StringUtil.isEmpty(usersEntity.getTitle()), UsersEntity::getTitle, usersEntity.getTitle())
                        .like(!StringUtil.isEmpty(usersEntity.getIsDelete()), UsersEntity::getIsDelete, usersEntity.getIsDelete())
        );
    }


}
