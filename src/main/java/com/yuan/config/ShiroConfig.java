package com.yuan.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加内置过滤器

        /**
         * anon: 无需认证可以访问
         * authc:必须认证可以访问
         * user： 必须有记住我可以访问
         * perms: 拥有某个资源权限才可访问
         * role：拥有某个角色才可以访问
         */

        //此处不做授权工作，做事配置信息而已
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/touser", "authc");
        filterMap.put("/blog/**", "perms[all]");
        filterMap.put("/user/toadmin", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录页面
        bean.setLoginUrl("/");
        //未授权跳转页面
        bean.setUnauthorizedUrl("/user/unauth");


        return bean;
    }

    //ShiroDialect ：用来整合shrio-thymeleaf

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }


}
