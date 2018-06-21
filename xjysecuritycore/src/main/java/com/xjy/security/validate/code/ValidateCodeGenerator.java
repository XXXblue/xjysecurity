package com.xjy.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2012:04
 * @Description:    所有验证码生成的接口
 * @Modified By:
 */
public interface ValidateCodeGenerator {
    ValidateCode generator(ServletWebRequest servletWebRequest);
}
