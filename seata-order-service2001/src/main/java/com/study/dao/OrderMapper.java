package com.study.dao;

import com.study.vo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    public int create(Order order);

    public int update(@Param("userId") Long id, @Param("status") Integer status);
}
