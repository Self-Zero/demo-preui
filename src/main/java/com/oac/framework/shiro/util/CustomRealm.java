package com.oac.framework.shiro.util;

import com.oac.framework.shiro.domain.Permission;
import com.oac.framework.shiro.domain.Role;
import com.oac.framework.shiro.domain.User;
import com.oac.project.system.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *  自定义Realm
 *  当密码验证通过后，就到了我们的自定义realm，在我们自定义realm中实现了AuthorizingRealm接口，
 *  将其方法进行重写，将各种权限对用户进行授权，同时对用户身份进行验证
 *  代码如下，每一行代码具体含义十分详细了。
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     * @param  authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 身份认证方法
        System.out.println("----------------身份认证方法------------------");
        // 从token获取用户名，从主体传过来的认证信息中获取
        // 加这一步的目的是在post请求时会先进入认证然后再到请求
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        // 获取用户的登录信息，用户名
        String userCode = authenticationToken.getPrincipal().toString();
        // 根据service调用用户名，查找用户的全部信息
        // 通过用户名到数据库获取凭证
        User user = userService.queryByName(userCode);
        if (user == null){
            return null;
        }else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            // System.out.println("getName():"+getName());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode,user.getPassword().toString(),getName());
            return simpleAuthenticationInfo;

        }
    }

    //角色权限和对应权限添加
    //Authorization授权，将数据库中的角色和权限授权给输入的用户名
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限认证方法
        System.out.println("----------------权限认证方法------------------");
        // 获取登录的用户名
        // String userCode = (String) SecurityUtils.getSubject().getPrincipal();
        String userCode = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("userCode:"+userCode);
        // 到数据库里查询要授权的内容
        User user = userService.queryByName(userCode);
        System.out.println(user);
        // 记录用户的所有角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role r:user.getRoles()){
            // 将所有的角色信息添加进来
            System.out.println("角色"+r.getRoleName());
            simpleAuthorizationInfo.addRole(r.getRoleName());
            for (Permission p:r.getPermissions()){
                //将此次遍历到的角色的所有权限拿到，添加进来
                System.out.println("权限"+p.getPermissionName());
                simpleAuthorizationInfo.addStringPermission(p.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }
}
