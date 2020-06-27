## SpringSecurity

web开发中，安全第一位，过滤器拦截器...

功能需求：否



安全问题什么时会考虑？设计之初

- 漏洞，隐私泄露
- 结构一旦确定



Shiro、SpringSecurity

认证、授权

- 功能权限
- 访问权限
- 菜单权限





该框架是针对spring项目的安全框架

也是sringboot底层安全默认的技术选型

可以实现强大的web安全控制

仅仅只需要引入依赖即可



- WebSecurityCOnfigureAdapter:自定义sercrity策略
- AuthenticationManagerBuilder：自定义认证策略
- @EnableWebSecurity:开启websecurity模式



认证（Authentication）

授权（Authorization）



https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/







```xml
 <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.9.RELEASE</version>
        
        
          <!-- t'h'ythymeleaf和springsecurity整合-->
        <!-- https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity4 -->
        <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-springsecurity4</artifactId>
            <version>3.0.4.RELEASE</version>
        </dependency>
```



```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


    <table border="1px" style="margin: auto">
        <tr>
            <!-- 登陆显示用户名,角色-->
            <td sec:authorize="isAuthenticated()"><span sec:authentication="name"></span></td>
            版本有问题，无法进行显示
            <td sec:authorize="isAuthenticated()"><span sec:authentication="principal.getAuthorities()"></span></td>
        </tr>
        <tr>

            <!--登陆之后有注销 -->
            <!--登陆之前有登陆 -->
            <td><a href="#" th:href="@{/index}" >首页</a></td>
            <!-- 是否登陆-->
            <td><a href="#" th:href="@{/logout}"  sec:authorize="isAuthenticated()">注销</a></td>
             <td><a href="#" th:href="@{/toLogin}" sec:authorize="!isAuthenticated()"> 登陆</a></td>
        </tr>
        <tr>
            <td colspan="3">springsecurity</td>
        </tr>
        <tr>
            <td><a href="#" th:href="@{/level1/1}">level1--1</a></td>
            <td><a href="#" th:href="@{/level1/2}">level1--2</a></td>
            <td><a href="#" th:href="@{/level1/3}">level1--3</a></td>
        </tr>
        <tr>
            <td><a href="#" th:href="@{/level2/1}">level2--1</a></td>
            <td><a href="#" th:href="@{/level2/2}">level2--2</a></td>
            <td><a href="#" th:href="@{/level2/3}">level2--3</a></td>
        </tr>
        <tr>
            <td><a href="#" th:href="@{/level3/1}">level3--1</a></td>
            <td><a href="#" th:href="@{/level3/2}">level3--2</a></td>
            <td><a href="#" th:href="@{/level3/3}">level3--3</a></td>
        </tr>


    </table>

</body>
</html>
```

2.0.9版本之后可能不能实现该html中的功能





