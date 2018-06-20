package com.xjy.security.browser.support;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/199:45
 * @Description:   返回结果类
 * @Modified By:
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
