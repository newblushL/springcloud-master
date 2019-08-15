package com.springcloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @description: pre过滤器，实现IP黑名单过滤操作
 * @author: ll
 * @create: 2019-08-14 00:14
 */
public class IpFilter extends ZuulFilter {

    private List<String> blackIpList = Arrays.asList("192.168.0.104");

    public IpFilter() {
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
        RequestContext ctx = RequestContext.getCurrentContext();
        // 根据参数决定是否执行过滤器
        Object success = ctx.get("isSuccess");
        return success == null ? true : Boolean.parseBoolean(success.toString());
    }

    @Override
    public Object run() {
        // 模拟异常信息，试试ErrorFilter
        // System.out.println(2/0);
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtils.getIpAddr(ctx.getRequest());
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
            // 告诉Zuul不需要将当前请求转发到后端服务
            ctx.setSendZuulResponse(false);
            // 配置来forward:/local的路由，ctx.setSendZuulResponse(false)是不起作用的，需要设置
            ctx.set("sendForwardFilter.ran", true);
            // 设置一个标识是否成功，为true后续的过滤器才执行，若为false则不执行
            ctx.set("isSuccess", false);
            ResponseData data = ResponseData.fail(400, "非法请求");
            // 通过setResponseBody返回数据给客户端
            ctx.setResponseBody(JsonUtils.objectToJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}