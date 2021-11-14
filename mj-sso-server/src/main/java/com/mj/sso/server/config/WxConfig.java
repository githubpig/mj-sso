package com.mj.sso.server.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author piggy
 * @desciption
 * @date 2021/10/19 - 10:38
 */
@Component
public class WxConfig implements InitializingBean {

    @Value("${weixin.appid}")
    private String appId;
    @Value("${weixin.secret}")
    private String secret;
    @Value("${weixin.redirecturi}")
    private String redirectUri;
    @Value("${weixin.authorizedUrl}")
    private String authorizedUrl;
    @Value("${weixin.access_token}")
    private String accessToken;
    @Value("${weixin.userinfo}")
    private String userinfo;
    @Value("${weixin.smredirectUri}")
    private String smredirectUri;

    @Value("${mj.sso.manage.url}")
    private String manageUrl;




//    @Value("${qq_app_id}")
//    private String qqAppId;
//    @Value("${qq_app_key}")
//    private String qqAppKEY;
//    @Value("${qq_redirect_uri}")
//    private String qqRedirectURI;



    public static String APP_ID;
    public static String SECRET;
    public static String REDIRECT_URI;
    public static String AUTHORIZED_URL;
    public static String ACCESS_TOKEN;
    public static String USER_INFO;
    public static String SM_REDIRECTURI;
    public static String MANAGE_URL;


//    public static String QQ_APP_ID;
//    public static String QQ_APP_KEY;
//    public static String QQ_REDIRECT_URI;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        SECRET = secret;
        REDIRECT_URI =redirectUri;
        AUTHORIZED_URL = authorizedUrl;
        ACCESS_TOKEN = accessToken;
        USER_INFO = userinfo;
        SM_REDIRECTURI = smredirectUri;
        MANAGE_URL = manageUrl;
//        QQ_APP_ID = qqAppId;
//        QQ_APP_KEY = qqAppKEY;
//        QQ_REDIRECT_URI = qqRedirectURI;
    }
}
