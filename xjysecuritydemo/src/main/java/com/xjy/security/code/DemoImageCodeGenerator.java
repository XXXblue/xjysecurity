package com.xjy.security.code;

import com.xjy.security.validate.code.ImageCode;
import com.xjy.security.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2015:39
 * @Description:    打开component就能让覆盖测试生效
 * @Modified By:
 */
//@Component("imageCodeGenerator")
@Slf4j
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generator(ServletWebRequest servletWebRequest) {
        log.info("测试覆盖生效");
        return null;
    }
}
