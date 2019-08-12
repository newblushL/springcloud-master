package com.springcloud.hystrix.service;

import com.springcloud.hystrix.service.impl.UserRemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: Feign客户端接口
 * @author: ll
 * @create: 2019-08-12 20:50
 */
@FeignClient( value = "eurake-client-user-service",fallback = UserRemoteClientFallback.class
        ,fallbackFactory = UserRemoteClientFallBackFactory.class)
public interface UserRemoteClient {
    @GetMapping("/user/hello")
    String hello();
}