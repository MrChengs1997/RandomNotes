package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author ccrr
 */
@Controller
public class MyController {

    @RequestMapping({"/","index"})
    public String toIndex(Model model){
        model.addAttribute("hello","helloword");
        return "index";
    }

    @RequestMapping("/user/add")
    public  String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public  String update(){
        return "user/update";
    }

    //跳转至登陆页面
    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    //登陆
    //r认证逻辑操作
    @RequestMapping("/login")
    public String Login(String username,String password){
        //获取当前的认证
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登陆数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try {
            //执行登录方法
            subject.login(usernamePasswordToken);
            return "index";
        }catch (UnknownAccountException e){
           // model.addAttribute("msg","用户民不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
           // model.addAttribute("msg","密码不存在");
            return "login";
        }
    }

    //未授权走到未授权页面
    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthic(){
        return "未授权无法访问";
    }


}
