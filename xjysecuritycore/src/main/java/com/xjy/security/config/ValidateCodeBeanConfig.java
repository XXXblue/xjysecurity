package com.xjy.security.config;

import com.xjy.security.properties.SecurityProperties;
import com.xjy.security.validate.code.ImageCodeGenerator;
import com.xjy.security.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2012:12
 * @Description:
 * @Modified By:
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;
//这种bean的返回类型名字的小写开头就是你bean名字，所以要注意
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
//    暴露接口，返回具体实现类
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
}
