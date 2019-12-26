## 1 **MDN** **文档**

https://developer.mozilla.org/zh-CN/docs/Web/API/XMLHttpRequest



## 2 **理解**

\1. 使用 XMLHttpRequest (XHR)对象可以与服务器交互, 也就是发送 ajax 请求 

\2. 前端可以获取到数据，而无需让整个的页面刷新。 

\3. 这使得 Web 页面可以只更新页面的局部，而不影响用户的操作。



## **3.** **区别一般** **http** **请求与** **ajax** **请求** 

\1. ajax 请求是一种特别的 http 请求 

\2. 对服务器端来说, 没有任何区别, 区别在浏览器端 

\3. 浏览器端发请求: 只有 XHR 或 fetch 发出的才是 ajax 请求, 其它所有的都是 

非 ajax 请求 

\4. 浏览器端接收到响应 

​		(1) 一般请求: 浏览器一般会直接显示响应体数据, 也就是我们常说的刷新/ 

跳转页面 

​		(2) ajax 请求: 浏览器不会对界面进行任何更新操作, 只是调用监视的回调 

函数并传入响应相关数据 



## **4. API** 

\1. XMLHttpRequest(): 创建 XHR 对象的构造函数 

\2. status: 响应状态码值, 比如 200, 404 

\3. statusText: 响应状态文本 

\4. readyState: 标识请求状态的只读属性 

​				0: 初始 

​				1: open()之后 

​				2: send()之后 

​				3: 请求中 

​				4: 请求完成 

\5. onreadystatechange: 绑定 readyState 改变的监听 

\6. responseType: 指定响应数据类型, 如果是'json', 得到响应后自动解析响应 

体数据 

\7. response: 响应体数据, 类型取决于 responseType 的指定 

\8. timeout: 指定请求超时时间, 默认为 0 代表没有限制 

\9. ontimeout: 绑定超时的监听 

\10. onerror: 绑定请求网络错误的监听 

\11. open(): 初始化一个请求, 参数为: (method, url[, async]) 

\12. send(data): 发送请求 

\13. abort(): 中断请求 

\14. getResponseHeader(name): 获取指定名称的响应头值 

\15. getAllResponseHeaders(): 获取所有响应头组成的字符串 

\16. setRequestHeader(name, value): 设置请求头 





## **5 XHR** **的** **ajax** **封装**(简单版 **axios)**

**特点** 

1. 函数的返回值为 promise, 成功的结果为 response, 异常的结果为 error 
2. 能处理多种类型的请求: GET/POST/PUT/DELETE 
3. 函数的参数为一个配置对象 
		{
			url: '', 
			// 请求地址 
			method: '', // 请求方式 GET/POST/PUT/DELETE 
			params: {}, // GET/DELETE 请求的 query 参数 
			data: {}, // POST 或 DELETE 请求的请求体参数 
		} 
4. 响应 json 数据自动解析为 js













