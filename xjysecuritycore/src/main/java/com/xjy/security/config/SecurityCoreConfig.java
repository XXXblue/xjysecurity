package com.xjy.security.config;
import com.xjy.security.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/199:56
 * @Description:
 * @Modified By:
 */
//这玩意的作用就是让那些properties生效
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
