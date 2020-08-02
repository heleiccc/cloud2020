package com.study.controller;

import com.study.service.PaymentFeignService;
import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        return paymentFeignService.selById(id);
    }
    @GetMapping("/consumer/payment/lb")
    public String lb(){
        return paymentFeignService.lb();
    }

    @GetMapping("/consumer/payment/timeout")
    public String timeout(){
        return paymentFeignService.timout();
    }
}

