Byte : 字节. 数据存储的基本单位，比如移动硬盘1T ， 单位是byte

bit : 比特, 又叫位. 一个位要么是0要么是1. 数据传输的单位 , 比如家里的宽带100MB，下载速度并没有达到100MB，一般都是12-13MB，那么是因为需要使用 100 / 8

关系: 1Byte = 8bit





### 获取字符串byte

```java
  public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c=b;
            // 打印发现byte实际上就是ascii码
            System.out.println(c);
        }
    }
```

```
97
```





### byte对应bit

```java
 public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c=b;
            // 打印发现byte实际上就是ascii码
            System.out.println(c);
            // 我们在来看看每个byte对应的bit，byte获取对应的bit
            String s = Integer.toBinaryString(c);
            System.out.println(s);
        }
    }
```

```
97
1100001
```



### 中文对应的字节

```
// 中文在GBK编码下, 占据2个字节
// 中文在UTF-8编码下, 占据3个字节
```



```java
    public static void main(String[] args) throws Exception{

        String a = "尚";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            //byte
            System.out.print(b + "   ");
            //bit
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
```

```
-27   11111111111111111111111111100101
-80   11111111111111111111111110110000
-102   11111111111111111111111110011010
```



修改 编码格式 , 编码格式改成 GBK

```java
 public static void main(String[] args) throws Exception{

        String a = "尚";
        byte[] bytes = a.getBytes("GBK");
        for (byte b : bytes) {
            //byte
            System.out.print(b + "   ");
            //bit
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
```

```
-55   11111111111111111111111111001001
-48   11111111111111111111111111010000
```





