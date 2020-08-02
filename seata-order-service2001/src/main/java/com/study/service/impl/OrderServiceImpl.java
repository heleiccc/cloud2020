package com.study.service.impl;

import com.study.dao.OrderMapper;
import com.study.vo.Order;
import com.study.service.OrderService;
import com.study.springcloud.entities.CommonResult;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 业务：下订单-->减库存-->减余额-->更改订单状态
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
//    @GlobalTransactional(name = "fsp_tx_group")
    public CommonResult create(Order order) {
        log.info("开始创建订单");
        orderMapper.create(order);
        log.info("减库存开始");

        return null;
    }
}
