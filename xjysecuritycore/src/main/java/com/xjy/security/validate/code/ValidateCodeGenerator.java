package com.xjy.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2012:04
 * @Description:    验证码图片生成的接口
 * @Modified By:
 */
public interface ValidateCodeGenerator {
    ImageCode generator(ServletWebRequest servletWebRequest);
}
