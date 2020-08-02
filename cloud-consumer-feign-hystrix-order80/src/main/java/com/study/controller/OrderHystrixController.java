package com.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import com.study.service.PaymentFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
//@DefaultProperties(defaultFallback = "globalHandler")
public class OrderHystrixController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/hystrix/ok/{id}")
//    @HystrixCommand
    public String paymentInfo_ok(@PathVariable("id") Integer id){
//        int i = 10/0;
        return paymentFeignService.paymentInfo_ok(id);
    }
    @GetMapping("/consumer/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
//        int i = 10 / 0;
        return paymentFeignService.paymentInfo_TimeOut(id);
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是80，线程池： "+Thread.currentThread().getName() +"服务器繁忙或运行错误，请稍后重试，id：" + id+"\t o(╥﹏╥)o";
    }
    //全局的FallBack方法,全局的异常处理不能保证参数统一，因此此方法没有参数
    public String globalHandler(){
        return "全局异常： "+Thread.currentThread().getName() +"服务器繁忙或运行错误，请稍后重试\to(╥﹏╥)o";
    }
}
