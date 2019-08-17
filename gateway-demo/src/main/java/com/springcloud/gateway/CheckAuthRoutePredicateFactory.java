package com.springcloud.gateway;


import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @description: 自定义路由断言工厂
 * @author: ll
 * @create: 2019-08-15 21:30
 */
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {


    public CheckAuthRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange ->{
            System.err.println("进入CheckAuthRoutePredicateFactory\t"+config.getName());
            if(config.getName().equals("ll")){
                return true;
            }
            return false;
        };
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