package com.ryan.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Ryan
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
@ApiModel(value = "UsersEntity对象", description = "")
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "用户登陆名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "性别：1-男，0-女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private String birthday;

    @ApiModelProperty(value = "身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty(value = "联系方式")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "用户头像")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "用户角色:由字典表role_dic管理")
    @TableField("role")
    private Integer role;

    @ApiModelProperty(value = "职级：由字典表title_dic管理")
    @TableField("title")
    private Integer title;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    private Date modifyTime;

    @ApiModelProperty(value = "用户状态:0-正常，1-已注销")
    @TableField("is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    @ApiModelProperty(value = "验证码")
    private Integer verificationCode;

    /**
     * 自定义token，用于取代session，作为每次会话的标记
     */
    @ApiModelProperty("自定义toke")
    @TableField(exist = false)
    private String customSessionToken;

}
