package com.study.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentFeignService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "-------PaymentFallBackService  paymentInfo_ok o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-------PaymentFallBackService  paymentInfo_TimeOut o(╥﹏╥)o";
    }
}
