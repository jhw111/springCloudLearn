package com.shencome.client.es.sr;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import feign.Retryer;

@Configuration
public class FeignConfig {
    
    @Primary
    @Bean
    public Retryer feignRetryer(){
        /**
         * Default(为下次发起重试请求 生成间隔时间算法的参数(单位:毫秒), 距下次发起重试请求最大的间隔时间(时间单位:毫秒), 重试次数)
         */
        return new Retryer.Default(1000,TimeUnit.SECONDS.toMillis(1000),50);
    }
    
    //取消重试
    @Bean
    Retryer feignRetry() {
         return Retryer.NEVER_RETRY;
    }
}
