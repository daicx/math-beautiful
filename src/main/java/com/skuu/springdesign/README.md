# Spring + 函数式设计模式 (springdesign)

基于 **Spring**（IoC、AOP、Events）与 **函数式编程**（Lambda、Function、Supplier、Optional、Stream）实现的 23 种设计模式，与 `design` 目录下的经典 Java 版本对应。

## 设计原则

- **Spring**：`@Component` / `@Service` / `@Configuration`、依赖注入、Spring Events、可选 AOP。
- **函数式**：接口用 `Function`/`Supplier`/`Consumer`/`Predicate`、Optional、Stream、方法引用、Lambda，减少继承与匿名类。

## 模式列表与实现要点

| 模式 | 包名 | Spring/函数式要点 |
|------|------|------------------|
| 单例 Singleton | singleton | Spring Bean 默认单例；或 `@Scope("singleton")` |
| 工厂+策略 Factory+Strategy | factory | 按支付方式名获取 `PaymentStrategy`（工厂），多实现可替换（策略）；`PaymentStrategyService.pay(method, request)`，支持 alipay/wechat/card |
| 原型 Prototype | prototype | `@Scope("prototype")` + `ObjectFactory<T>` 或 `Supplier<T>` |
| 适配器 Adapter | adapter | 目标接口 + `Function<Source, Target>` 或 `@Bean` 包装 |
| 桥接 Bridge | bridge | 抽象与实现分离，实现为 `@Component`，注入策略用 `Function` |
| 组合 Composite | composite | 树结构 + `Stream` 遍历，节点为 Bean 或工厂生成 |
| 装饰器 Decorator | decorator | `Function<T,T>.andThen(...)` 或 `@Primary` + 委托 Bean |
| 外观 Facade | facade | `@Service` 门面，注入多个子服务，对外单一接口 |
| 享元 Flyweight | flyweight | 池化用 `ConcurrentHashMap` + `computeIfAbsent`，工厂为 `@Component` |
| 代理 Proxy | proxy | Spring AOP `@Around` 或 函数式包装 `Function<T,R>` |
| 责任链 Chain | chain | `List<Predicate/Function>` 或 `Stream` 顺序执行，链由 `@Bean` 组装 |
| 命令 Command | command | `Map<String, Runnable>` 或 `Supplier/Consumer` 注册表 |
| 解释器 Interpreter | interpreter | 表达式树 + `Function<Context, T>` 求值 |
| 迭代器 Iterator | iterator | `Stream`、`Iterable`、`Spliterator`，数据源为 Bean |
| 中介者 Mediator | mediator | `@Component` 中介者，注入所有 Colleague，事件用 Spring Events |
| 备忘录 Memento | memento | 状态用不可变对象，`Supplier<Memento>` / `Function<Memento, Void>` |
| 观察者 Observer | observer | **Spring Events**：`ApplicationEventPublisher` + `@EventListener` |
| 状态 State | state | 状态枚举 + `Map<State, Map<Event, State>>`，侧效用 `Consumer` |
| 模板方法 Template | templatemethod | 订单履约固定流程「校验→步骤→收尾」，`OrderFulfillmentTemplate` + 按类型注入步骤列表（实物含 Ship，虚拟不含） |
| 访问者 Visitor | visitor | 双分派用 `Map<Class, Function>` 或 数据 + `Function<Element, R>` |
| 组合(业务) Combination | combination | 上述多种组合：Events + State + Strategy + Validator(Predicate) |

## 运行与测试

- 各包下有 `*Test` 或 `*Runner` 类，可运行 `main` 或通过 Spring Boot 启动后调用。
- 确保模块被 Spring 扫描：`@SpringBootApplication` 所在包或其父包包含 `com.skuu.springdesign`。
