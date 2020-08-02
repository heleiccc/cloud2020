package com.study.service;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class HandlerPaymentService implements PaymentService {
    @Override
    public CommonResult getPayment(Integer id) {
        return new CommonResult(4444,"openfeign服务降级--HandlerPaymentService,o(╥﹏╥)o，异常信息：",new Payment(id.longValue(),null));
    }
}
