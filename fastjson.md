### 相关介绍

具体信息见github项目主页

https://github.com/alibaba/fastjson



### 使用

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.61</version>
</dependency>
```

### Map 转 JSON 字符串

**将Map转Json字符串**

```
    @Test
    public  void MapToJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");

        String s = JSON.toJSONString(map);
        System.out.println(s);
    }
```

```
{"a":"1","b":"2","c":"3"}
```



### POJO List 转 JSON 字符串

 **将Java Bean List转Json字符串**

```
 @Test
    public  void PojoToJson(){
        Student s = new Student();
        s.setAge(10);
        s.setName("mr");

        Student s1 = new Student();
        s1.setAge(32);
        s1.setName("mr1");

        List<Student> stu = new ArrayList<Student>();
        stu.add(s);
        stu.add(s1);

        String object = JSON.toJSONString(stu);

    }


    class  Student{
        private String name;
        private int age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
```

```
[{"age":10,"name":"mr"},{"age":32,"name":"mr1"}]
```



### Json 字符串转 JsonObject

**将Json字符串转为JsonObject对象**

**取值不存在时，返回null,使用Gson会抛异常**

```
 @Test
    public  void JsonToObject(){

        String json = "{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\"}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.getString("a"));
        System.out.println(jsonObject.getString("b"));
        System.out.println(jsonObject.getString("c"));

    }

```

```
1
2
3
```



### JsonObject 转 Json 字符串

将JsonObject对象转为Json字符串

取值不存在时，返回null,使用Gson会抛异常

```
 @Test
    public  void JsonObjectToJson(){

        String json = "{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\"}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.getString("a"));
        System.out.println(jsonObject.getString("b"));
        System.out.println(jsonObject.getString("c"));
        System.out.println("-----华丽分割线-----");

        String toJson = JSONObject.toJSONString(jsonObject);
        System.out.println(toJson);
    }
```

```
1
2
3
-----华丽分割线-----
{"a":"1","b":"2","c":"3"}
```



### JSONObject添加 JSONArray 

将JsonObject添加到JsonArray

```
   @Test
    public  void JsonObjectToJsonArray(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "mr");
        jsonObject1.put("age", 22);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "mr1");
        jsonObject2.put("age", 18);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);

        String jsonArrStr = JSONArray.toJSONString(jsonArray);
        System.out.println(jsonArrStr);


    }
```

```
[{"name":"mr","age":22},{"name":"mr1","age":18}]
```



### Json 数组字符串转 JsonArray

```
 public  void JsonToJsonArray(){
        String json =
                "[{\"name\":\"mr\",\"age\":22},{\"name\":\"mr1\",\"age\":18}]";

        JSONArray jsonArray = JSONArray.parseArray(json);
        for (Object o : jsonArray){
            JSONObject jsonObject = (JSONObject) o;
            System.out.println(jsonObject.getString("name"));
            System.out.println(jsonObject.getInteger("age"));
        }
    }
```

```
mr
22
mr1
18
```



### POJO 转 Json 字符串

```
 @Test
    public  void PojoToJson(){
        Student s = new Student();
        s.setAge(10);
        s.setName("mr");

//两种方式
        String o1 = JSONObject.toJSONString(s);
        String o2 = JSON.toJSONString(s);
        System.out.println(o1);
        System.out.println(o2);

    }
```

```
{"age":10,"name":"mr"}
{"age":10,"name":"mr"}
```



### POJO 转 JsonObject

将Java Bean 转Json 对象

```
@Test
    public  void PojoToJsonObject(){
        Student s = new Student();
        s.setAge(10);
        s.setName("mr");
        /**方式一*/
        String jsonStr = JSONObject.toJSONString(s);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.get("name"));

        /**方式二*/
        JSONObject jsonObject1 = (JSONObject)JSONObject.toJSON(s);
        System.out.println(jsonObject1.get("age"));
    }
```

```
mr
10
```



### POJO List 转 JsonArray

将Java Bean List 转Json 数组

```
   @Test
    public  void PojoListToJsonArray(){
        Student s = new Student();
        s.setAge(10);
        s.setName("mr");

        Student s1 = new Student();
        s1.setAge(32);
        s1.setName("mr1");

        List<Student> stu = new ArrayList<Student>();
        stu.add(s);
        stu.add(s1);

        /**方式1*/
        String jsonArrStr = JSONArray.toJSONString(stu);
        JSONArray jsonArray = JSONArray.parseArray(jsonArrStr);
        JSONObject jsonObject1 = (JSONObject)jsonArray.get(0);
        System.out.println(jsonObject1.get("name"));

        /**方式2*/
        JSONArray jsonArray1 = (JSONArray)JSONArray.toJSON(stu);
        JSONObject jsonObject2 = (JSONObject)jsonArray1.get(1);
        System.out.println(jsonObject2.get("name"));

    }
```

```
mr
mr1
```

### JsonObject 转 POJO

将Json 对象 转 Java Bean

```
    @Test
    public  void  JsonObjToPojo(){
        Student s = new Student();
        s.setAge(10);
        s.setName("mr");

        String jsonPOJOStu = JSON.toJSONString(s);
        Student person = JSONObject.parseObject(jsonPOJOStu, Student.class);
        System.out.println(person.toString());
    }
```

```
fastjson.Student@64d7f7e0
```


### JsonArray 转 POJO List

将Json 数组 转 Java List

```
 @Test
    public  void  JsonArrToPojoList(){
        String  json = "[{\"name\":\"mr\",\"age\":22},{\"name\":\"mr1\",\"age\":18}]";

        List<Student> StudentList = JSONArray.parseArray(json, Student.class);
        for (Student student:StudentList){
            System.out.println(student.getAge());
            System.out.println(student.getName());
            System.out.println("----huali----");
        }

    }
```

```
22
mr
----huali----
18
mr1
----huali----
```















