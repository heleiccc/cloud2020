package com.study.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    private final static String PAYMENT_URL = "http://cloud-providerconsul-payment8006/";
    @Autowired
    private RestTemplate restTemplate;//提供了多种远程访问http服务的方法
    @GetMapping("/consumer/payment/get")
    public String get(){
        return restTemplate.getForObject(PAYMENT_URL+"payment/consul",String.class);
    }

}
