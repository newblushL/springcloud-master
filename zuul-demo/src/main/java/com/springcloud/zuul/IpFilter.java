package com.springcloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @description: pre过滤器，实现IP黑名单过滤操作
 * @author: ll
 * @create: 2019-08-14 00:14
 */
public class IpFilter extends ZuulFilter {

    //IP黑名单列表
    private List<String> blackIpList = Arrays.asList("192.168.0.104");

    public IpFilter(){
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtils.getIpAddr(ctx.getRequest());
        // 在黑名单中禁用
        if(StringUtils.isNotBlank(ip) && blackIpList.contains(ip)){
            // 告诉Zuul不需要将当前请求转发到后端服务
            ctx.setSendZuulResponse(false);
            ResponseData data = ResponseData.fail("非法请求",400);
            // 通过setResponseBody返回数据给客户端
            ctx.setResponseBody(JsonUtils.objectToJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}