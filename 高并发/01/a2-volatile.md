### JMM

JMM模型是抽象的概念，描述的是多线程与内存之间的通信

java线程内存模型与cpu缓存模型类似

他是标准化的

用于屏蔽掉各种操作系统的内存访问差异



![](F:/typora_file/高并发/01/picc/jmm.jpg)



由于cpu有不同的型号

由于java语言是跨平台的语言

怎么去通用底层的硬件结构

所以java有了JMM内存模型







### 八大原子操作

![](F:/typora_file/高并发/01/picc/八大原子操作.jpg)



### 小案例

```
package bf;


import org.apache.log4j.Logger;

public class VolatitleVisbilitySample extends  Thread {
    static Logger logger = Logger.getLogger(VolatitleVisbilitySample.class);

     private static  boolean initFlag = false;

     public static void  refresh(){
         logger.info("refresh data...");
         initFlag = true;
         logger.info("refresh data success");
     }

     public static  void  loadData(){
         while (!initFlag){

         }
         String current = Thread.currentThread().getName();
         System.out.println("线程" + current +"当前线程嗅探到initFlag的状态改变");
     }

    @Override
    public void run() {
        String current = Thread.currentThread().getName();
        if (current.equals("a")){
            loadData();
        }else {
            refresh();
        }
    }

    public static void main(String[] args) {
         //先执行loadData（）
        Thread threadA = new VolatitleVisbilitySample();
        threadA.setName("a");
        //在执行refresh（）
        Thread threadB = new VolatitleVisbilitySample();
        threadB.setName("b");

        threadA.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }
}
```

```
refresh data...
refresh data success
```

大致的流程图：

![](F:/typora_file/高并发/01/picc/线程AB.jpg)





线程间通信

线程的工作内存相互不可见

xianchengA感知不到线程B对变量的修改





### 保证线程间的通信

volatile：可以保证内存中的数据被修改可以被其他的线程感知到



```
 private static volatile   boolean initFlag = false;
```



```
refresh data...
refresh data success
线程a当前线程嗅探到initFlag的状态改变
```





### JMM的三大特性

可见性

原子性

有序性





### volatitle可见性原理

![](picc/volatile.jpg)





![](picc/实现.jpg)



