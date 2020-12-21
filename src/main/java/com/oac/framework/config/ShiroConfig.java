package com.oac.framework.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.oac.framework.shiro.util.CustomFilter;
import com.oac.framework.shiro.util.CustomMatcher;
import com.oac.framework.shiro.util.CustomRealm;
import com.oac.framework.shiro.util.CustomSessionManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 1.进行全局配置
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        //shiro对象
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //登录界面，没有登录的用户访问授权的界面就会跳转到该界面
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/");
        bean.setUnauthorizedUrl("/unauth");
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
        filterMap.put("myRememberFilter", customFilter());
        // MAP
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        // 认证顺序是从上往下执行。
        linkedHashMap.put("/logout", "logout");//在这儿配置登出地址，不需要专门去写控制器。
        linkedHashMap.put("/static/**", "anon");
        //开启注册页面不需要权限
        linkedHashMap.put("/register", "anon");
        linkedHashMap.put("/saveregister", "anon");
        //验证phone唯一
        linkedHashMap.put("/solephone", "anon");
        //获取验证码
        linkedHashMap.put("/getcode", "anon");
        //验证码判断
        linkedHashMap.put("/comparecode", "anon");
        linkedHashMap.put("/websocket", "anon");//必须开启。
        linkedHashMap.put("/**/**/css/**", "anon");//不需要验证
        linkedHashMap.put("/**/css/**", "anon");//不需要验证
        //配置错误页面
        linkedHashMap.put("error", "anon");//不需要验证
        linkedHashMap.put("/**/images/**", "anon");//不需要验证
        linkedHashMap.put("/**/layui/**", "anon");//不需要验证
        linkedHashMap.put("/**", "user");//需要进行权限验证
        bean.setFilterChainDefinitionMap(linkedHashMap);
        return bean;
    }

    // 2.配置securityManager安全管理器
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("customRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义myRealm
        securityManager.setRealm(customRealm);
        //注入自定义cacheManager
        securityManager.setCacheManager(cacheManager());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        //注入自定义sessionManager
        securityManager.setSessionManager(sessionManager());
        System.out.println(securityManager);
        return securityManager;
    }

    // 3.配置权限认证器
    @Bean("customRealm")
    public CustomRealm customRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher) {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(credentialsMatcher);
        return customRealm;
    }

    // 4.配置密码验证器
    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CustomMatcher();
    }

    // 5.配置缓存验证器
    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }
    // 6.配置Cookie管理对象

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    // 7.配置记住我Cookie对象参数
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name=remember
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // cookie的生效时间为10秒
        simpleCookie.setMaxAge(10);
        return simpleCookie;
    }

    // 8.配置sessionManager
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //请求头中url中含有JSessionId=***********************,此处设置false，不显示JSessionId
        customSessionManager.setSessionIdUrlRewritingEnabled(false);
        //这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
        customSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return customSessionManager;
    }

    // 9.注入自定义记住我过滤器
    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }

    // 10.配置shiro的生命周期
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // 11.配置shiro注解是否生效
    /**
     * 启动Shiro的注解(如@RequiresRoles, @RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

    // 12.配置前台的shiro标签，使其能够使用
    // 前台是使用shiro结合thymeleaf实现细粒度权限控制的。
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
