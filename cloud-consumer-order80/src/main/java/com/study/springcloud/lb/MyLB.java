package com.study.springcloud.lb;

import com.sun.xml.internal.ws.api.pipe.NextAction;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger nums = new AtomicInteger(0);//第几次访问
    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstances) {
        if(serviceInstances == null || serviceInstances.size() < 1){
            return null;
        }
        return serviceInstances.get(incrementNums() % serviceInstances.size());
    }
    public final Integer incrementNums(){
        int current;
        int next;
        do {
            current = nums.get();
            next = current > Integer.MAX_VALUE ? 0 :current + 1;
        }while (!nums.compareAndSet(current,next));
        System.out.println("****第"+next+"次调用");
        return next;
    }

}
