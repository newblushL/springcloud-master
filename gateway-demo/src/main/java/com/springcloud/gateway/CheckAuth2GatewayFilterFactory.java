package com.springcloud.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义过滤器工厂2
 * @author: ll
 * @create: 2019-08-17 13:50
 */
@Component
public class CheckAuth2GatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            System.err.println("\"进入了CheckAuth2GatewayFilterFactory\"+config.getName()"+"\t"
                    +config.getValue());
            ServerHttpRequest request = exchange.getRequest().mutate().build();
            return chain.filter(exchange.mutate().request(request).build());
        });
    }
}