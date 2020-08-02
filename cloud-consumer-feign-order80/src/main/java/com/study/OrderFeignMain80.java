package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //开启Feign功能
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication. run(OrderFeignMain80.class,args);
    }
}
