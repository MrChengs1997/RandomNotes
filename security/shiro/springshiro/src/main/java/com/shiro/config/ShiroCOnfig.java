package com.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ccrr
 */
@Configuration
public class ShiroCOnfig {

    //shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean( @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //认证=============================================
        //添加shiro内置过滤器
        // anon ：无需认证就可以访问
        // authc ：必须认证之后才能访问
        // user ： 必须拥有记住我才能使用
        // perms ：拥有某个字段的权限才能访问
        //链式
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/user/add","authc");
        filterChainDefinitionMap.put("/user/update","authc");
        //filterChainDefinitionMap.put("/user/*","authc");

        //授权==============================================
        //没权限
        //There was an unexpected error (type=Unauthorized, status=401).
        filterChainDefinitionMap.put("/user/add","perms[user:add]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //没有权限设置登陆页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //未授权访问页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        return shiroFilterFactoryBean;
    }


    //DefaultWebSecurity
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager( @Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象
    @Bean("userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }



}
