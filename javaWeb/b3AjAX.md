## **什么是**

AJAX 即“**A**synchronous **J**avascript ***A****nd* **X**ML”（异步 JavaScript 和 XML），是指一种创建交互式网页应用的网页开发 技术。

ajax 是一种浏览器通过 js 异步发起请求，局部更新页面的技术。 

Ajax 请求的局部更新，浏览器地址栏不会发生变化 

局部更新不会舍弃原来页面的内容





```java
package com.mrchengs.servlet;

import com.google.gson.Gson;
import com.mrchengs.bean.Students;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ajaxServlet extends  BaseServlet {

    protected void ajxj(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Students students= new Students();
        students.setAge("2");
        students.setName("mr");

        //Gson
        Gson gson = new Gson();
        // toJson 方法可以把 java 对象转换成为 json 字符串
        String studentJson = gson.toJson(students);


        resp.getWriter().write(studentJson);

    }
}

```





```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <script type="text/javascript">
        function ajaxRequest() {
            //创建对象
            var xmlhttprequest = new XMLHttpRequest();
            //调用open方法设置请求参数
            xmlhttprequest.open("GET","http://localhost:8080/web_war_exploded/ajax?action=ajxj",true);
            //在 send 方法前绑定 onreadystatechange 事件，处理请求完成后的操作
            xmlhttprequest.onreadystatechange = function (ev) {
                if (xmlhttprequest.readyState ==4 && xmlhttprequest.status == 200){
                    var json = JSON.parse(xmlhttprequest.responseText);
                    alert(json.name)
                    alert(json.age)
                    document.getElementById("div01").innerHTML = "name：" + json.name + " , age：" + json.age;
                }
            };
            xmlhttprequest.send();
        }

    </script>
</head>
<body>


<button onclick="ajaxRequest()">ajax request</button>
<div id="div01"> </div>

</body>
</html>


```













