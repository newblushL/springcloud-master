package com.springcloud.sleuth.config;

import brave.http.HttpAdapter;
import brave.http.HttpSampler;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.instrument.web.ServerSampler;
import org.springframework.cloud.sleuth.instrument.web.SkipPatternProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

/**
 * @description:
 * @author: ll
 * @create: 2019-08-21 21:19
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * @Author: LL
     * @Description: 过滤无意义的调用跟踪
     * @Date: 2019-08-22
     * @Param provider:
     * @return: brave.http.HttpSampler
     **/
    @Bean(name = ServerSampler.NAME)
    HttpSampler myHttpSampler(SkipPatternProvider provider){
        Pattern pattern = provider.skipPattern();
        return new HttpSampler() {
            @Override
            public <Req> Boolean trySample(HttpAdapter<Req, ?> httpAdapter, Req req) {
                String url = httpAdapter.path(req);
                boolean shouldSkip = pattern.matcher(url).matches();
                if(shouldSkip){
                    return false;
                }
                return null;
            }
        };
    }
}