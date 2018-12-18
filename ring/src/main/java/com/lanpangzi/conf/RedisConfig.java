package com.lanpangzi.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
    @Bean
    public ValueOperations<String,Object> valueOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForValue();
    }
    @Bean
    public ListOperations<String,Object> listOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForList();
    }
    @Bean
    public SetOperations<String,Object> setOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForSet();
    }
    @Bean
    public HashOperations<String,Object,Object> hashOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForHash();
    }
    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForZSet();
    }
}
