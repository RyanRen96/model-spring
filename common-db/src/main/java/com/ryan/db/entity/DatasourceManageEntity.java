package com.ryan.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ryan
 * @since 2021-01-21
 */
@TableName("datasource_manage")
@Data
@EqualsAndHashCode(callSuper = false)
public class DatasourceManageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private String id;

    /**
     * 数据源名称
     */
    @TableField("datasource_name")
    private String datasourceName;

    /**
     * 数据集主类型：1-关系型数据库，2-文件，3-NoSQL，4-消息队列 ，5-API
     */
    @TableField("main_type")
    private Integer mainType;

    /**
     * 数据源类型
     */
    @TableField("datasource_type")
    private String datasourceType;

    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;

    /**
     * 驱动
     */
    @TableField("driver")
    private String driver;

    /**
     * 连接地址
     */
    @TableField("url")
    private String url;

    /**
     * 连接用户
     */
    @TableField("username")
    private String username;

    /**
     * 连接密码
     */
    @TableField("`password`")
    private String password;

    /**
     * schema
     */
    @TableField("`schema`")
    private String schema;

    /**
     * 操作者
     */
    @TableField("operator")
    private String operator;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;


}
