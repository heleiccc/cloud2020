package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.study.myHandler.CustomerBlocakHandler;
import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RateLimitController {

    @GetMapping(value = "/rateLimit/byUrl/")
    @SentinelResource(value = "rateLimit/constomerBlockHandler",
            blockHandler = "handlerExceptionByUrl")
    public CommonResult<Payment> byUrl(){
        return new CommonResult(200,"按客户自定义", new Payment(2020L,"serial;003"));
    }
    public CommonResult<Payment> handlerExceptionByUrl(){
        return new CommonResult(444,"错误，o(╥﹏╥)o", new Payment(2020L,"serial;003"));
    }
    @GetMapping(value = "/rateLimit/constomerBlockHandler/")
    @SentinelResource(value = "rateLimit/constomerBlockHandler",
            blockHandlerClass = CustomerBlocakHandler.class,
            blockHandler = "handlerException")
    public CommonResult<Payment> constomerBlockHandler(){
        return new CommonResult(200,"按客户自定义", new Payment(2020L,"serial;003"));
    }
}
