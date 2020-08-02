package com.study.service;

import com.study.vo.Order;
import com.study.springcloud.entities.CommonResult;

public interface OrderService {
    CommonResult create(Order order);
}
