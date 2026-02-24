# CGLIB 动态代理实现原理

## 工作原理示意图

```
客户端调用
    ↓
代理对象（Order的子类：Order$$EnhancerByCGLIB）
    ↓
MethodInterceptor.intercept()  ← 在这里增强（日志、性能监控等）
    ↓
MethodProxy.invokeSuper()      ← 调用父类（被代理类）的原始方法
    ↓
真实对象的方法执行
    ↓
返回结果给拦截器
    ↓
返回结果给代理对象
    ↓
返回结果给客户端
```

## 生成的代理类结构（伪代码）

```java
public class Order$$EnhancerByCGLIB extends Order {
    
    // 拦截器引用
    private MethodInterceptor CGLIB$CALLBACK_0;
    
    // 重写所有父类方法
    @Override
    public final void create(int var1) {
        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
        if (var10000 == null) {
            CGLIB$BIND_CALLBACKS(this);
            var10000 = this.CGLIB$CALLBACK_0;
        }
        
        if (var10000 != null) {
            // 调用拦截器
            var10000.intercept(this, CGLIB$create$0$Method, new Object[]{var1}, CGLIB$create$0$Proxy);
        } else {
            super.create(var1);
        }
    }
    
    @Override
    public final void pay(int var1) {
        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
        if (var10000 == null) {
            CGLIB$BIND_CALLBACKS(this);
            var10000 = this.CGLIB$CALLBACK_0;
        }
        
        if (var10000 != null) {
            var10000.intercept(this, CGLIB$pay$1$Method, new Object[]{var1}, CGLIB$pay$1$Proxy);
        } else {
            super.pay(var1);
        }
    }
}
```

## 详细流程

### 步骤1：创建代理类
```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(Order.class);      // 设置父类
enhancer.setCallback(interceptor);        // 设置拦截器
Order proxy = (Order) enhancer.create();  // 创建代理对象
```

执行流程：
1. Enhancer 分析 Order 类结构
2. 生成 `Order$$EnhancerByCGLIB` 字节码
3. 加载并实例化代理类
4. 返回代理对象

### 步骤2：方法调用
```java
proxy.create(1);
```

实际执行：
1. 进入 `Order$$EnhancerByCGLIB.create()`（重写方法）
2. 调用 `interceptor.intercept()`
3. 执行 `methodProxy.invokeSuper()` 调用父类方法
4. 返回结果

## invokeSuper 的重要作用

```java
// 项目代码注释（第32行）：
// 注意这里是调用 invokeSuper 而不是 invoke，否则死循环
// methodProxy.invokesuper执行的是原始类的方法，method.invoke执行的是子类的方法

Object result = methodProxy.invokeSuper(obj, args);
```

invokeSuper 的底层实现：
- 使用 FastClass 机制，绕过反射，直接调用
- 调用的是父类（被代理类）的方法，不是代理类的方法
- 避免了死循环

## JDK 动态代理 vs CGLIB

| 对比项 | JDK 动态代理 | CGLIB |
|--------|-------------|-------|
| 代理方式 | 实现接口 | 继承类 |
| 需要接口 | ✅ 必需 | ❌ 不需要 |
| 代理普通类 | ❌ 不能 | ✅ 可以 |
| final 方法 | ✅ 不受影响 | ❌ 不能被代理 |
| private 方法 | ✅ 不受影响 | ❌ 不能被代理 |
| 性能 | 反射调用稍慢 | FastClass 调用更快 |
| 字节码生成 | ❌ 不需要 | ✅ 运行时生成 |

## CGLIB 的优缺点

### 优点
1. **可以代理普通类**：不需要实现接口
2. **性能更好**：使用 FastClass 直接调用方法
3. **功能强大**：可以进行深层次的字节码操作

### 缺点
1. **不能代理 final 方法**：final 方法不能被重写
2. **不能代理 private 方法**：私有方法无法被子类访问
3. **依赖 CGLIB 库**：需要额外引入依赖
4. **生成的代理类较大**：包含所有方法的重写版本

## 常见应用场景

1. **Spring AOP**（默认使用 CGLIB）
   - 为没有接口的类添加切面
   - 事务管理、日志记录等

2. **Hibernate**（延迟加载）
   - 实体类的延迟加载代理

3. **Mockito**（测试框架）
   - 创建 Mock 对象

## 完整示例

### 目标类
```java
public class Order {
    public void create(int id) {
        System.out.println("create");
    }
}
```

### 拦截器
```java
public class LogInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
        before();
        Object result = proxy.invokeSuper(obj, args);  // 调用父类方法
        after();
        return result;
    }
}
```

### 使用
```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(Order.class);
enhancer.setCallback(new LogInterceptor());
Order order = (Order) enhancer.create();
order.create(1);  // 会被拦截器增强
```

## 技术细节

### FastClass 机制
CGLIB 使用 FastClass 来避免反射调用：

```java
// 传统反射调用（慢）
method.invoke(obj, args);

// FastClass 调用（快）
// CGLIB 会在运行时生成一个 FastClass 类
// 直接通过索引调用方法，避免了反射的开销
```

### 字节码增强
CGLIB 使用 ASM（字节码操作框架）在运行时：
- 分析类结构
- 生成子类字节码
- 重写需要拦截的方法
- 添加拦截器调用逻辑

## 调试技巧

### 导出生成的代理类
```java
// 在代码中添加
System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cglib_classes");
```

### 查看生成的类文件
生成的类文件会保存在指定目录，可以用 JD-GUI 或反编译工具查看。

## 总结

CGLIB 通过字节码生成和继承实现动态代理：
- **核心优势**：可以代理没有接口的类
- **实现方式**：生成子类，重写方法
- **关键技术**：FastClass 优化、MethodInterceptor 拦截
- **最佳实践**：`invokeSuper` 避免死循环

