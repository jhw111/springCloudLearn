package com.shencome.client.es.sr;

import org.springframework.stereotype.Component;

@Component
public class HelloRemoteFallback implements HelloRemote{
    @Override
    public String hello(String name){
        return "hi,"+name+",sorry.error!";
    }
}