### l链接

- 验证（Verify）

  - 目的在于确保Class文件的字节流中包含信息符合当前虚拟机的要求，保证被加载类的正确性，不会为危害虚拟机自身安全
  - 重要包含四种验证：文件格式验证，元数据验证，字节码验证，符号引用验证
  - 字节码文件的开头：**<u>CA FE BA BE</u>**

- 准备（Prepare）

  - 为类变量分配内存并且设置该变量的默认初始值，即**<u>零值</u>**

  - ```java
    public class test {
        //prepare阶段赋值为0
        //initial阶段赋值为2
        private static  int a = 2;
        public static void main(String[] args) {
            System.out.println(a);
        }
    }
    ```

  - 这里不包含final修饰的static，，因为final在编译时就会分配了，准备阶段会显示初始化

  - 这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量会随着对象一起分配到java堆中

- 解析（Resolve）

  - 将常量池的符号引用转换为直接引用过程

  - 解析操作往往会伴随着jvm在执行完初始化之后在执行

  - 解析动作主要针对类/接口/字段/类方法/接口方法/方法类型等对应常量池中的CONSTANT_CLASS_INFO、

    CONSTANT_FIRLDREF_info、CONSTANT_Mthodref_info等