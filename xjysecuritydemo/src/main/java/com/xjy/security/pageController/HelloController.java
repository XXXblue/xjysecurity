package com.xjy.security.pageController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1715:26
 * @Description:
 * @Modified By:
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Hello(){
        throw new RuntimeException();
    }
}
