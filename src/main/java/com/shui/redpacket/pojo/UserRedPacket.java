package com.shui.redpacket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Alias("userRedPacket")
public class UserRedPacket implements Serializable {
    private static final long serialVersionUID = 9135330557962005997L;

    private Long id;
    private Long redPacketId;
    private Long userId;
    private Double amount;
    private Timestamp grabTime;
    private String note;
}
