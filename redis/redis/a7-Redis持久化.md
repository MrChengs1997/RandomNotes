# [Redis持久化](https://www.cnblogs.com/Mrchengs/p/10056294.html)



**Redis的持久化：**

可以参考官网的说明：

https://redis.io/topics/persistence



持久下主要有一下两种：

1.RDB（Redis DataBase）

2.AOF（Append Only File）











### **RDB**

1.what

​    在指定的时间间隔内将内存中的数据集快照写入磁盘，

​    也就是行话讲的Snapshot快照，它恢复时是将快照文件直接读到内存里



Redis会**单独**创建（fork）一个子进程来进行持久化，会先将数据写入到

一个临时文件中，待持久化过程都结束了，再用这个**临时文件**替换上次**持久化好的文件**。



整个过程中，**主进程是不进行任何IO操作**的，这就确保了极高的性能

如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。**RDB的缺点是最后一次持久化后的数据可能丢失**。



2.fork

fork的作用是复制一个与当前进程一样的进程。新进程的所有数据（变量、环境变量、程序计数器等）

数值都和原进程一致，但是是一个全新的进程，并作为原进程的子进程。



3.rdb 保存的是dump.rdb文件



4.如何触发RDB快照

参考配置文件的解析--->SNAPSHOTTING快照

https://www.cnblogs.com/Mrchengs/p/10053560.html

**a.配置文件中默认的快照配置**

冷拷贝后重新使用->可以cp dump.rdb dump_new.rdb

**b.命令save或者是bgsave**

Save：save时只管保存，其它不管，全部阻塞

BGSAVE：Redis会在后台异步进行快照操作，快照同时还可以响应客户端请求。可以通过lastsave

命令获取最后一次成功执行快照的时间

**c.执行flushall命令，也会产生dump.rdb文件，但里面是空的，无意义**



5.如何恢复

**a.将备份文件 (dump.rdb) 移动到 redis 安装目录并启动服务即可**

 **b.CONFIG GET dir获取目**录



6.优势

​    a.适合大规模的数据恢复

​    b.对数据完整性和一致性要求不高



7.劣势

​    a.在一定间隔时间做一次备份，所以如果redis意外down掉的话，就会丢失最后一次快照后的所有修改

​    b.fork的时候，内存中的数据被克隆了一份，大致2倍的膨胀性需要考虑

 

8.如何停止

​    动态所有停止RDB保存规则的方法：redis-cli config set save ""



![](picc/rdb.jpg)





###  **AOF**



1.what

以**日志的形式**来**记录每个写操作**，将Redis执行过的所有写指令记录下来(读操作不记录)，

只许追加文件但不可以改写文件，redis启动之初会读取该文件重新构建数据，换言之，redis

重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作



2.Aof保存的是appendonly.aof文件

参考redis配置文件的APPEND ONLY MODE追加

 https://www.cnblogs.com/Mrchengs/p/10053560.html





3.AOF启动/修复/恢复

​    正常恢复：

​    a.启动：设置Yes -> 修改默认的appendonly no，改为yes

​    b.将有数据的aof文件复制一份保存到对应目录(config get dir)

​    c.恢复：重启redis然后重新加载

 

​    异常恢复：

​    a.启动：设置Yes --->修改默认的appendonly no，改为yes

​    b.备份被写坏的AOF文件

​    c.修复 --->redis-check-aof --fix进行修复

​    d.恢复：重启redis然后重新加载



4.rewrite

​    .aof文件越来越多.....

​    如何压缩精减



是什么：

AOF采用**文件追加方式**，文件会越来越大为避免出现此种情况，新增了**重写机制**

当AOF文件的大小超过所设定的阈值时，Redis就会启动AOF文件的内容压缩，

只保留可以恢复数据的最小指令集.可以使用命令**bgrewriteaof**



 重写原理：

AOF文件持续增长而过大时，会fork出一条新进程来将文件重写(也是先写临时文件最后再rename)，

遍历新进程的内存中数据，每条记录有一条的Set语句。重写aof文件的操作，并没有读取旧的aof文件，

是将整个内存中的数据库内容用命令的方式重写了一个新的aof文件，这点和快照有点类似



 触发机制

 Redis会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍

且文件大于64M时触发

```
# Specify a percentage of zero in order to disable the automatic AOF
# rewrite feature.

auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```



5.优势

a.每修改同步：appendfsync always   同步持久化 每次发生数据变更会被立即记录到磁盘 

性能较差但数据完整性比较好

b.每秒同步：appendfsync everysec    异步操作，每秒记录   如果一秒内宕机，有数据丢失

 c.不同步：appendfsync no   从不同步

 

6.劣势

​    a.相同数据集的数据而言aof文件要远大于rdb文件，恢复速度慢于rdb

​    b.aof运行效率要慢于rdb,每秒同步策略效率较好，不同步效率和rdb相同



7.小结

![](picc/aof.jpg)







