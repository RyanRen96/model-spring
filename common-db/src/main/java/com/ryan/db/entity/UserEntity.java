package com.ryan.db.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ryan
 */

@TableName("user")
@Data
public class UserEntity implements Serializable {

    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("gender")
    private Integer gender;
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 乐观锁配置：当要更新一条记录的时候，希望这条记录没有被别人更新
     * 乐观锁实现方式：
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = newVersion where version = oldVersion
     * 如果version不对，就更新失败
     */
//    @Version
//    private Integer version;

}
