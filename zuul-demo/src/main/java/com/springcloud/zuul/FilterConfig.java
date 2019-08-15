package com.springcloud.zuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 过滤器配置
 * @author: ll
 * @create: 2019-08-14 00:59
 */
@Configuration
public class FilterConfig {

    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }
}