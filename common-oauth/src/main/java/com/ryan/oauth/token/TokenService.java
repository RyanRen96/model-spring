//package com.ryan.oauth.token;
//
//
//import com.ryan.db.entity.UserEntity;
//import com.ryan.oauth.utils.TokenGenerator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
///**
// * Token 相关实现
// */
//@Service
//public class TokenService {
//
//    //2小时后过期
//    private final static int EXPIRE = 60 * 2;
//    private final String TOKEN_PREFIX = "TOKEN-ID:";
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private LoginFilterService loginFilterService;
//
//
//    /**
//     * 创建 Token
//     *
//     * @param user
//     * @return
//     */
//    @Deprecated
//    public String createToken(UserEntity user, List<RoleEntity> roleEntities, List<PermissionEntity> permissions) {
//        //生成一个token
//        String token = TokenGenerator.generateValue();
//        return refreshToken(token, user, roleEntities, permissions);
//    }
//
//
//    /**
//     * 更新Token信息（登录时调用），并保存相关信息
//     *
//     * @param token
//     * @param user
//     * @param organization
//     * @param permissions
//     * @return
//     */
//    public String refreshToken(String token, UserEntity user, OrganizationEntity organization, List<RoleEntity> roleEntities, List<PermissionEntity> permissions) {
//        //保存token
//        UserContextInfo userContextInfo = new UserContextInfo();
//        userContextInfo.setUserId(user.getId());
//        userContextInfo.setEmail(user.getEmail());
//        userContextInfo.setName(user.getNickname());
//        userContextInfo.setPhone(user.getPhone());
//        userContextInfo.setUsername(user.getUsername());
//        userContextInfo.setToken(token);
//        userContextInfo.setOrganization(organization);
//        userContextInfo.setRoles(roleEntities);
//        userContextInfo.setPermissions(permissions);
//        userContextInfo.setPreferences(user.getPreferences());
//        userContextInfo.setDisplay(user.getDisplay());
//        setRedisToken(token, userContextInfo);
//
//        return token;
//    }
//
//
//    /**
//     * 更新token信息（每次成功登录后调用），刷新成功登录的loginToken到redis
//     *
//     * @param token
//     * @param loginToken
//     * @return
//     */
//    public String refreshToken(String token, String loginToken) {
//        UserContextInfo userContextInfo = getTokenEntity(token);
//        userContextInfo.setLoginToken(loginToken);
//        setRedisToken(token, userContextInfo);
//        return token;
//    }
//
//
//    /**
//     * 更新token信息（每次请求时调用），只刷新过期时间，不修改相关信息
//     *
//     * @param token
//     * @param userContextInfo
//     * @return
//     */
//    public String refreshToken(String token, UserContextInfo userContextInfo) {
//        setRedisToken(token, userContextInfo);
//        return token;
//    }
//
//
//    /**
//     * 更新token信息（更新用户信息时调用），只修改用户信息，其他相关信息不修改
//     *
//     * @param token
//     * @param user
//     * @return
//     */
//    public String refreshToken(String token, UserEntity user) {
//        UserContextInfo userContextInfo = getTokenEntity(token);
//        userContextInfo.setUserId(user.getId());
//        userContextInfo.setEmail(user.getEmail());
//        userContextInfo.setName(user.getNickname());
//        userContextInfo.setPhone(user.getPhone());
//        userContextInfo.setUsername(user.getUsername());
//        setRedisToken(token, userContextInfo);
//        return token;
//    }
//
//
//    /**
//     * 更新token信息（更新组织机构和权限时调用），只修改组织机构或者权限信息，其他相关信息不修改
//     *
//     * @param token
//     * @param organizationEntity
//     * @param permissionEntities
//     * @return
//     */
//    public String refreshToken(String token, OrganizationEntity organizationEntity, List<PermissionEntity> permissionEntities) {
//        UserContextInfo userContextInfo = getTokenEntity(token);
//        if (organizationEntity != null) {
//            userContextInfo.setOrganization(organizationEntity);
//        }
//        if (permissionEntities != null) {
//            userContextInfo.setPermissions(permissionEntities);
//        }
//        setRedisToken(token, userContextInfo);
//        return token;
//    }
//
//
//    /**
//     * 判断token是否存在
//     *
//     * @param token
//     * @return
//     */
//    public boolean isTokenExist(String token) {
//        if (redisTemplate.hasKey(TOKEN_PREFIX + token)) {
//            return true;
//        }
//        return false;
//    }
//
//
//    /**
//     * Token 根据token获取 UserContextInfo
//     *
//     * @param token
//     * @return
//     */
//    public UserContextInfo getTokenEntity(String token) {
//        UserContextInfo userContextInfo = getRedisToken(token);
//        return userContextInfo;
//    }
//
//
//    /**
//     * Token 根据username获取 UserContextInfo
//     *
//     * @param username
//     * @return
//     */
//    public UserContextInfo getTokenEntityByUsername(String username) {
//        String token = getTokenByUsername(username);
//        if (!StringUtils.isNullOrEmptyStr(token)) {
//            return getTokenEntity(token);
//        }
//        return null;
//    }
//
//
//    /**
//     * 删除TOKEN
//     *
//     * @param token
//     */
//    public void cleanToken(String token) {
//        redisTemplate.delete(TOKEN_PREFIX + token);
//    }
//
//
//    /**
//     * redis用token获取value
//     *
//     * @param token
//     * @return
//     */
//    private UserContextInfo getRedisToken(String token) {
//        return (UserContextInfo) redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
//    }
//
//
//    /**
//     * 根据username获取token
//     *
//     * @param username
//     * @return
//     */
//    public String getTokenByUsername(String username) {
//        if (loginFilterService.isLoginContextInfoExist(username)) {
//            return loginFilterService.getLoginContextInfo(username).getToken();
//        } else {
//            return "";
//        }
//    }
//
//
//    /**
//     * redis设置token，并保存一条登录记录，以实现单设备登录
//     *
//     * @param token
//     * @param userContextInfo
//     */
//    private void setRedisToken(String token, UserContextInfo userContextInfo) {
//        // 以token为key，userContextInfo为value，保存用户信息
//        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, userContextInfo);
//        redisTemplate.expire(TOKEN_PREFIX + token, EXPIRE, TimeUnit.MINUTES);
//    }
//
//}
