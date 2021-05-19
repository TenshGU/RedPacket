package com.shui.redpacket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/23
 */
@EnableCaching
@Configuration
@EnableConfigurationProperties(RedisScriptProperties.class)
public class RedisScriptAutoConfiguration {

    @Autowired
    RedisScriptProperties redisScriptProperties;

    @Bean("shalRedisScript")
    public DefaultRedisScript<Long> shalRedisScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        String path = redisScriptProperties.getPath();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(path)));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

}
