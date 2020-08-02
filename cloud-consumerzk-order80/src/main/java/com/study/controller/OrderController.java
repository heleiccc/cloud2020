package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderController {
    private static String INVOKE_URI = "http://cloud-payment-server";
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/consumer/get")
    public String get(){
        return restTemplate.getForObject(INVOKE_URI+"/payment/zk",String.class);
    }
}
