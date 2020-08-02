package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String uri;

    @GetMapping("/consumer/payment/get/{id}")
    public String getPayment(@PathVariable Integer id){
        return restTemplate.getForObject(uri+"/payment/nacos/"+id,String.class);
    }

}
