package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.study.service.PaymentService;
import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class OrderController {
    @Value("${server-url.nacos-user-service}")
    public String url;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")
//    @SentinelResource(value = "fallback",blockHandler = "handlerFallback")
    @SentinelResource(value = "fallback",blockHandler = "handlerFallback",
            fallback = "handlerFallback",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult getPayment(@PathVariable Long id){
        CommonResult result = restTemplate.getForObject(url + "/payment/get/" + id, CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException,该ID没有对应的记录，空指针异常");
        }
        return result;
    }
    //fallback
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id,null);
        return new CommonResult(444,e.getMessage(),payment);
    }
    //blockHandler
    public CommonResult blockHandler(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id,null);
        return new CommonResult(444,"服务限流--blockHandler"+e.getMessage(),payment);
    }
    //========openFeign
    @GetMapping("/consumer/openFeign/get/{id}")
    public CommonResult getPayment(@PathVariable Integer id){
        return paymentService.getPayment(id);
    }
}
