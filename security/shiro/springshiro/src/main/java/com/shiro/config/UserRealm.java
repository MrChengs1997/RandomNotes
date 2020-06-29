package com.shiro.config;

import com.shiro.pojo.Users;
import com.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ccrr
 */
//自定义UserRealm
public class UserRealm  extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("AuthorizationInfo...授权");
        //UsernamePasswordToken token = (UsernamePasswordToken) principalCollection;
        //System.out.println(token.getUsername());

        //把权限赋给用户
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        //进行给用户授权
        //simpleAuthenticationInfo.addStringPermission("user:add");

        //用户授权应该有一张数据表字段进行代表用户的授权信息
        //拿到当前用户对象
        Subject subject = SecurityUtils.getSubject();
        Users currentUser = (Users) subject.getPrincipal();
        //设置用户权限
        simpleAuthenticationInfo.addStringPermission(currentUser.getPerms());

        return simpleAuthenticationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("AuthenticationInfo...认证");

        //直接走下面的逻辑
        // @RequestMapping("/login")
        //String userName = "mc";
       // String password = "1234";

        //会从上面的url中进行取数据
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //查询真实的数据库
        Users users = userService.queryUser(token.getUsername());

        if (users == null){
            return null;
            //UnknownAccountException
        }

//        if (!token.getUsername().equals(userName)){
//            //抛出异常---》UnknownAccountException
//            return null;
//        }


        //密码认证，shiro自己做密码认证，以防密码泄露
        //参数一：获取当前用户的认证:再授权就可以使用 Subject subject = SecurityUtils.getSubject();去获取
        //参数二：传递密码对象
        //参数三：认证名
       // return new SimpleAuthenticationInfo("",password,"");
        //可以加密： MD5，MD5盐值加密
        return new SimpleAuthenticationInfo(users,users.getPassword(),"");
    }
}
