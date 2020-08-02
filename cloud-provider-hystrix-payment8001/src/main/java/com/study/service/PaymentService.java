package com.study.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池： "+Thread.currentThread().getName() +"paymentInfo_OK，id：" + id;
    }

    /**
     * 模拟延时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeout = 3;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName() +"paymentInfo_OK，id：" + id+",耗时："+timeout;
    }
    //paymentInfo_TimeOut的FallBack方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是8001，线程池： "+Thread.currentThread().getName() +"服务器繁忙或运行错误，请稍后重试，id：" + id+"\t o(╥﹏╥)o";
    }
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//开启断路器功能
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求的次数的阈值，熔断的资格
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//快照时间窗，此时间范围内统计数据
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//请求出错的百分比
    })
    //服务熔断
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*****id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功，流水号：\t" + serialNumber;
    }
    //熔断降级方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后重试，o(╥﹏╥)o  id："+id;
    }
}
