package com.springcloud.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义过滤器工厂
 * @author: ll
 * @create: 2019-08-17 13:41
 */
@Component
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {
    public CheckAuthGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            System.err.println("进入了CheckAuthGatewayFilterFactory"+config.getName());
//            ServerHttpRequest request = exchange.getRequest().mutate().build();
//            return chain.filter(exchange.mutate().request(request).build());
//        });
        return new IpCheckFilter();
    }


    public static class Config{
        private String name;

        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}