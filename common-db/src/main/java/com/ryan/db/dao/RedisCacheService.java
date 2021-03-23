package com.ryan.db.dao;

import com.ryan.common.Util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheService {
    @Autowired
    RedisTemplate<String, Object> stringRedisTemplate;

    /* ----------- common --------- */
    public Collection<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void delete(Collection<String> key) {
        stringRedisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return	stringRedisTemplate.hasKey(key);
    }




    /**
     * get
     * @param key
     * @return
     */
    public Object get(String key) {

        Object value = stringRedisTemplate.opsForValue().get(key);
        if(value!=null) {
            byte[] val=(byte[]) value;
            value= SerializeUtil.unserialize(val);
        }
        return value;
    }




    /**
     *
     * @param key
     * @param obj
     */
    public void set(String key, Object obj) {
        if (obj == null) {
            return;
        }
        stringRedisTemplate.opsForValue().set(key, SerializeUtil.serialize(obj));
    }


    /**
     * getString
     * @param key
     * @return
     */
    public String getString(String key){
        Object value = stringRedisTemplate.opsForValue().get( key);
        String valueRetun = "";

        if(value!=null) {
            byte[] val=(byte[]) value;
            valueRetun=SerializeUtil.unserialize(val).toString();
        }
        return valueRetun;
    }

    /**
     * getString
     * @param key
     * @return
     */
    public String getStringNoPrefix(String key){
        Object value = stringRedisTemplate.opsForValue().get(key);
        String valueRetun = "";

        if(value!=null) {
            byte[] val=(byte[]) value;
            valueRetun=SerializeUtil.unserialize(val).toString();
        }
        return valueRetun;
    }

    /**
     * set key time
     * @param key 键
     * @param obj 值
     * @param timeout 超时时间
     * @param unit  超时时间单位 参照TimeUnit
     */
    public void setKeyByTime(String key, Object obj, Long timeout, TimeUnit unit) {
        if (obj == null) {
            return;
        }

        if (timeout != null) {
            stringRedisTemplate.opsForValue().set( key, SerializeUtil.serialize(obj), timeout, unit);
        } else {
            stringRedisTemplate.opsForValue().set(key, SerializeUtil.serialize(obj));
        }
    }
}
