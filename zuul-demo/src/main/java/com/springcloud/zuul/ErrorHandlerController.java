package com.springcloud.zuul;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 异常是返回统一的格式
 * @author: ll
 * @create: 2019-08-15 13:39
 */

@RestController
public class ErrorHandlerController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseData error(HttpServletRequest request){
        Map<String,Object> errorAttributes = getErrorAttributes(request);
        String message = (String)errorAttributes.get("message");
        String trace = (String) errorAttributes.get("trace");
        if(StringUtils.isNotBlank(trace)){
            message += String.format(" and trace %s" ,trace);
        }
        return ResponseData.fail(400,message);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        return errorAttributes.getErrorAttributes(new ServletWebRequest(request),true);
    }
}