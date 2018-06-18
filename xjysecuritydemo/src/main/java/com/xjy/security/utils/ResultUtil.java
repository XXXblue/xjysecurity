package com.xjy.security.utils;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/29:48
 * @Description: 结果工具类
 * @Modified By:
 */
public class ResultUtil {

    public static Result success(Object o){
        Result result = new Result();
        result.setMsg("成功");
        result.setCode(1);
        result.setData(o);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setData(null);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
