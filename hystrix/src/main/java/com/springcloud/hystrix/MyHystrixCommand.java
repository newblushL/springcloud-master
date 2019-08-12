package com.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @description:
 * @author: ll
 * @create: 2019-08-07 16:07
 */
public class MyHystrixCommand extends HystrixCommand<String> {
    private final String name;

    public MyHystrixCommand(String name) {
        //线程隔离策略配置，系统默认采用线程隔离策略，可以通过andThreadPoolPropertiesDefaults配置线程池的参数
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(
                "myGroup")).andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionIsolationStrategy(
                                HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10).withMaxQueueSize(100).withMaximumSize(100)));
        this.name = name;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    @Override
    protected String run() {
        /*try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.err.println("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String result = new ClearCaChcheHystrixCommand("ll").execute();
        System.out.println(result);
        ClearCaChcheHystrixCommand.flushCache("ll");
        Future<String> future = new ClearCaChcheHystrixCommand("ll").queue();
        System.out.println(future.get());
    }
}