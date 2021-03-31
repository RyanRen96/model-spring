package com.ryan.oauth.customImpl;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

public class MyRedisTokenStore extends RedisTokenStore {

    public MyRedisTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
