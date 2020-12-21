package com.oac.framework.shiro.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;

/**
 *  自定义密码验证器
 *  （1）这个类实现了shiro的SimpleCredentialsMatcher接口来重新密码验证方法
 *  （2）同时写了加密的方法encrypt(String data)，拿到前台传过来的密码后，使用该方法加密后与数据库的拿到的密码进行比对，返回ture或者fasle。
 *  （3）当然，前台注册时，保存数据库的密码也需要用同样的形式把密码加密后再保存。
 */
public class CustomMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = encrypt(String.valueOf(usernamePasswordToken.getPassword()));
        String mysqlpwd = (String) info.getCredentials();
        return this.equals(password, mysqlpwd);
    }

    // 将传进来的密码进行加密的方法
    private String encrypt(String data){
        String sha384Hex = new Sha384Hash(data).toBase64();
        return sha384Hex;
    }

}
