package com.mj.sso.server.core.model;

import java.io.Serializable;

/**
 * @author piggy
 * @desciption
 * @date 2021/11/6 - 23:24
 */
public class WxAuth implements Serializable {

    public WxAuth() {
    }

    private String code;
    private String redirectUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
