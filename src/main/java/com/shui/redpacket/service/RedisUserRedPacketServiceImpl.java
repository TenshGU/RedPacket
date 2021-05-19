package com.shui.redpacket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/20
 */
@Service
public class RedisUserRedPacketServiceImpl implements RedisUserRedPacketService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisRedPacketService redisRedPacketService;

    @Autowired
    private DefaultRedisScript<Long> defaultRedisScript;

    @Override
    public Long grapRedPacketByRedis(Long redPacketId, Long userId) {
        List<String> keysList = new ArrayList<>();
        keysList.add(String.valueOf(redPacketId));
        String args = userId + "-" +System.currentTimeMillis();
        Long res = stringRedisTemplate.execute(defaultRedisScript, keysList, args);
        if (res == 2) {
            //最后一个红包
            String unitAmountStr = (String) stringRedisTemplate.opsForHash().get("red_packet_" + redPacketId, "unitAmount");
            double unitAmount = Double.parseDouble(unitAmountStr);
            redisRedPacketService.saveUserRedPacketByRedis(redPacketId,unitAmount);
        }
        return res;
    }
}
