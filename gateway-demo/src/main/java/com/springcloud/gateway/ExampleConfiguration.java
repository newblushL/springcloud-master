package com.springcloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @description: 全局过滤器使用
 * @author: ll
 * @create: 2019-08-17 13:56
 */
public class ExampleConfiguration {
    private Logger log = LoggerFactory.getLogger(ExampleConfiguration.class);
    @Bean
    @Order(-1)
    public GlobalFilter a(){
        return (exchange, chain) -> {
            log.info("first pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("third post filter");
            }));
        };
    }

    @Bean
    @Order(0)
    public GlobalFilter b(){
        return (exchange, chain) -> {
            log.info("second pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("second post filter");
            }));
        };
    }

    @Bean
    @Order(1)
    public GlobalFilter c(){
        return (exchange, chain) -> {
            log.info("third pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("first post filter");
            }));
        };
    }
}