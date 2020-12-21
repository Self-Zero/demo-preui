package com.oac.project.system.user.controller;

import com.oac.framework.shiro.domain.User;
import com.oac.project.system.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/login")
    public String loginOne(){
        System.out.println("走的get请求");
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginTwo(@Param("userCode") String userCode,
                           @Param("password") String password, Model model,
                           HttpServletRequest request){
        System.out.println("走的post请求");
        System.out.println("userCode:"+userCode);
        System.out.println("password:"+password);
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(userCode,password);
        Boolean isadmin = false;
        try {
            // 3.执行登录认证
            subject.login(token);
            System.out.println("--------------------------------------------------------------");
            isadmin = subject.hasRole("admin");
            //log.info("是否为管理员："+isadmin);
            System.out.println("是否为管理员："+isadmin);
            System.out.println("--------------------------------------------------------------");
            Object principal = SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByName(principal.toString());
            HttpSession session = request.getSession();
            session.setAttribute("users",user);
        }catch (UnknownAccountException e){
            // e.printStackTrace();
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //e.printStackTrace();
            model.addAttribute("msg","密码错误");
            return "login";
        }
        if(isadmin){
            return "redirect:/";
        }else{
            return "redirect:/unauth";
        }
    }

    @RequestMapping(value = "/unauth")
    public String unauth(){
        return "403";
    }

    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"select", "update", "insert"}, logical = Logical.AND)
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    /*@RequiresPermissions(value = "delete")
    @RequestMapping("/delete")
    public String getA4Page(){
        return "user/delete";
    }

    @RequiresPermissions(value = {"select", "update", "delete"}, logical = Logical.AND)
    @RequestMapping("/order")
    public String getA3Page(){
        return "user/order";
    }*/
}