package com.springcloud.smconf_dmeo;

import org.cxytiandi.conf.client.init.ConfInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SmconfDmeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmconfDmeoApplication.class, args);
    }

//    @Bean
//    public ConfInit confInit() {
//        return new ConfInit();
//    }
}
