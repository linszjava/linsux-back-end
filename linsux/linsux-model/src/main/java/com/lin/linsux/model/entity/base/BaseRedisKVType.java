package com.lin.linsux.model.entity.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/10 17:34
 */
public class BaseRedisKVType<K,T> {

    @Autowired
    private RedisTemplate<K,T> redisTemplate;



    /**
     * 设置key-value
     * @param key
     * @param value
     */
    public void set(K key,T value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置key-value
     * @param key
     * @param value
     * @param time
     */
    public void set(K key,T value,long time) {
        redisTemplate.opsForValue().set(key, value, time);
    }

    /**
     * 设置key-value 带过期时间和时间单位
     */
    public void set(K key, T value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }


    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除key对应的value
     * @param key
     */
    public void delete(K key) {
        redisTemplate.delete(key);
    }

    /**
     * 设置key的过期时间
     */
    public void expire(K key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 设置过期时间 默认单位为秒
     */
    public void expire(K key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
}
