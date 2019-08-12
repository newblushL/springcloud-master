package com.springcloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.hystrix.service.UserRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-07 21:07
 */
@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRemoteClient userRemoteClient;

    @GetMapping("/callHello")
    @HystrixCommand(fallbackMethod = "defaultCallHello")
    public String callHello() {
        String result = restTemplate.getForObject("http://127.0.0.1:8088/house/hello",
                String.class);
        return result;
    }

    public String defaultCallHello() {
        return "fail";
    }

    @GetMapping("/callHello2")
    public String callHello2() {
        return userRemoteClient.hello();
    }
}