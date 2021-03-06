package com.jiuzhang.usercenter.rpcdomain.req;

import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
public class LoginReq implements Serializable {

    private static final long serialVersionUID = -7559061990780206659L;

    private String username;
    private String password;

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
