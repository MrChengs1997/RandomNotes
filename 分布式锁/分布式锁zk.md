### 场景描述

在线程高并发场景下

生产有一定业务含义的惟一的订单编号

如：2019-01-01-12-32-12 年月日时分秒

![](picc/zk1.jpg)



小案例

生成订单的类

```
public class OrderFactory {

    private static  int i = 0;

    //synchronized
    public String createOrder(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date()) +( i++);
    }
}
```

线程类

```
public class OrderService implements Runnable {

    //100客户端
    private static  int count = 100;

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private OrderFactory  orderFactory = new OrderFactory();

    private static CountDownLatch countDownLatch = new CountDownLatch(count);

    private static  Object object = new Object();

    @Override
    public void run() {

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createOrder();
    }

    public  void createOrder(){
        String oder = orderFactory.createOrder();
        System.out.println(Thread.currentThread().getName() + "---" + oder);
        //logger.info(Thread.currentThread().getName() + "-" + oder);
    }

    public static void main(String[] args) {
        for (int i = 0;i<count;i++){
            new Thread(new OrderService()).start();
            countDownLatch.countDown();
        }
    }
}

```

此时会有数据订单重复问题

```
Thread-94---2019-10-28 20:33:120
Thread-16---2019-10-28 20:33:120
```

线程是抢占式的获取cpu资源

![](picc/zk2.jpg)



对订单类进行加锁

synchronized:用于费静态、静态方法、代码块上

### 锁的当前实例（this）

```
public class OrderFactory {

    private static  int i = 0;

    //synchronized
    public synchronized String createOrder(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date()) +( i++);
    }
}
```

此时不可以订单重复的问题！

此时每次都是不同的对象new OrderService()

```
    public static void main(String[] args) {
        for (int i = 0;i<count;i++){
            new Thread(new OrderService()).start();
            countDownLatch.countDown();
        }
    }
```

### 锁静态方法

锁的是对象Class

jvm通过双亲委派模型Class  独占一份（锁是一份对象）

```
public class OrderFactory {

    private static  int i = 0;

    //synchronized
    public synchronized static String createOrder(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date()) +( i++);
    }
}
```



![](picc/zk3.jpg)



### 锁代码块

```
public class OrderFactory {

    private static  int i = 0;

    //synchronized
    public   String createOrder(){
    synchronized(this){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date()) +( i++);
    }
   }
}
```

此时可以解决问题！

### 分布式环境下

由于不同的服务在不同的服务器上

此时使用synchronized无法解决以上遇到的问题



此时需要使用到zk进行作为分布式锁的控制（mysql&redis）

![](picc/zk4.jpg)









