## **Redis是什么**

①Redis:REmote DIctionary Server(远程字典服务器)

②是完全开源免费的，用C语言编写的，遵守BSD协议，是一个高性能的(key/value)分布式内存数据库，**基于内存**运行

并**支持持久化**的**NoSQL数据库**，是当前最热门的NoSql数据库之一,也被人们称为数据结构服务器



## **能干嘛**

①内存存储和持久化：redis支持**异步**将内存中的数据写到硬盘上，同时不影响继续服务

②取最新N个数据的操作，如：可以将最新的10条评论的ID放在Redis的List集合里面

③模拟类似于HttpSession这种需要设定过期时间的功能

④发布、订阅消息系统

⑤定时器、计数器



##**官网**

①.官网http://redis.io/

②.中文翻译的官网http://www.redis.cn/



## **功能**

数据类型、基本操作和配置

持久化和复制，RDB/AOF

事务的控制

主从复制



## **在Linux上安装可能出现的问题**

运行make命令时故意出现的错误解析：

```
gcc是linux下的一个编译程序，是C程序的编译工具。
GCC(GNU Compiler Collection) 是 GNU(GNU's Not Unix) 计划提供的编译器家族，它能够支持 C, C++,  Objective-C, Fortran, 
Java 和 Ada 等等程序设计语言前端，同时能够运行在 x86, x86-64, IA-64,  PowerPC, SPARC 和 Alpha 等等几乎目前所有的硬件平台
上。鉴于这些特征，以及 GCC 编译代码的高效性，使得 GCC 成为绝大多数自由软件开发编译的首选工具。虽然对于程序员们来说，编译器只是一个
工具，除了开发和维护人员，很少有人关注编译器的发展，但是 GCC 的影响力是如此之大，它的性能提升甚至有望改善所有的自由软件的运行效率，
同时它的内部结构的变化也体现出现代编译器发展的新特征。
```



 首先安装gcc

```
yum install gcc-c++
```

二次make：jemalloc/jemalloc.h：没有那个文件或目录

如果之前使用make安装之后，报了这个错，一定要将原来文件删除再次进行安装

不然会报错找不到文件夹



## **安装**

```
$ wget http://download.redis.io/releases/redis-3.2.4.tar.gz
$ tar xzf redis-3.2.4.tar.gz
$ cd redis-3.2.4
$ make

$make install
```

 安装成功之后，下面的命令启动服务器

```
 $ src/redis-server
```

客户端测试命令：

```
$ src/redis-cli
redis> set key val1
OK
redis> get key
"val1"
```

一般不建议直接使用源码，所以此时：在根目录建立myredis文件夹

赋值配置文件的代码

```
cp redis.conf /myredis
```

启动：

```
redis-server /myredis/redis.conf
```

 进入客户端的编译测试系统：

```
redis-cli -p 6379
```

```
127.0.0.1:6379> ping
PONG
127.0.0.1:6379> set key value
OK
127.0.0.1:6379> get key
"value"
```



## **启动之后的讲解**

 查看默认安装目录：usr/local/bin

```
[admin@zehrde3v3xqy7gpv9e4z ~]$ cd /usr/local/bin/
[admin@zehrde3v3xqy7gpv9e4z bin]$ ll
total 32736
-rwxr-xr-x 1 root root 4366648 Oct 19 17:27 redis-benchmark
-rwxr-xr-x 1 root root 8111856 Oct 19 17:27 redis-check-aof
-rwxr-xr-x 1 root root 8111856 Oct 19 17:27 redis-check-rdb
-rwxr-xr-x 1 root root 4806880 Oct 19 17:27 redis-cli
lrwxrwxrwx 1 root root      12 Oct 19 17:27 redis-sentinel -> redis-server
-rwxr-xr-x 1 root root 8111856 Oct 19 17:27 redis-server
```

 **redis-benchmark**

