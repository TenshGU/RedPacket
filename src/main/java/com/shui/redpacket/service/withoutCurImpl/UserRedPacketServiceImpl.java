package com.shui.redpacket.service.withoutCurImpl;

import com.shui.redpacket.dao.RedPacketMapper;
import com.shui.redpacket.dao.UserRedPacketMapper;
import com.shui.redpacket.pojo.RedPacket;
import com.shui.redpacket.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/20
 */
@Service("userRedPacketServiceOri")
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Autowired
    private RedPacketMapper redPacketMapper;

    @Autowired
    private UserRedPacketMapper userRedPacketMapper;

    private static final int FAILED = -1;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED)
    public int grapRedPacket(Long redPacketId, Long userId) {
        RedPacket redPacket = redPacketMapper.getRedPacket(redPacketId);
        if (redPacket.getStock() > 0) {
            redPacketMapper.decreaseRedPacket(redPacketId);
            //生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote(userId + "抢了" + redPacket.getUnitAmount() + "元");
            //插入抢红包信息
            int result = userRedPacketMapper.grapRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }
}
