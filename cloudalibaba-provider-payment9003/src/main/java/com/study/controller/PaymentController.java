package com.study.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    private static Map<Integer,Payment> serial = new HashMap<>();
    static {
        serial.put(1,new Payment(1L,"11111"));
        serial.put(2,new Payment(2L,"22222"));
        serial.put(3,new Payment(3L,"33333"));
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable Integer id){
        return new CommonResult(200,serverPort+"O(∩_∩)O哈哈~",serial.get(id));
    }
}
