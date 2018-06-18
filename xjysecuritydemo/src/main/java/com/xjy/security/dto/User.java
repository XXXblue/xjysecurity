package com.xjy.security.dto;

import java.util.Date;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1716:14
 * @Description:
 * @Modified By:
 */
public class User {
    private String username;
    private String password;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
