## **理解**

说明

1) 官方提供的用来实现 SPA 的 vue 插件 
2) github: https://github.com/vuejs/vue-router 
3) 中文文档: http://router.vuejs.org/zh-cn/ 
4) 下载: npm install vue-router --save



相关 API 说明

1) **VueRouter**(): 用于创建路由器的构建函数 
new VueRouter({ 
	// 多个配置项 
})



2) 路由配置 
**routes**: [ 
	{ 
		// 一般路由 
		path: '/about', 
		component: About 
	},
	{ 
		// 自动跳转路由 
		path: '/', 
		redirect: '/about' 
	} 
]



3) 注册路由器 
import router from './router' 
new Vue({ 
	router 
})



4) 使用路由组件标签 
1. <router-link>: 用来生成路由链接 
	<router-link to="/xxx">Go to XXX</router-link> 

2. <router-view>: 用来显示当前路由组件界面 
	<router-view></router-view>





## 基本路由

见**d5_基本路由**





## 嵌套路由

见**d6_嵌套路由**

