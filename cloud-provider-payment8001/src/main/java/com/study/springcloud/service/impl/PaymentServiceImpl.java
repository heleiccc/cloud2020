package com.study.springcloud.service.impl;

import com.study.springcloud.entities.Payment;
import com.study.springcloud.mapper.PaymentMapper;
import com.study.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;
    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment selById(Long id) {
        return paymentMapper.selById(id);
    }
}
