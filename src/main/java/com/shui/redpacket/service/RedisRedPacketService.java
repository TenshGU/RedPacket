package com.shui.redpacket.service;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/23
 */
public interface RedisRedPacketService {
    public void saveUserRedPacketByRedis(Long redPacketId,Double unitAmount);
}
