package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)  //定义接收管道
public class ReceiveMessageController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message){
        System.out.println("消费者2号，收到消息："+message.getPayload()+"\t port:"+serverPort);
    }
}
