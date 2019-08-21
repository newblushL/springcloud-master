package com.springcloud.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-21 20:48
 */
@RestController
public class AritcleController {
    private Logger logger = LoggerFactory.getLogger(AritcleController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/article/hello")
    public String hello(){
        logger.info("我是article/hello");
        return restTemplate.getForObject("http://sleuth-con/con/hello",String.class);
    }
}