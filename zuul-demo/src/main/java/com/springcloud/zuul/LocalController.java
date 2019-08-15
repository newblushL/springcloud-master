package com.springcloud.zuul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-14 00:05
 */
@RestController
public class LocalController {
    @GetMapping("/local/{id}")
    public String local(@PathVariable String id) {
        return id;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}