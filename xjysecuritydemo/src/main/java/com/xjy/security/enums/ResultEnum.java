package com.xjy.security.enums;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/210:17
 * @Description: 错误信息
 * @Modified By:
 */
public enum ResultEnum {
    //用枚举的方式代替properties的国际化配置
    UNKNOW_ERROR(-1,"系统错误"),
    SUCCESS(0,"成功"),
    ONE_TWO_TWO(122,"这个错了");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
