package com.springcloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-17 12:35
 */
@RestController
public class GatewayController {

    @GetMapping("/user/hello")
    public String hello(HttpServletRequest request){
        //System.err.println(request.getHeader("X-Request-Foo"));
        return "success";
    }

    @GetMapping("user/show")
    public String show(){
        return "show";
    }

}