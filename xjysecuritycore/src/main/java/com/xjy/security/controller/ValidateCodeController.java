package com.xjy.security.controller;

import com.xjy.security.properties.SecurityProperties;
import com.xjy.security.validate.code.ImageCode;
import com.xjy.security.validate.code.ImageCodeGenerator;
import com.xjy.security.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1917:08
 * @Description:
 * @Modified By:
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        
        ImageCode imageCode = validateCodeGenerator.generator(new ServletWebRequest(request));
//        存session，这里存spring管理的session,切记一点就是你用spring管理的session就必须用spring封装的requset去获取
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }



}
