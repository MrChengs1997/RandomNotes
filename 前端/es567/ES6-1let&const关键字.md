####  6let

1. 作用:
  * 与var类似, 用于声明一个变量



2. 特点:
  * 在块作用域内有效

    ```
    <button>测试1</button>
    <br>
    <button>测试2</button>
    <br>
    <button>测试3</button>
    <br>
    <script type="text/javascript">
    let  btn = document.getElementsByTagName('button')
    for (let i = 0; i <btn.length; i++) {
      var b = btn[i]
      b.onclick = function () {
        alert(i)
      }
    }
    </script>
    ```

    此时不会进行直接打印i的总数3

    是会对进行排序后的按钮点击数件进行相应的打印

    

  * 不能重复声明

    ```
      let  names = 'q'
      let names = '2
    ```

  * 不会预处理, 不存在提升

    找var和function

    如：console.log(a);var a = 20;此时是可以进行打印的为udefind

    此时是可以进行预解析的

    但是let不会进行预解析



3. 应用:
  * 循环遍历加监听
  * 使用let取代var是趋势









#### const

1. 作用:
  * 定义一个常量
2. 特点:
  * 不能修改
  * 其它特点同let
3. 应用:

  * 保存不用改变的数据

```
    const sex = '男';
    console.log(sex);
    //sex = '女';//不能修改
    console.log(sex);
```

![](picc/const.png)





