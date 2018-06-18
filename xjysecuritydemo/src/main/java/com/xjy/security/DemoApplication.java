package com.xjy.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1715:20
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {
    public static void main(String args[]){
        SpringApplication.run(DemoApplication.class,args);
    }
}