```
[admin@zehrde3v3xqy7gpv9e4z bin]$ redis-benchmark
====== PING_INLINE ======
  100000 requests completed in 2.87 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

56.37% <= 1 milliseconds
99.93% <= 2 milliseconds
100.00% <= 2 milliseconds
34867.50 requests per second

====== PING_BULK ======
  100000 requests completed in 2.86 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

57.33% <= 1 milliseconds
99.94% <= 2 milliseconds
100.00% <= 2 milliseconds
34940.60 requests per second

====== SET ======
  100000 requests completed in 2.90 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

55.90% <= 1 milliseconds
99.77% <= 2 milliseconds
99.96% <= 3 milliseconds
99.97% <= 14 milliseconds
100.00% <= 14 milliseconds
34518.46 requests per second

====== GET ======
  100000 requests completed in 2.91 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

55.49% <= 1 milliseconds
99.88% <= 2 milliseconds
99.95% <= 9 milliseconds
99.98% <= 10 milliseconds
100.00% <= 10 milliseconds
34399.72 requests per second

====== INCR ======
  100000 requests completed in 2.90 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

55.60% <= 1 milliseconds
99.84% <= 2 milliseconds
99.95% <= 12 milliseconds
99.96% <= 13 milliseconds
99.98% <= 14 milliseconds
100.00% <= 14 milliseconds
34423.41 requests per second

====== LPUSH ======
  100000 requests completed in 2.94 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

53.33% <= 1 milliseconds
99.85% <= 2 milliseconds
99.96% <= 3 milliseconds
100.00% <= 3 milliseconds
34025.18 requests per second

====== RPUSH ======
  100000 requests completed in 2.96 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

53.62% <= 1 milliseconds
99.91% <= 2 milliseconds
99.95% <= 14 milliseconds
99.98% <= 15 milliseconds
100.00% <= 15 milliseconds
33818.06 requests per second

====== LPOP ======
  100000 requests completed in 2.95 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

53.06% <= 1 milliseconds
99.86% <= 2 milliseconds
99.97% <= 3 milliseconds
100.00% <= 3 milliseconds
33921.30 requests per second

====== RPOP ======
  100000 requests completed in 3.00 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

52.03% <= 1 milliseconds
99.80% <= 2 milliseconds
99.95% <= 13 milliseconds
99.96% <= 14 milliseconds
99.98% <= 15 milliseconds
100.00% <= 15 milliseconds
33288.95 requests per second

====== SADD ======
  100000 requests completed in 2.97 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

53.54% <= 1 milliseconds
99.59% <= 2 milliseconds
99.89% <= 3 milliseconds
99.94% <= 4 milliseconds
99.95% <= 8 milliseconds
99.97% <= 9 milliseconds
100.00% <= 9 milliseconds
33658.70 requests per second
```



 **1.单进程**

```
单进程模型来处理客户端的请求。对读写等事件的响应
是通过对epoll函数的包装来做到的。Redis的实际处理速度完全依靠主进程的执行效率
```

```
`epoll是Linux内核为处理大批量文件描述符而作了改进的epoll，是Linux下多路复用IO接口select/poll的增强版本，<br>它能显著提高程序在大量并发连接中只有少量活跃的情况下的系统CPU利用率。`
```

**2.默认16个数据库，类似数组下表从零开始，初始默认使用零号库**

```
设置数据库的数量，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id

  databases 16
```

**3.select命令切换数据库**

默认第一个库是0

不同库之间的数据是不相通的

```
127.0.0.1:6379> SELECT 3
OK
127.0.0.1:6379[3]> get key
(nil)
127.0.0.1:6379[3]> 
```

**4.dbsize查看当前数据库的key的数量**

系统默认自带的有key

```
127.0.0.1:6379> DBSIZE
(integer) 4
```

5.**flushdb**：清空当前库

6.**Flushall**；通杀全部库

**7.统一密码管理，16个库都是同样密码，要么都OK要么一个也连接不上**

**8.Redis索引都是从零开始**









