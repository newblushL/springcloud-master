package com.springcloud.hystrix.service;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: Feign FallBackFactory回退
 * @author: ll
 * @create: 2019-08-12 21:42
 */
public class UserRemoteClientFallBackFactory implements FallbackFactory<UserRemoteClient> {
    private Logger logger = LoggerFactory.getLogger(UserRemoteClientFallBackFactory.class);
    @Override
    public UserRemoteClient create(Throwable throwable) {
        logger.error("UserRemoteClient回退：",throwable);
        return new UserRemoteClient() {
            @Override
            public String hello() {
                return "fail";
            }
        };
    }
}