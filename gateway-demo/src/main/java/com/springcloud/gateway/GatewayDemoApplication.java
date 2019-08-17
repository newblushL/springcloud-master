package com.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayDemoApplication.class, args);
    }

    /**
     * @Author: LL
     * @Description: IP限流Key指定
     * @Date: 2019-08-17
     * @return: org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     **/
//    @Bean
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
//    }
}
