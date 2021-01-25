package com.jiuzhang.rpcdomain.req;

import java.io.Serializable;

/**
 * @author by
 * @since 2020/04/12 携带注册信息请求的entity
 */
public class RegisterReq implements Serializable {

    private static final long serialVersionUID = -690477244058984705L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 验证码
     */
    private String captcha;

    /**
     * Constructor.
     */
    public RegisterReq() {
    }

    /**
     * Gets get username.
     *
     * @return the get username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets set username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets get password.
     *
     * @return the get password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets set password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets get captcha.
     *
     * @return the get captcha
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * Sets set captcha.
     *
     * @param captcha the captcha
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * Gets get email.
     *
     * @return the get email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets set email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
