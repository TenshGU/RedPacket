package com.shui.redpacket.dao;

import com.shui.redpacket.pojo.UserRedPacket;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRedPacketMapper {

    /**
     * 插入抢红包信息
     * @param userRedPacket 抢红包信息
     * @return 影响记录数
     */
    public int grapRedPacket(UserRedPacket userRedPacket);

    public int insertGrapRecordBatch(List<UserRedPacket> recordList);
}
