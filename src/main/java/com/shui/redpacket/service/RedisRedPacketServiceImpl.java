package com.shui.redpacket.service;

import com.shui.redpacket.dao.UserRedPacketMapper;
import com.shui.redpacket.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/23
 */
@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {

    private static final int ROUND_SIZE = 1000;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserRedPacketMapper userRedPacketMapper;

    @Override
    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
        System.out.println("开始保存数据");
        long start = System.currentTimeMillis();
        long count = 0;
        //获取列表操作对象
        BoundListOperations ops = stringRedisTemplate.boundListOps("userRecord_" + redPacketId);
        Long size = ops.size();
        Long round = size % ROUND_SIZE == 0? size/ROUND_SIZE : size/ROUND_SIZE +1;
        List<UserRedPacket> userRecordList = new ArrayList<>(ROUND_SIZE);
        for (int i = 0; i < round; i++) {
            List<String> subList = null;
            if (i == 0) {
                subList = ops.range(i * ROUND_SIZE, (i + 1) * ROUND_SIZE);
            } else {
                subList = ops.range(i * ROUND_SIZE + 1, (i + 1) * ROUND_SIZE);
            }
            for (String str : subList) {
                String[] split = str.split("-");
                String userIdStr = split[0];
                String timeStr = split[1];
                long userId = Long.parseLong(userIdStr);
                long time = Long.parseLong(timeStr);

                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacket.setNote("抢红包" + redPacketId);
                userRecordList.add(userRedPacket);
            }
            count += userRedPacketMapper.insertGrapRecordBatch(userRecordList);
            userRecordList.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println("插入了" + count + "条记录" + "，所用时间为" + (end-start) + "毫秒");
    }
}
