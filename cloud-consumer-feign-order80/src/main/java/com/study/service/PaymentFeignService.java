package com.study.service;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component  //注入到容器中由spring管理，实现远程调用
@FeignClient(value = "CLOUD-PAYMENT-SERVER")    //告诉它应该去找那个服务
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")    //调用服务的哪个接口
    public CommonResult<Payment> selById(@PathVariable("id") Long id);
    @GetMapping("/payment/lb")
    public String lb();

    @GetMapping("/payment/timeout")
    public String timout();
}
