package com.mj.sso.sample.config;

import com.mj.sso.core.conf.Conf;
import com.mj.sso.core.filter.XxlSsoTokenFilter;
import com.mj.sso.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxueli 2018-11-15
 */
@Configuration
public class XxlSsoConfig implements DisposableBean {


    @Value("${mj.sso.server}")
    private String xxlSsoServer;

    @Value("${mj.sso.logout.path}")
    private String xxlSsoLogoutPath;

    @Value("${mj.sso.redis.address}")
    private String xxlSsoRedisAddress;

    @Value("${mj.sso.excluded.paths}")
    private String xxlSsoExcludedPaths;


    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {

        // mj.sso, redis init
        JedisUtil.init(xxlSsoRedisAddress);

        // mj.sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setName("XxlSsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new XxlSsoTokenFilter());
        registration.addInitParameter(Conf.SSO_SERVER, xxlSsoServer);
        registration.addInitParameter(Conf.SSO_LOGOUT_PATH, xxlSsoLogoutPath);
        registration.addInitParameter(Conf.SSO_EXCLUDED_PATHS, xxlSsoExcludedPaths);

        return registration;
    }

    @Override
    public void destroy() throws Exception {

        // mj.sso, redis close
        JedisUtil.close();
    }

}
