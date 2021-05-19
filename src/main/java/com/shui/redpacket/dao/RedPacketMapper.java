package com.shui.redpacket.dao;

import com.shui.redpacket.pojo.RedPacket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/20
 */
@Mapper
public interface RedPacketMapper {
    /**
     * 获取红包信息
     * @param id 红包id
     * @return 红包具体信息
     */
    public RedPacket getRedPacket(Long id);

    /**
     * 扣减红包数
     * @param id 红包id
     * @return 更新记录数
     */
    public int decreaseRedPacketWithVersion(Long id,Integer version);

    public int decreaseRedPacket(Long id);
}
