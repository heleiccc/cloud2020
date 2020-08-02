package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
//    private final static String PAYMENT_URL = "http://localhost:8001/";
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVER";

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private RestTemplate restTemplate;//提供了多种远程访问http服务的方法
    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult create(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/lb")
    public String lb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        ServiceInstance instance = loadBalancer.getInstance(instances);
        URI uri = instance.getUri();
//        String uri = instance.getUri().toString();
//        uri = uri + "/payment/lb";
        System.out.println(uri);
        return restTemplate.getForObject(uri + "/payment/lb",String.class);
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipKin(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin",String.class);
    }
}
