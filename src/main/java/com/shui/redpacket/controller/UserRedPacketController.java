package com.shui.redpacket.controller;

import com.shui.redpacket.service.RedisUserRedPacketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/20
 */
@Controller
public class UserRedPacketController {
    @Autowired
    private RedisUserRedPacketServiceImpl redisUserRedPacketService;

    @GetMapping("/concurrent")
    public String concurrent() {
        return "concurrent";
    }

    @GetMapping("/grapRedPacket")
    @ResponseBody
    public Map<String, Object> grapRedPacket(Long redPacketId,Long userId) {
        Long result = redisUserRedPacketService.grapRedPacketByRedis(redPacketId,userId);
        Map<String, Object> map = new HashMap<>();
        boolean flag = result > 0;
        map.put("result",flag);
        map.put("message",flag ? "抢红包成功":"抢红包失败");
        return map;
    }
}
