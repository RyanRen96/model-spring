package com.ryan.db.mapper;

import com.ryan.db.entity.UserEntity;
import com.ryan.db.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Ryan Ren
 * @since 2021-01-28
 */
@Mapper
public interface UsersMapper extends BaseMapper<UsersEntity> {

    UsersEntity getInfoByUsername(@Param("username") String username);
}
