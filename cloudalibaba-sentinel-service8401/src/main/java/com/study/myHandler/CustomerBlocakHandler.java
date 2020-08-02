package com.study.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springcloud.entities.CommonResult;

/**
 * 全局处理配置规则的方法必须是static
 */
public class CustomerBlocakHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(555,"按客户自定义限流，global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(555,"按客户自定义限流，global handlerException----2");
    }
}
