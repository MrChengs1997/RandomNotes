package com.example.springsecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ccrr
 */
@Controller
public class RouterController {

   @RequestMapping({"/","/index"})
    //@RequestMapping("/test")
    public String index(){
        return "index";
    }

    //跳转到登陆页面
    @RequestMapping("/toLogin")
    public String toLogin(String username,String password){
        return "view/login";
    }

    @RequestMapping("/level1/{id}")
    public String toLevel1(@PathVariable("id") int id){
        return "view/level1/" + id;
    }

    @RequestMapping("/level2/{id}")
    public String toLevel2(@PathVariable("id") int id){
        return "view/level2/" + id;
    }

    @RequestMapping("/level3/{id}")
    public String toLevel3(@PathVariable("id") int id){
        return "view/level3/" + id;
    }

}
