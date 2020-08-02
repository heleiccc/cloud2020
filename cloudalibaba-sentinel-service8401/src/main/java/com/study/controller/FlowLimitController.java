package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "------A";
    }
    @GetMapping("/testB")
    public String testB(){
        return "------B";
    }
    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------D";
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "handlerHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p1",required = false) String p2){
        return "--testHotKey,^_^";
    }
    public String handlerHotKey(String p2, String p3, BlockException e){
        return "handlerHotKey,o(╥﹏╥)o";
    }

}
