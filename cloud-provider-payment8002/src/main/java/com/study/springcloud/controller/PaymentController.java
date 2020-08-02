package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){//前台参数以json方式传递时需要加@RequestBody
        int result = paymentService.create(payment);
        log.info("插入结果："+ result);
        if (result > 0) {
            return new CommonResult(200,"插入数据库成功"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败"+serverPort,result);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> selById(@PathVariable("id") Long id){
        Payment payment = paymentService.selById(id);
        log.info("查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200,"查询数据库成功"+serverPort,payment);
        }else{
            return new CommonResult(444,"查询数据库失败"+serverPort,payment);
        }
    }
    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
    }
    @GetMapping("/payment/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
