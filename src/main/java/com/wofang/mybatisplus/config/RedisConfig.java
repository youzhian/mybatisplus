package com.wofang.mybatisplus.config;

import io.lettuce.core.support.RedisClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis相关配置
 * @author 游志安
 */
@Configuration
public class RedisConfig {

    /**
     * 设置redisTemplate key序列化
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        //将key的序列化设置成StringRedisSerializer
        redisTemplate.setStringSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
