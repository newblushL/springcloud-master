package com.springcloud.zuul;

import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @description: 回退机制
 * @author: ll
 * @create: 2019-08-15 14:56
 */
@Component
public class ServiceConsumerFallbackProvider implements FallbackProvider {

    private Logger log = LoggerFactory.getLogger(ServiceConsumerFallbackProvider.class);

    @Override
    public String getRoute() {
        //*表示对所有服务进行回退操作，如果只想对某个服务进行回退，返回需要回退的服务名称就行，必须要注册Eurake
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                if (cause != null) {
                    log.error("", cause.getCause());
                }
                RequestContext ctx = RequestContext.getCurrentContext();
                ResponseData data = ResponseData.fail(500, "服务器内部错误");
                return new ByteArrayInputStream(JsonUtils.objectToJson(data).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json",
                        Charset.forName("utf-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }

}