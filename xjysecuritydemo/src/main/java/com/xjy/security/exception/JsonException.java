package com.xjy.security.exception;

import com.xjy.security.enums.ResultEnum;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1810:11
 * @Description:
 * @Modified By:
 */
public class JsonException extends RuntimeException {
    private Integer code;

    public JsonException (ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
