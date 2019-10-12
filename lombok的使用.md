## 1、介绍

```
Project Lombok makes java a spicier language by adding 'handlers' that know how to build and compile simple, boilerplate-free, not-quite-java code.
```

Lombok通过增加一些“处理程序”，可以让java变得简洁、快速

官网：https://projectlombok.org/features/all

## 2、 IDEA　中如何安装 Lombok

打开 IDEA 的 Settings 面板，并选择 Plugins 选项，然后点击 “Browse repositories”

在输入框输入”lombok”，得到搜索结果，点击安装，然后安装提示重启 IDEA，安装成功

## 3、使用方法

Lombok能以简单的注解形式来简化java代码，提高开发人员的开发效率。例如开发中经常需要写的javabean，都需要花时间去添加相应的getter/setter，也许还要去写构造器、equals等方法，而且需要维护，当属性多时会出现大量的getter/setter方法，这些显得很冗长也没有太多技术含量，一旦修改属性，就容易出现忘记修改对应方法的失误。

Lombok能通过注解的方式，在编译时自动为属性生成构造器、getter/setter、equals、hashcode、toString方法。出现的神奇就是在源码中没有getter和setter方法，但是在编译生成的字节码文件中有getter和setter方法。这样就省去了手动重建这些代码的麻烦，使代码看起来更简洁些。



Lombok的使用跟引用jar包一样，可以在官网（https://projectlombok.org/download）下载jar包，也可以使用maven添加依赖：

```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.20</version>
    <scope>provided</scope>
</dependency>
```



## 4、注解

- @Data
- @Setter
- @Getter
- @Log4j
- @AllArgsConstructor
- @NoArgsConstructor
- @EqualsAndHashCode
- @NonNull
- @Cleanup
- @ToString
- @RequiredArgsConstructor
- @Value
- @SneakyThrows
- @Synchronized



## 5、@Date

注解在 **属性** 上；为单个属性提供 set 方法; 

注解在 **类** 上，为该类所有的属性提供 set 方法， 都提供默认构造方法。



@Data注解在类上

会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法

**如为final属性，则不会为该属性生成setter方法。**



```
import lombok.Data;

@Data
public class Student {

    private String name;
    private  int age;

}
```

```
    @Test
    public  void test1(){
        Student stu = new Student();
        stu.setAge(10);
        stu.setName("ww");
        System.out.println(stu.toString());

    }
```



## 6、@Getter/@Setter

注解在 **属性** 上；为单个属性提供 set 方法; 注解在 **类** 上，为该类所有的属性提供 set 方法， 都提供默认构造方法。

注解在 **属性** 上；为单个属性提供 get 方法; 注解在 **类** 上，为该类所有的属性提供 get 方法，都提供默认构造方法。



@Data太过残暴（因为@Data集合了@ToString、@EqualsAndHashCode、@Getter/@Setter、@RequiredArgsConstructor的所有特性）不够精细

可以使用@Getter/@Setter注解，此注解在属性上，可以为相应的属性自动生成Getter/Setter方法

```
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
public class Student {

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private  int age;

}
```

```
    @Test
    public  void test1(){
        Student stu = new Student();
        stu.setName("ww");
        stu.setAge(21);
        System.out.println(stu.getAge() + stu.getName());

    }
```



## 7、@NonNull

该注解用在属性或构造器上，Lombok会生成一个非空的声明，可用于校验参数，能帮助避免空指针。

注解在 **属性** 上，会自动产生一个关于此参数的非空检查，如果参数为空，则抛出一个空指针异常，也会有一个默认的无参构造方法。

```
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

//@Data
public class Student {

    @NonNull
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private  int age;

    public void test(@NonNull Integer age){
        System.out.println("not null");
    }

}
```



## 8、@Cleanup

这个注解用在 **变量** 前面，可以保证此变量代表的资源会被自动关闭

默认是调用资源的 close() 方法

如果该资源有其它关闭方法可使用 @Cleanup(“methodName”) 来指定要调用的方法，也会生成默认的构造方法

该注解能帮助我们自动调用close()方法，很大的简化了代码。

```
  @Test
    public void teest2() throws IOException {
        @Cleanup InputStream in = new FileInputStream("");
        @Cleanup OutputStream out = new FileOutputStream("");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }
```



## 9、@EqualsAndHashCode

默认情况下，会使用所有非静态（non-static）和非瞬态（non-transient）属性来生成equals和hasCode，也能通过exclude注解来排除一些属性。



注解在 **类** 上, 可以生成 equals、canEqual、hashCode 方法。

```
import lombok.*;

@EqualsAndHashCode(exclude = {"name"})
public class Student {


    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private  int age;
}
```



