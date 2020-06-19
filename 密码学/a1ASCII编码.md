

```java
 public static void main(String[] args) {
            char a = 'A';
            int b = a;
            // 打印ascii码
            System.out.println(b);
    }
```

```
65
```





```java
 String a = "AaZ";
            // 获取ascii码，需要把字符串转成字符
            char[] chars = a.toCharArray();
            for (char c : chars) {

                int asciiCode = c;
                System.out.println(asciiCode);
            }
```

```
65
97
90
```

















