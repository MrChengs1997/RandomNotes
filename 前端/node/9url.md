# url

`url` 模块用于处理与解析 URL。

```
const url = require('url');
```

# URL 字符串与 URL 对象

URL 字符串是结构化的字符串，包含多个含义不同的组成部分。



使用 WHATWG 的 API 解析 URL 字符串：

```
const myURL =
  new URL('https://xxxx.xxxx:8080/x/x?query=string#hash');
```



# URL 类

```
const url = require('url');

//url.hash
//获取及设置 URL 的片段部分。
const myURL = new URL('https://example.org/foo#bar');
console.log(myURL.hash);//#bar

//url.host
//获取及设置 URL 的主机部分。
console.log(myURL.host);//example.org


//url.hostname
//获取及设置 URL 的主机名部分。 url.host 和 url.hostname 之间的区别是 url.hostname 不包含端口
console.log(myURL.hostname);//example.org

//url.href
//获取及设置序列化的 URL
//获取 href 属性的值等同于调用 url.toString()。
console.log(myURL.href);//https://example.org/foo#bar

//url.origin
//获取只读的序列化的 URL 的 origin。
const myURL1 = new URL('https://example.org/foo/bar?baz');
console.log(myURL1.origin);//https://example.org

//url.password
//获取及设置 URL 的密码部分。
//分配给 password 属性的值中包含的无效 URL 字符是百分比编码的
const myURL2 = new URL('https://abc:xyz@example.com');
console.log(myURL2.password);//xyz

//url.pathname
//获取及设置 URL 的路径部分。
//分配给 pathname 属性的值中包含的无效 URL 字符是百分比编码的。
const myURL3 = new URL('https://example.org/abc/xyz?123');
console.log(myURL3.pathname);///abc/xyz

//url.port
//获取及设置 URL 的端口部分。
//端口值可以是数字或包含 0 到 65535（含）范围内的数字字符串。
// 将值设置为给定 protocol 的 URL 对象的默认端口将会导致 port 值变为空字符串（''）。
const myURL4 = new URL('https://example.org:8888');
console.log(myURL4.port);//8888

//url.protocol
//获取及设置 URL 的协议部分。
//分配给 protocol 属性的无效协议值将会被忽略。
console.log(myURL4.protocol);//https:


//特殊协议
//WHATWG URL 标准认为少数 URL 协议规范在解析和序列化方面具有特殊性。
// 使用这些特殊协议之一解析 URL 时， url.protocol 属性可能会被更改为其他特殊协议
// 但不能更改为非特殊协议，反之亦然。

//url.search
//获取及设置 URL 的序列化查询部分。
const myURL5 = new URL('https://example.org/abc?123');
console.log(myURL5.search);//?123
myURL5.search = 'abc=xyz';
console.log(myURL5.href);//https://example.org/abc?abc=xyz

//url.searchParams
//获取表示 URL 查询参数的 URLSearchParams 对象。
// 该属性是只读的。 使用 url.search 设置来替换 URL 的整个查询参数。

///url.username
const myURL6 = new URL('https://abc:xyz@example.com');
console.log(myURL6.username);//abc

//url.toString()
//在 URL 对象上调用 toString() 方法将返回序列化的 URL。
//返回值与 url.href 和 url.toJSON() 的相同
console.log(myURL6.toString());//https://abc:xyz@example.com/

//url.toJSON()
//在 URL 对象上调用 toJSON() 方法将返回序列化的 URL。
// 返回值与 url.href 和 url.toString() 的相同。
const myURLs = [
    new URL('https://www.example.com'),
    new URL('https://test.example.org')
];
console.log(JSON.stringify(myURLs));//["https://www.example.com/","https://test.example.org/"]



```





# URLSearchParams 类

`URLSearchParams` API 提供对 `URL` 查询部分的读写权限。 `URLSearchParams` 类也能够与以下四个构造函数中的任意一个单独使用。`URLSearchParams` 类也可在全局对象上使用。



















