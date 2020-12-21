package com.oac.framework.shiro.util;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义记住我过滤器
 * 该过滤器是为了实现记住我后，用户再次登陆不需要进行权限验证，就能到达首页，后面会介绍当不使用该过滤器的后果。
 * （1）该过滤器实现当用户通过isRemembered（）登陆，没有通过isAuthenticated（）登陆时拿到user的session信息，
 *  保证后面到达首页时候能拥有各种跟通过isAuthenticated（）登陆时的session信息。
 * （2）过滤器完成了登陆条件的过滤，要么通过权限认证登陆成功，要么通过记住我登陆成功。
 * （3）在shiroconfig类中会进行shiro访问权限配置。
 */
public class CustomFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request,response);
        if (!(subject.isAuthenticated() && subject.isRemembered())){
            if (subject.getSession().getAttribute("user") == null && subject.getPrincipal() != null){
                subject.getSession().setAttribute("user",subject.getPrincipal());
            }
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
