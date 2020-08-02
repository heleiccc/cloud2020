package com.study.service;

import com.study.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = HandlerPaymentService.class)
public interface PaymentService {
    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Integer id);
}
