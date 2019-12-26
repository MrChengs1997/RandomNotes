## **json-server** **是什么**?



用来快速搭建 REST API 的工具包



## **使用** **json-server**

1. 在线文档: https://github.com/typicode/json-server

2. 下载: npm install -g json-server

3. 目标根目录下创建数据库 json 文件: db.json

   ```
   {
     "posts": [
       {
         "id": 1,
         "title": "json-server",
         "author": "typicode"
       },
       {
         "id": 2,
         "title": "json-server2",
         "author": "typicode2"
       }
     ],
     "comments": [
       {
         "id": 1,
         "body": "some comment",
         "postId": 1
       }
     ],
     "profile": {
       "name": "typicode"
     }
   }
   ```

4. 启动服务器执行命令:

   ```
   json-server --watch db.json
   ```

   ```
   GET /db 200 15.508 ms - 330
   GET /__rules 404 6.744 ms - 2
   GET /posts 200 12.849 ms - 154
   GET /comments 200 7.671 ms - 68
   GET /profile 200 9.835 ms - 24
   
   ```

   ```
   http://localhost:3000/posts?id=1
   ```

   





## **使用** **axios** **访问测试**



引入：

```
 <script src="https://cdn.bootcss.com/axios/0.19.0/axios.js"></script>
```

按钮：

```
 <button onclick="testGet()">GET请求</button>
    <button onclick="testPost()">POST请求</button>
    <button onclick="testPut()">PUT请求</button>
    <button onclick="testDelete()">DELETE请求</button>
```



js脚本：

```
 function testGet() {
      // axios.get('http://localhost:3000/posts')
      // axios.get('http://localhost:3000/posts/1')
      axios.get('http://localhost:3000/posts?id=1')
        .then(response => {
          console.log('/posts get', response.data)
        })
    }

    function testPost() {
      //{"title": "json-server3", "author": "typicode3"}:post传递的数据
      axios.post('http://localhost:3000/posts', {"title": "json-server3", "author": "typicode3"})
        .then(response => {
          console.log('/posts post', response.data)
        })
    }

    function testPut() {
      axios.put('http://localhost:3000/posts/3', {"title": "json-server...", "author": "typicode..."})
        .then(response => {
          console.log('/posts put', response.data)
        })
    }

    function testDelete() {
      axios.delete('http://localhost:3000/posts/3')
        .then(response => {
          console.log('/posts delete', response.data)
        })
    }

```







