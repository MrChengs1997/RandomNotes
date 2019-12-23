## **常用** 

1) Mint UI: 
	a. 主页: http://mint-ui.github.io/#!/zh-cn 
	b. 说明: 饿了么开源的基于 vue 的移动端 UI 组件库



2) Elment 
	a. 主页: http://element-cn.eleme.io/#/zh-CN 
	b. 说明: 饿了么开源的基于 vue 的 PC 端 UI 组件库





## **使用** **Mint UI**

下载

```
npm install --save mint-ui
```

实现按需打包

```
安装 babel-plugin-component：
npm install babel-plugin-component -D

修改 babel 配置
{
  "presets": [
    ["es2015", { "modules": false }]
  ],
  "plugins": [["component", [
    {
      "libraryName": "mint-ui",
      "style": true
    }
  ]]]
}
```

其余参考文档





















