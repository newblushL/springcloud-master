package com.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * @description: 缓存清除
 * @author: ll
 * @create: 2019-08-07 16:35
 */
public class ClearCaChcheHystrixCommand extends HystrixCommand<String> {
    private final String name;
    private static final HystrixCommandKey GETTER_KEY =
            HystrixCommandKey.Factory.asKey("MyKey");

    public ClearCaChcheHystrixCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(
                "MyGroup")).andCommandKey(GETTER_KEY));
        this.name = name;
    }

    public static void flushCache(String name) {
        HystrixRequestCache.getInstance(GETTER_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    @Override
    protected String run() {
        System.err.println("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了";
    }
}