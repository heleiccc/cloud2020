package com.study.springcloud.mapper;

import com.study.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    public int create(Payment payment);

    public Payment selById(Long id);
}
