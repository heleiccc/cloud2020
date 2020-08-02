package com.study.service.controller;

import com.study.service.IMessageProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider provider;
    @GetMapping("send")
    public String send(){
        return provider.send();
    }
}
