# 配置简单的接口



```
@Controller
@CrossOrigin
public class TestController {

    @GetMapping("/testcontroller")
    @ResponseBody
    public String test(String name,Integer password){

        User user = new User();
        user.setUser(name);
        user.setPassword(password);
        System.out.println(user.getUser());
        System.out.println(user.getPassword());
        return JSON.toJSONString(user);
    }
}

```





# 封装进行调接口

```
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title></title>
</head>
<body ng-app="myApp">

    <div ng-controller="MyCtrl">
      <button ng-click="btn()">点击事件</button>
    </div>

</body>
<script type='text/javascript' src="../../js/angular-1.5.5/angular.js"></script>
<script  type='text/javascript' >

  angular.module('myApp', [])
   .controller('MyCtrl', ['$scope','$http', function ($scope,$http) {

     $scope.btn= function () {
       $http({
         method: "GET",
         url: "http://localhost:8082/design/testcontroller",
         params: {name:'qwer',password:123}
       })
         .success(function (data, status, headers, config) {
                 alert('success')
                 alert(data.name)
                 alert(data.password)
                 alert(status)
                 alert(headers)
                 alert(config)
        })
        .error(function (data, status, headers, config) {
                 alert(data)
        });
     }

   }])
</script>
</html>
```





# GET请求语法

方式1

```
$http({
    method: "GET",
    url: "",
  params: myParams
})
.success(function (data, status, headers, config) {
   
})
.error(function (data, status, headers, config) {
    
});
```

方式2：

```
$http.get(url)

```





# POST请求

方式1：

```
$http({
    method: "POST",
    url: "",
  data: myData,
})
.success(function (data, status, headers, config) {
   
})
.error(function (data, status, headers, config) {
    
});
```

方式2：

```
$http.post(url, data)
  .success(function (data, status, headers, config) {
     
  })
  .error(function (data, status, headers, config) {
      
  });
```



# JSON跨域请求

```
  $http.jsonp('http://localhost:3000/node_jsonp?callback=JSON_CALLBACK&username=Tom2&passwod=123abc2')
      .success(function (data) {
        $scope.persons = data;
      })
      .error(function (data) {
        alert(data);
      });
```















