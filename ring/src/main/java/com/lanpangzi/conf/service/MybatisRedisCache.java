package com.lanpangzi.conf.service;

import com.lanpangzi.conf.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache{

    private final String id;
    private RedisTemplate template;
    private final ReadWriteLock readWriteLock= new ReentrantReadWriteLock();

    private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    public MybatisRedisCache (final String id){
        if (null == id){
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id=id;
    }
    private RedisTemplate getRedisTemplate(){
        if(this.template==null){
            this.template= ApplicationContextHolder.getBean("redisTemplate");
        }
        return this.template;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        this.getRedisTemplate().opsForValue().set(key,value,10, TimeUnit.MINUTES);
        logger.info("存入缓存中");
    }

    @Override
    public Object getObject(Object key) {
        logger.info("获取缓存的");
        return this.getRedisTemplate().opsForValue().get(key);
    }

    @Override
    public Object removeObject(Object key) {
        logger.info("移除掉了缓存的");
        return this.getRedisTemplate().delete(key);
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        //回调函数
        redisTemplate.execute((RedisCallback) collection->{
            collection.flushDb();
            return  null;
        });
        logger.info("清空了");
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
