package com.springcloud.hystrix.service.impl;

import com.springcloud.hystrix.service.UserRemoteClient;
import org.springframework.stereotype.Component;

/**
 * @description: 实现UserRemoteClient类
 * @author: ll
 * @create: 2019-08-12 20:53
 */
@Component
public class UserRemoteClientFallback implements UserRemoteClient {
    @Override
    public String hello() {
        return "fail";
    }
}