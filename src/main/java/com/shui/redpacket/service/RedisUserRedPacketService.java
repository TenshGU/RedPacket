package com.shui.redpacket.service;

public interface RedisUserRedPacketService {
    /**
     * 保存红包信息
     * @param redPacketId 红包编号
     * @param userId 抢红包用户编号
     * @return 影响记录数
     */
    public Long grapRedPacketByRedis(Long redPacketId,Long userId);
}
