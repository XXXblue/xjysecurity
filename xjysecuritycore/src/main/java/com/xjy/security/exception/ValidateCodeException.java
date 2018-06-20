package com.xjy.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/209:42
 * @Description:
 * @Modified By:
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
