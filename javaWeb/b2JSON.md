## **什么是**

**JSON** (JavaScript Object Notation) 是一种轻量级的数据交换格式。易于人阅读和编写。同时也易于机器解析和生成。JSON 采用完全独立于语言的文本格式，而且很多语言都提供了对 json 的支持（包括 C, C++, C#, Java, JavaScript, Perl, Python 等）。 这样就使得 JSON 成为理想的数据交换格式。 



json 是一种轻量级的数据交换格式。 

轻量级指的是跟 xml 做比较。 

数据交换指的是客户端和服务器之间业务数据的传递格式。





## **json** **的定义**

json 是由键值对组成，并且由花括号（大括号）包围。每个键由引号引起来，键和值之间使用冒号进行分隔， 

多组键值对之间进行逗号进行分隔。 

```json
  var jsonObj = {
            "key1":12,
            "key2":"abc",
            "key3":true,
            "key4":[11,"arr",false],
            "key5":{ "key5_1" : 551, "key5_2" : "key5_2_value" },
            "key6":[{
                "key6_1_1":6611, "key6_1_2":"key6_1_2_value"},
                {"key6_2_1":6621, "key6_2_2":"key6_2_2_value" }]
        };
```







## **json** **的访问** 

json 本身是一个对象。 

json 中的 key 我们可以理解为是对象中的一个属性。 

json 中的 key 访问就跟访问对象的属性一样： json 对象.key

```html
  var jsonObj = {
            "key1": 12,
            "key2": "abc",
            "key3": true,
            "key4": [11, "arr", false],
            "key5": {"key5_1": 551, "key5_2": "key5_2_value"},
            "key6": [{
                "key6_1_1": 6611, "key6_1_2": "key6_1_2_value"
            },
                {"key6_2_1": 6621, "key6_2_2": "key6_2_2_value"}]
        };

        alert(typeof (jsonObj));
        // object json 就是一个对象
        //
        alert(jsonObj.key1); //12
        alert(jsonObj.key2); // abc
        alert(jsonObj.key3); // true
        alert(jsonObj.key4);// 得到数组[11,"arr",false]
        // json 中 数组值的遍历
        for (var i = 0; i < jsonObj.key4.length; i++) {
            alert(jsonObj.key4[i]);
        }
        alert(jsonObj.key5.key5_1);//551
        alert(jsonObj.key5.key5_2);//key5_2_value
        alert(jsonObj.key6);// 得到 json 数组
        // 取出来每一个元素都是 json 对象 
        var jsonItem = jsonObj.key6[0];
        alert(jsonItem.key6_1_1); //6611
        alert(jsonItem.key6_1_2); //key6_1_2_value
```



## **两个常用方法**

json 的存在有两种形式。 

一种是：对象的形式存在，我们叫它 json 对象。 

一种是：字符串的形式存在，我们叫它 json 字符串。 

一般我们要操作 json 中的数据的时候，需要 json 对象的格式。 

一般我们要在客户端和服务器之间进行数据交换的时候，使用 json 字符串。 

JSON.stringify()    把 json 对象转换成为 json 字符串 

JSON.parse()     把 json 字符串转换成为 json 对象



## **JSON** **在** **java** **中的使用**

gson-2.2.4.jar



**javaBean** **和** **json** **的互转**

```java
  Students students= new Students();
        students.setAge("2");
        students.setName("mr");

        //Gson
        Gson gson = new Gson();
        // toJson 方法可以把 java 对象转换成为 json 字符串
        String personJsonString = gson.toJson(students);
        System.out.println(personJsonString);


        // fromJson 把 json 字符串转换回 Java 对象
        // 第一个参数是 json 字符串
        // 第二个参数是转换回去的 Java 对象类型
        Students students1 = gson.fromJson(personJsonString, Students.class);
        System.out.println(students1);
```



```
{"name":"mr","age":"2"}
com.mrchengs.bean.Students@7aec35a
```



**List** **和** **json** **的互转**

```java
      Students students= new Students();
        students.setAge("2");
        students.setName("mr");

        Students students1= new Students();
        students.setAge("21");
        students.setName("mr11");

        List<Students> stuList = new ArrayList<>();
        stuList.add(students);
        stuList.add(students1);

        //Gson
        Gson gson = new Gson();
        // 把 List 转换为 json 字符串
        String personListJsonString = gson.toJson(stuList);
        System.out.println(personListJsonString);


        //可以转为list但是使用泛型会有问题
        List<Students> list = gson.fromJson(personListJsonString,new StudentType().getType());
        System.out.println(list);
        Students person = list.get(0);
        System.out.println(person);

```



```java
public class StudentType extends TypeToken<List<Students>> {
}

```

```
[{"name":"mr11","age":"21"},{}]
[com.mrchengs.bean.Students@1de0aca6, com.mrchengs.bean.Students@255316f2]
com.mrchengs.bean.Students@1de0aca6
```



**map** **和** **json** **的互转** 

```java
   Students students= new Students();
        students.setAge("2");
        students.setName("mr");

        Students students1= new Students();
        students.setAge("21");
        students.setName("mr11");

        Map<Integer,Students> map = new HashMap<>();

        map.put(1,students);
        map.put(2,students1);

        Gson gson = new Gson();
        // 把 map 集合转换成为 json 字符串
        String personMapJsonString = gson.toJson(map);
        System.out.println(personMapJsonString);

        Map<Integer,Students> personMap2 = gson.fromJson(personMapJsonString, new TypeToken<HashMap<Integer,Students>>(){}.getType());
        System.out.println(personMap2);
        Students p = personMap2.get(1);
        System.out.println(p);
```

{"1":{"name":"mr11","age":"21"},"2":{}}
{1=com.mrchengs.bean.Students@531d72ca, 2=com.mrchengs.bean.Students@22d8cfe0}
com.mrchengs.bean.Students@531d72ca