package com.example.springsecurity.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 配置security配置类
 */

//@Configuration
@EnableWebSecurity
public class SecurityConfigSEVEN extends WebSecurityConfigurerAdapter {
    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //首页所有人可以访问
        //功能页只有对应的权限才能访问

        //authorizeRequests()认证请求
        //antMatchers（）添加路径
        //permitAll（）所有人可以访问
        //hasRole()有什么身份
        http.authorizeRequests()
                .antMatchers("/index").permitAll()

                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认跳转到登陆页面，是一个系统默认提供的提供的页面
        //需要开启登录到页面
        http.formLogin();

        //定制登录页

        //loginProcessingUrl()  Gets the URL to submit an authentication request to (i.e. where username/password
        //需要设置前端form的参数
        http.formLogin().loginPage("/toLogin").usernameParameter("user").passwordParameter("pwd").loginProcessingUrl("/login");

        //默认开启csrf
        http.csrf().disable();//关闭csrf功能

        //开启注销功能，直接使用系统的url
        //	 * The following customization to log out when the URL "/custom-logout" is invoked.
        //	 * Log out will remove the cookie named "remove", not invalidate the HttpSession,
        //	 * clear the SecurityContextHolder, and upon completion redirect to "/logout-success".
        //<td><a href="#" th:href="@{/logout}" >注销</a></td>
        //logoutSuccessUrl注销成功之后的跳转url
        http.logout().logoutSuccessUrl("/index");

        //开启记住我
        //默认保存时间2周
        //cookie
        //默认参数是rember
        //重新定制需要添加.rememberMeParameter("rember")
        http.rememberMe();
    }

    /**
     * 认证
     * 密码编码：密码没有被加密
     * 在spring Security5.0+中，新增了 很多的加密方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2")
                .and()
                .withUser("user2").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2")
                .and()
                .withUser("user3").password(new BCryptPasswordEncoder().encode("123456")).roles("vip3");

    }
}

