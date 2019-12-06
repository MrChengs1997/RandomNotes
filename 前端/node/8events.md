# events - 事件触发器

大多数 Node.js 核心 API 构建于惯用的异步事件驱动架构，其中某些类型的对象（又称触发器，Emitter）会触发命名事件来调用函数（又称监听器，Listener）。



所有能触发事件的对象都是 `EventEmitter` 类的实例。 这些对象有一个 `eventEmitter.on()` 函数，用于将一个或多个函数绑定到命名事件上。 事件的命名通常是驼峰式的字符串，但也可以使用任何有效的 JavaScript 属性键。。



当 `EventEmitter` 对象触发一个事件时，所有绑定在该事件上的函数都会被同步地调用。 被调用的监听器返回的任何值都将会被忽略并丢弃。



# 简单的 `EventEmitter` 实例

```
const EventEmitter = require('events');


class MyEmitter extends EventEmitter {}
// 创建 eventEmitter 对象
const myEmitter = new MyEmitter();
//注册监听
myEmitter.on('events', () => {
    console.log('触发事件');
    console.log("事件触发待处理的事件")
});
myEmitter.emit('events');
```

```
F:\node\node.exe F:\WebStorm-2019.3\node\event.js
触发事件
事件触发待处理的事件
```





# 将参数和 this 传给监听器

`eventEmitter.emit()` 方法可以传任意数量的参数到监听器函数。 当监听器函数被调用时， `this` 关键词会被指向监听器所绑定的 `EventEmitter` 实例。

```
const EventEmitter = require('events');


class MyEmitter extends EventEmitter {}

// 创建 eventEmitter 对象
const myEmitter = new MyEmitter();

//监听
myEmitter.on('event', function(a, b) {
    console.log(a, b, this, this === myEmitter);

});
myEmitter.emit('event', 'a', 'b');
```



```
F:\node\node.exe F:\WebStorm-2019.3\node\event.js
a b MyEmitter {
  _events: [Object: null prototype] { event: [Function] },
  _eventsCount: 1,
  _maxListeners: undefined
} true
```

# 异步 VS 同步

`EventEmitter` 以注册的顺序同步地调用所有监听器。 这样可以确保事件的正确排序，并有助于避免竞态条件和逻辑错误。

 当适当时，监听器函数可以使用 `setImmediate()` 和 `process.nextTick()` 方法切换到异步的操作模式：

```
const EventEmitter = require('events');


class MyEmitter extends EventEmitter {}

// 创建 eventEmitter 对象
const myEmitter = new MyEmitter();

//监听
myEmitter.on('event', (a, b) => {
    setImmediate(() => {
        console.log('异步地发生');
    });
});

myEmitter.emit('event', 'a', 'b');
console.log('end');
```

```
F:\node\node.exe F:\WebStorm-2019.3\node\event.js
end
异步地发生
```

# 仅处理事件一次

使用 `eventEmitter.once()` 可以注册最多可调用一次的监听器。 

当事件被触发时，监听器会被注销，然后再调用。

```
const EventEmitter = require('events');

class MyEmitter extends EventEmitter {}

// 创建 eventEmitter 对象
const myEmitter = new MyEmitter();

//监听
myEmitter.once("event",function () {
    console.log('触发事件');
});

myEmitter.emit('event');
myEmitter.emit('event');
```



```
F:\node\node.exe F:\WebStorm-2019.3\node\event.js
触发事件

Process finished with exit code 0

```



# 错误事件

当 `EventEmitter` 实例出错时，应该触发 `'error'` 事件。 这些在 Node.js 中被视为特殊情况

如果没有为 `'error'` 事件注册监听器，则当 `'error'` 事件触发时，会抛出错误、打印堆栈跟踪、并退出 Node.js 进程。

```
const EventEmitter = require('events');


class MyEmitter extends EventEmitter {}

// 创建 eventEmitter 对象
const myEmitter = new MyEmitter();

//监听
myEmitter.on('error', (err) => {
    console.log(err.toString())
    console.error('错误信息');
});
myEmitter.emit('error', new Error('错误'));
```

```

F:\node\node.exe F:\WebStorm-2019.3\node\event.js
Error: 错误
错误信息

```



# EventEmitter 类



'newListener' 事件
'removeListener' 事件
EventEmitter.listenerCount(emitter, eventName)
EventEmitter.defaultMaxListeners
emitter.addListener(eventName, listener)
emitter.emit(eventName[, ...args])
emitter.eventNames()
emitter.getMaxListeners()
emitter.listenerCount(eventName)
emitter.listeners(eventName)
emitter.off(eventName, listener)
emitter.on(eventName, listener)
emitter.once(eventName, listener)
emitter.prependListener(eventName, listener)
emitter.prependOnceListener(eventName, listener)
emitter.removeAllListeners([eventName])
emitter.removeListener(eventName, listener)
emitter.setMaxListeners(n)
emitter.rawListeners(eventName)

