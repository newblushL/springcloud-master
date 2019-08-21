package com.springcloud.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-21 21:11
 */
@RestController
public class ConController {

    private Logger logger = LoggerFactory.getLogger(ConController.class);
    @GetMapping("/con/hello")
    public String hello(){
        logger.info("我是/con/hello");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}