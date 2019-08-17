package com.springcloud.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @description: 自定义IP拦截过滤器
 * @author: ll
 * @create: 2019-08-17 15:29
 */
@Component
public class IpCheckFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        // 此处写的非常绝对，只做演示，实际中需要采取配置的方式
        if(getIp(headers).equals("127.0.0.1")){
            ServerHttpResponse response = exchange.getResponse();
            ResponseData data = new ResponseData();
            data.setCode(401);
            data.setMessage("非法请求");
            byte[] datas = JsonUtils.objectToJson(data).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type",
                    "application/json;charset=UTF-8");
            return chain.filter(exchange);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    //这里从请求头获取用户的实际IP，根据Nginx转发的请求头获取

    private String getIp(HttpHeaders headers){
        return "127.0.0.1";
    }
}