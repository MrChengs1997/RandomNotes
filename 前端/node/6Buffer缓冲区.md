# BUffER



- 从结构上看Buffer非常像一个数组，它的元素为16进制的两位数。
- 实际上一个元素就表示内存中的一个字节。
- 实际上Buffer中的内存不是通过JavaScript分配的，而是在底层通过C++申请的。
- 也就是我们可以直接通过Buffer来创建内存中的空间。



Buffer的结构和数组很像，操作的方法也类似数组

数组不能存储二进制文件，Buffer专门存储二进制数据，十六进制显示

使用Buffer不需要引入直接使用即可

Buffer中的每一个元素的范围是从 00 - FF（0000 0000--1111 1111）

**1个字节（byte）=8bit（位）**







测试

```
//使用Buffer
var str = "Buffer";

//讲一个字符窜 保存到buffer中
//将数据分配一个新的 Buffer
var buf = Buffer.from(str);

console.log(buf);

console.log(str.length);//占内存的大小
console.log(buf.length);//字符串长度
```

结果

```
F:\node\node.exe F:\WebStorm-2019.3\node\Buffer\node.js
<Buffer 42 75 66 66 65 72>
6
6
```



# 文档

文档：http://nodejs.cn/api/buffer.html



```
//过期方法创建Buffer
var buf1 = new Buffer(10);
console.log(buf1.length);//10

var buf = Buffer.alloc(10);
console.log(buf.length);//10

//通过索引来操作buf中的元素
console.log(buf);//00 00 00 00 00 00 00 00 00 00
buf[0] = 33;
console.log(buf);//21 00 00 00 00 00 00 00 00 00

//越界问题
//Buffer大小一旦确定不能进行更改
//Buffer实际上对内存的直接操作
//alloc分配连续的空间
buf[11] = 33;
console.log(buf);//21 00 00 00 00 00 00 00 00 00

//将数据转换为二进制，但是最多保存8位
//此时要么舍弃前面的要么舍弃后面的（直至二进制数为8位）
//此时直接取二进制的后八位，超过范围就进行舍弃
buf[0] = 256;
console.log(buf);//<Buffer 00 00 00 00 00 00 00 00 00 00>

//读取元素
buf[0] = 33;
//显示则是进行十进制进行显示
console.log(buf[0]);//33


//Class Method: Buffer.allocUnsafe(size)
//新创建的 Buffer 的内容是未知的，可能包含敏感数据
var  buf3 = Buffer.allocUnsafe(10);
console.log(buf3);//<Buffer a8 c9 e8 02 00 00 00 00 b0 c9>


//将数据分配一个新的 Buffer
//Buffer.from(str)
```





