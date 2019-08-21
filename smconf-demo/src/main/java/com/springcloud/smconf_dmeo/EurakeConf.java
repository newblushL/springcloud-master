package com.springcloud.smconf_dmeo;

import org.cxytiandi.conf.client.annotation.ConfField;
import org.cxytiandi.conf.client.annotation.CxytianDiConf;
import org.cxytiandi.conf.client.core.SmconfUpdateCallBack;
import org.cxytiandi.conf.client.core.rest.Conf;

/**
 * @description: Eurake配置信息
 * @author: ll
 * @create: 2019-08-21 17:12
 */
@CxytianDiConf(system = "common", env = true, prefix = "eurake")
public class EurakeConf implements SmconfUpdateCallBack {

    @ConfField("Eurake注册中心地址")
    private String defaultZone = "http://ll:123456@127.0.0.1:8761/eurake/";

    public String getDefaultZone() {
        return defaultZone;
    }

    public void setDefaultZone(String defaultZone) {
        this.defaultZone = defaultZone;
    }

    @Override
    public void reload(Conf conf) {
        System.out.println(conf.getKey() + "更新了");
    }
}