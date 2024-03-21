package com.cb.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

@Bean
public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    //自定义RedisTemplate
    log.info("开始创建redis模板对象...");
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    redisTemplate.setConnectionFactory(redisConnectionFactory);
    // 设置key的序列化策略

    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setHashKeySerializer(RedisSerializer.string());
    // 设置value的序列化策略

    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    return redisTemplate;
}
}
