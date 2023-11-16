package com.lin.linsux.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/30 20:00
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 1.创建 redisTemplate 模板类
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 3.创建 序列化类
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 4. key 采用 String 的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 5. hash 的 key 采用 String 的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 6. value 采用 jackson 的序列化方式
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // 7. hash 的 value 采用 jackson 的序列化方式
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // 8. 设置支持事务
        redisTemplate.setEnableTransactionSupport(true);
        // 9.返回
        return redisTemplate;
    }
}
