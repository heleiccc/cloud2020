package com.study.controller;

import com.study.vo.Order;
import com.study.service.OrderService;
import com.study.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/create")
    public CommonResult create(Order order){
        return orderService.create(order);
    }
}
