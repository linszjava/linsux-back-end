package com.lin.linsux.common.util.redis;

import com.lin.linsux.model.entity.base.BaseRedisKVType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>TODO</p>
 * 简化redis操作的工具类
 * @author linsz
 * @version v1.0
 * @date 2023/10/30 18:41
 */
@Component
public class RedisObjectOperator extends BaseRedisKVType<String,Object> {

}
