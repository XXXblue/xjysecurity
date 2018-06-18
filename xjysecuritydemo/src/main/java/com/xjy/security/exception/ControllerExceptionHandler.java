package com.xjy.security.exception;

import com.xjy.security.controller.UserController;
import com.xjy.security.enums.ResultEnum;
import com.xjy.security.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1810:15
 * @Description:
 * @Modified By:
 */
@ControllerAdvice("com.xjy.security.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object hander(Exception e){
        //这种是基于微服务的异常处理
        if(e instanceof  JsonException){
            //json返回可处理的异常
            JsonException jsonException = (JsonException) e;
            return ResultUtil.error(jsonException.getCode(),e.getMessage());
        }else{
            e.printStackTrace();
            //记录错误日志
            JsonException jsonException = new JsonException(ResultEnum.UNKNOW_ERROR);
            //json返回不可处理的异常
            return ResultUtil.error(jsonException.getCode(),jsonException.getMessage());
        }
    }
}
