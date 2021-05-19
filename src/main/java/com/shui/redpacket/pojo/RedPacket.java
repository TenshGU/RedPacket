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
@Alias("redPacket")
public class RedPacket implements Serializable {
    private static final long serialVersionUID = -7557647741050665961L;

    private Long id;
    private Long userId;
    private Double amount;
    private Timestamp sendDate;
    private Integer total;
    private Double unitAmount;
    private Integer stock;
    private Integer version;
    private String note;
}
