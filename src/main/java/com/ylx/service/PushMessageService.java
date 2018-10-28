package com.ylx.service;

import com.ylx.dto.OrderDTO;

/**
 * 推送消息
 * Created by LJH
 * 2018-10-30 22:08
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
