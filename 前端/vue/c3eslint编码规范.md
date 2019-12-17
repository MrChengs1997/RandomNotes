**说明**

1) ESLint 是一个代码规范检查工具 
2) 它定义了很多特定的规则, 一旦你的代码违背了某一规则 eslint会作出非常有用的提示 
3) 官网: http://eslint.org/ 
4) 基本已替代以前的 JSLint



**ESLint** **提供以下支持**

1) ES 

2) JSX 

3) style 检查 

4) 自定义错误和提示



**ESLint** **提供以下几种校验**

1) 语法错误校验 

2) 不重要或丢失的标点符号，如分号 

3) 没法运行到的代码块（使用过 WebStorm 的童鞋应该了解） 

4) 未被使用的参数提醒 

5) 确保样式的统一规则，如 sass 或者 less 

6) 检查变量的命名



**规则的错误等级有三种** 

1) 0：关闭规则。 

2) 1：打开规则，并且作为一个警告（信息打印黄色字体） 

3) 2：打开规则，并且作为一个错误（信息打印红色字体）



**相关配置文件**

1) .eslintrc.js : 全局规则配置文件 
	'rules': { 
		'no-new': 1 
	}



2) 在 js/vue 文件中修改局部规则 
/* eslint-disable no-new */ 

new Vue({ 
	el: 'body', 
	components: {
		App 
	} 
})



3) .eslintignore: 指令检查忽略的文件 
	*.js 
	*.vue



若要让某个规则失效

修改：.eslintric.js文件



