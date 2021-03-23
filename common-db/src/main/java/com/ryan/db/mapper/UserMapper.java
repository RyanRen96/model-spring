package com.ryan.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ryan.db.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author renlinxuan
 */

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    IPage<UserEntity> selectUserPage(Page<UserEntity> page);

}