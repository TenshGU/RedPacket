package com.shui.redpacket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/23
 */
@ConfigurationProperties(prefix = "spring.redis.script")
public class RedisScriptProperties {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
