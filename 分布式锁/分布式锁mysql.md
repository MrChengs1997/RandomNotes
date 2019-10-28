

## 回顾经典的卖票问题

```
    private int serialNumber = 100;
    //private static  final  Integer ID_NUM = 1;
    
    @Autowired
    MySqlLockService mySqlLockService;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    MySqlLockMapper mySqlLockMapper;

    @Test
    public  void mySqlLock() throws InterruptedException {
        //mySqlLockMapper.insertByLockId(ID_NUM);
        //mySqlLockMapper.deleteByLockId(ID_NUM);
        TicketRunable t = new TicketRunable();

        Thread thread1 = new Thread(t,"a");
        Thread thread2 = new Thread(t,"b");
        Thread thread3 = new Thread(t,"c");
        Thread thread4 = new Thread(t,"d");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(5000);

    }
    class TicketRunable implements  Runnable{
        @Override
        public void run() {
            while (serialNumber >0) {
                if (serialNumber > 0){
                    try {
                        Thread.currentThread();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"卖。。。"+serialNumber--);
                }
            }

        }
    }

```



结果

```
a卖。。。7
c卖。。。7
a卖。。。5
b卖。。。3
c卖。。。4
d卖。。。5
c卖。。。2
a卖。。。1
d卖。。。2
b卖。。。2
```





## Lock锁的处理



```
class TicketRunable implements  Runnable{

        Lock lock = new ReentrantLock();
        
        @Override
        public void run() {
            while (serialNumber >0) {
            
                lock.lock();
                
                if (serialNumber > 0){

                    try {
                        Thread.currentThread();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"卖。。。"+serialNumber--);
                }
                
                lock.unlock();
                
            }

        }
    }

```

但是这中处理方法在分布式环境中依然会存在问题

不同系统之间使用不同的服务器

此时控制的底层jvm也不是一套系统，由多个计算机组成的环境

所以此时的枷锁无效。







## MySQL锁

mybatis连接的MySQL的操作

```
@Mapper
public interface MySqlLockMapper {

    //public int

    public int deleteByLockId(Integer lock_id);

    public  int insertByLockId(Integer lock_id);
}
```

映射的xml文件

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mapper.MySqlLockMapper">

    <delete id="deleteByLockId" >
         delete from my_lock where lock_id = #{value};
    </delete>

    <insert id="insertByLockId" >
     		INSERT INTO `my_lock` VALUES (#{value});
    </insert>

</mapper>
```



service类

```
@Service
public class MySqlLockService implements Lock {

    private  final  Integer ID_NUM =1;

    @Autowired
    MySqlLockMapper mySqlLockMapper;

    //阻塞式加锁
    //不成功就一直等待资源的释放
    @Override
    public void lock() {
        //尝试加锁
        if (tryLock()){//加锁成功就退出
            return;
        }
        //加锁失败代表当前资源有其他线程使用
        //加锁失败当前任务休眠一段时间
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //递归调用，再次重新加锁
        lock();

    }

    //非阻塞加锁，向数据库写入id为1的数据，能下成功即加锁成功
    //成功就成功，不成功就撤退
    @Override
    public boolean tryLock() {
        try {
            mySqlLockMapper.insertByLockId(ID_NUM);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //解锁
    @Override
    public void unlock() {
        mySqlLockMapper.deleteByLockId(ID_NUM);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

```



进行相应的操作

```
 @Autowired
    MySqlLockService mySqlLockService;
    
    class TicketRunable implements  Runnable{
        Lock lock = new ReentrantLock();
        @Override
        public void run() {
            while (serialNumber >0) {

                mySqlLockService.lock();
                
                if (serialNumber > 0){

                    try {
                        Thread.currentThread();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"卖。。。"+serialNumber--);
                }
                
                mySqlLockService.unlock();
                
            }

        }
    }
```

此时可以通过MySql作为锁

对表进行锁表

在分布式环境下可以达到枷锁的效果

注意：对于锁表的字段要求是唯一键





并发能力300-700

如果产生断点等问题

会出现死锁