## 10、@ToString

类使用@ToString注解，Lombok会生成一个toString()方法

默认情况下，会输出类名、所有属性（会按照属性定义顺序），用逗号来分割。

通过将`includeFieldNames`参数设为true，就能明确的输出toString()属性



这个注解用在 **类** 上，可以生成所有参数的 toString 方法，还会生成**默认的构造方法**。



```

@ToString(exclude="id")
public class ToStringExample {
  private static final int STATIC_VAR = 10;
  private String name;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;
  
  public String getName() {
    return this.getName();
  }
  
  @ToString(callSuper=true, includeFieldNames=true)
  public static class Square extends Shape {
    private final int width, height;
    
    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
  }
}
```



```
@ToString(exclude = {"name"})
public class Student {


    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private  int age;
    @Setter
    @Getter
    private  int sex;
}

```

```
    @Test
    public  void test1(){
        Student stu = new Student();
       stu.setAge(2);
        stu.setName("ss");
        stu.setSex(2);
        System.out.println(stu.toString());
    }
```

```
Student(age=2, sex=2)
```

## 11、**@NoArgsConstructor**

注解在 **类** 上；为类提供一个无参的构造方法。





## 12、**@RequiredArgsConstructor**

这个注解用在 **类** 上，使用类中所有带有 @NonNull 注解的或者带有 final 修饰的成员变量生成对应的构造方法。

部分参数构造器



## 13、**@AllArgsConstructor**

注解在 **类** 上；为类提供一个全参的构造方法，加了这个注解后，类中不提供默认构造方法了。



## 14、**@Value**

这个注解用在 **类** 上，会生成含所有参数的构造方法，get 方法，此外还提供了equals、hashCode、toString 方法。

**使用这个方法之后@Setter注解会失效**

```
@Value
public class Student {


    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private  int age;
    @Setter
    @Getter
    private  int sex;
}
```



## 15、**@SneakyThrows**

这个注解用在 **方法** 上，可以将方法中的代码用 try-catch 语句包裹起来，捕获异常并在 catch 中用 Lombok.sneakyThrow(e) 把异常抛出，可以使用 @SneakyThrows(Exception.class) 的形式指定抛出哪种异常，也会生成默认的构造方法。

```
   @SneakyThrows(IOException.class)
    public void test(){
        InputStream in = new FileInputStream("");
    }
```



## 16、**@Synchronized**

这个注解用在 **类方法** 或者 **实例方法** 上，效果和 synchronized 关键字相同



区别：在于锁对象不同，对于类方法和实例方法

1、synchronized 关键字的锁对象分别是类的 class 对象和 this 对象

2、@Synchronized 的锁对象分别是 私有静态 final 对象 lock 和 私有 final 对象 lock2

也可以自己指定锁对象，此外也提供默认的构造方法。



## 17、Lombok工作原理分析

JDK5引入了注解的同时，也提供了两种解析方式

**1-运行时注解**

运行时能够解析的注解，必须将@Retention设置为RUNTIME，这样就可以通过反射拿到该注解。java.lang,reflect反射包中提供了一个接口AnnotatedElement，该接口定义了获取注解信息的几个方法，Class、Constructor、Field、Method、Package等都实现了该接口，对反射熟悉的朋友应该都会很熟悉这种解析方式。



**2-编译时注解**

Annotation Processing Tool：

apt自JDK5产生，JDK7已标记为过期，不推荐使用，JDK8中已彻底删除，自JDK6开始，可以使用Pluggable Annotation Processing API来替换它，apt被替换主要有2点原因：

- api都在com.sun.mirror非标准包下
- 没有集成到javac中，需要额外运行



Pluggable Annotation Processing API：

[JSR 269](https://jcp.org/en/jsr/detail?id=269)自JDK6加入，作为apt的替代方案，它解决了apt的两个问题，javac在执行的时候会调用实现了该API的程序，这样我们就可以对编译器做一些增强，这时javac执行的过程如下：

![img](picc\picc1.png)



Lombok本质上就是一个实现了“[JSR 269 API](https://www.jcp.org/en/jsr/detail?id=269)”的程序。在使用javac的过程中，它产生作用的具体流程如下：

1. javac对源代码进行分析，生成了一棵抽象语法树（AST）
2. 运行过程中调用实现了“JSR 269 API”的Lombok程序
3. 此时Lombok就对第一步骤得到的AST进行处理，找到@Data注解所在类对应的语法树（AST），然后修改该语法树（AST），增加getter和setter方法定义的相应树节点
4. javac使用修改后的抽象语法树（AST）生成字节码文件，即给class增加新的节点（代码块）

