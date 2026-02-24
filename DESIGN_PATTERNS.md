# 🎨 Java设计模式完整实现

这个项目包含了GoF（Gang of Four）经典的23种设计模式的完整Java实现，每个模式都有详细的注释、实际案例和测试代码。

## 📚 目录

- [创建型模式 (5种)](#创建型模式)
- [结构型模式 (7种)](#结构型模式)
- [行为型模式 (11种)](#行为型模式)
- [运行示例](#运行示例)
- [设计原则](#设计原则)

---

## 创建型模式

创建型模式关注对象的创建过程，将对象的创建与使用分离。

### 1. 单例模式 (Singleton Pattern)

**📁 位置**: `src/main/java/com/skuu/design/singleton/`

**📖 定义**: 确保一个类只有一个实例，并提供全局访问点。

**🎯 使用场景**:
- 配置管理器
- 日志管理器
- 数据库连接池
- 线程池

**💡 实现方式**:
- 懒汉式（线程不安全）
- 懒汉式（线程安全，synchronized）
- 双重检查锁定
- 饿汉式
- 静态内部类
- 枚举（推荐）

**▶️ 运行**: `java com.skuu.design.singleton.Test`

---

### 2. 工厂方法模式 (Factory Method Pattern)

**📁 位置**: `src/main/java/com/skuu/design/factory/jdgc/`

**📖 定义**: 定义一个创建对象的接口，让子类决定实例化哪个类。

**🎯 使用场景**:
- JDBC数据库连接
- 日志记录器
- 不同类型的文档创建

**💡 实现要点**:
- 简单工厂：一个工厂类负责创建
- 工厂方法：每个产品有对应的工厂

**▶️ 运行**: `java com.skuu.design.factory.jdgc.Test`

---

### 3. 抽象工厂模式 (Abstract Factory Pattern)

**📁 位置**: `src/main/java/com/skuu/design/factory/gcff/` 和 `factory/cxgc/`

**📖 定义**: 提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。

**🎯 使用场景**:
- 跨平台UI组件
- 不同数据库的访问
- 不同操作系统的产品族

**💡 实现要点**:
- 产品族的概念
- 工厂接口定义创建方法
- 具体工厂创建具体产品

**▶️ 运行**: 
- `java com.skuu.design.factory.gcff.Test`
- `java com.skuu.design.factory.cxgc.Test`

---

### 4. 建造者模式 (Builder Pattern)

**📁 位置**: `src/main/java/com/skuu/design/builder/`

**📖 定义**: 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

**🎯 使用场景**:
- 复杂对象的创建（多个参数）
- StringBuilder
- Lombok的@Builder
- HTTP请求构建

**💡 实现要点**:
- 链式调用
- 可选参数
- 不可变对象

**▶️ 运行**: `java com.skuu.design.builder.Test`

---

### 5. 原型模式 (Prototype Pattern)

**📁 位置**: `src/main/java/com/skuu/design/prototype/`

**📖 定义**: 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。

**🎯 使用场景**:
- 对象创建成本高
- 需要大量相似对象
- 避免构造函数

**💡 实现要点**:
- 实现Cloneable接口
- 重写clone()方法
- 浅拷贝 vs 深拷贝

**▶️ 运行**: `java com.skuu.design.prototype.Test`

---

## 结构型模式

结构型模式关注类和对象的组合，通过继承和组合来获得更强大的功能。

### 6. 适配器模式 (Adapter Pattern)

**📁 位置**: `src/main/java/com/skuu/design/adapter/`

**📖 定义**: 将一个类的接口转换成客户希望的另一个接口。

**🎯 使用场景**:
- 接口不兼容的类之间的适配
- 第三方库的接口适配
- 旧系统接口适配

**💡 实现方式**:
- 类适配器（继承）
- 对象适配器（组合）★推荐
- 接口适配器

**▶️ 运行**: `java com.skuu.design.adapter.Test`

---

### 7. 桥接模式 (Bridge Pattern)

**📁 位置**: `src/main/java/com/skuu/design/bridge/`

**📖 定义**: 将抽象部分与实现部分分离，使它们都可以独立地变化。

**🎯 使用场景**:
- 跨平台图形系统
- 不同数据库的访问
- 不同消息队列的实现

**💡 实现要点**:
- 抽象部分持有实现部分的引用
- 两个维度独立变化
- 避免继承爆炸

**▶️ 运行**: `java com.skuu.design.bridge.Test`

---

### 8. 组合模式 (Composite Pattern)

**📁 位置**: `src/main/java/com/skuu/design/composite/`

**📖 定义**: 将对象组合成树形结构以表示"部分-整体"的层次结构。

**🎯 使用场景**:
- 文件系统（文件和文件夹）
- 组织架构树
- UI组件树
- 菜单结构

**💡 实现要点**:
- 统一的组件接口
- 叶子节点和容器节点
- 递归结构

**▶️ 运行**: `java com.skuu.design.composite.Test`

---

### 9. 装饰模式 (Decorator Pattern)

**📁 位置**: `src/main/java/com/skuu/design/decorator/`

**📖 定义**: 动态地给一个对象添加一些额外的职责。

**🎯 使用场景**:
- Java I/O流（InputStream, OutputStream）
- Servlet API（Wrapper）
- 咖啡店点单系统

**💡 实现要点**:
- 装饰器持有被装饰对象
- 装饰器实现相同接口
- 可以多层装饰

**▶️ 运行**: `java com.skuu.design.decorator.Test`

---

### 10. 外观模式 (Facade Pattern)

**📁 位置**: `src/main/java/com/skuu/design/facade/`

**📖 定义**: 为子系统中的一组接口提供一个一致的界面。

**🎯 使用场景**:
- 复杂系统的简化接口
- 分层架构的各层入口
- 第三方库的封装
- 微服务网关

**💡 实现要点**:
- 封装多个子系统
- 提供简化的统一接口
- 最少知识原则

**▶️ 运行**: `java com.skuu.design.facade.Test`

---

### 11. 享元模式 (Flyweight Pattern)

**📁 位置**: `src/main/java/com/skuu/design/flyweight/`

**📖 定义**: 运用共享技术有效地支持大量细粒度的对象。

**🎯 使用场景**:
- 文本编辑器的字符对象
- 游戏中的大量粒子、子弹
- String常量池
- Integer缓存（-128到127）

**💡 实现要点**:
- 内部状态（可共享）
- 外部状态（不可共享）
- 享元工厂管理对象池

**▶️ 运行**: `java com.skuu.design.flyweight.Test`

---

### 12. 代理模式 (Proxy Pattern)

**📁 位置**: `src/main/java/com/skuu/design/proxy2/`

**📖 定义**: 为其他对象提供一种代理以控制对这个对象的访问。

**🎯 使用场景**:
- 延迟加载（虚拟代理）
- 权限控制（保护代理）
- 远程调用（远程代理）
- Spring AOP

**💡 代理类型**:
- 静态代理
- JDK动态代理
- CGLIB代理

**▶️ 运行**: `java com.skuu.design.proxy2.Test`

---

## 行为型模式

行为型模式关注对象之间的通信和职责分配。

### 13. 责任链模式 (Chain of Responsibility Pattern)

**📁 位置**: `src/main/java/com/skuu/design/chain/`

**📖 定义**: 为请求创建一个接收者对象的链，沿着链传递请求直到有对象处理它。

**🎯 使用场景**:
- 请假审批流程
- 异常处理链
- Servlet过滤器链
- Spring Security过滤器

**💡 实现要点**:
- 每个处理器持有下一个处理器引用
- 链式调用
- 可以动态组合

**▶️ 运行**: `java com.skuu.design.chain.Test`

---

### 14. 命令模式 (Command Pattern)

**📁 位置**: `src/main/java/com/skuu/design/command/`

**📖 定义**: 将一个请求封装为一个对象，从而可以用不同的请求对客户进行参数化。

**🎯 使用场景**:
- GUI按钮和菜单
- 撤销/重做功能
- 事务系统
- 线程池（Runnable）

**💡 实现要点**:
- 命令对象持有接收者引用
- 支持撤销操作
- 支持宏命令

**▶️ 运行**: `java com.skuu.design.command.Test`

---

### 15. 解释器模式 (Interpreter Pattern)

**📁 位置**: `src/main/java/com/skuu/design/interpreter/`

**📖 定义**: 给定一个语言，定义它的文法的一种表示，并定义一个解释器。

**🎯 使用场景**:
- 数学表达式计算
- SQL解析
- 正则表达式
- 规则引擎

**💡 实现要点**:
- 终结符表达式
- 非终结符表达式
- 抽象语法树
- 上下文对象

**▶️ 运行**: `java com.skuu.design.interpreter.Test`

---

### 16. 迭代器模式 (Iterator Pattern)

**📁 位置**: `src/main/java/com/skuu/design/iterator/`

**📖 定义**: 提供一种方法顺序访问聚合对象中的各个元素，而又不暴露其内部表示。

**🎯 使用场景**:
- Java集合框架（Iterator）
- 数据库结果集（ResultSet）
- 文件系统遍历
- 树形结构遍历

**💡 实现要点**:
- 统一的遍历接口
- 隐藏内部结构
- 支持多种遍历方式

**▶️ 运行**: `java com.skuu.design.iterator.Test`

---

### 17. 中介者模式 (Mediator Pattern)

**📁 位置**: `src/main/java/com/skuu/design/mediator/`

**📖 定义**: 用一个中介对象来封装一系列的对象交互。

**🎯 使用场景**:
- 聊天室
- MVC架构的Controller
- 机场调度系统
- GUI对话框组件

**💡 实现要点**:
- 中介者协调所有同事对象
- 同事对象只知道中介者
- 降低对象间耦合

**▶️ 运行**: `java com.skuu.design.mediator.Test`

---

### 18. 备忘录模式 (Memento Pattern)

**📁 位置**: `src/main/java/com/skuu/design/memento/`

**📖 定义**: 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。

**🎯 使用场景**:
- 撤销/重做功能
- 事务回滚
- 游戏存档
- 浏览器历史记录

**💡 实现要点**:
- 备忘录不可变（final）
- 窄接口和宽接口
- 保持封装性

**▶️ 运行**: `java com.skuu.design.memento.Test`

---

### 19. 观察者模式 (Observer Pattern)

**📁 位置**: `src/main/java/com/skuu/design/observer/`

**📖 定义**: 定义对象间的一对多依赖关系，当一个对象状态改变时，所有依赖它的对象都会收到通知。

**🎯 使用场景**:
- 事件监听系统
- MVC架构（Model通知View）
- 发布-订阅系统
- 响应式编程（RxJava）

**💡 实现要点**:
- 主题维护观察者列表
- 观察者实现更新接口
- 松耦合设计

**▶️ 运行**: `java com.skuu.design.observer.Test`

---

### 20. 状态模式 (State Pattern)

**📁 位置**: `src/main/java/com/skuu/design/status/`

**📖 定义**: 允许对象在内部状态改变时改变它的行为。

**🎯 使用场景**:
- 订单状态流转
- TCP连接状态
- 游戏角色状态
- 工作流引擎

**💡 实现要点**:
- 状态接口定义行为
- 具体状态类实现不同行为
- 上下文持有当前状态

**▶️ 运行**: `java com.skuu.design.status.OrderStatePatternTest`

---

### 21. 策略模式 (Strategy Pattern)

**📁 位置**: `src/main/java/com/skuu/design/strategy/`

**📖 定义**: 定义一系列算法，把它们一个个封装起来，并且使它们可以相互替换。

**🎯 使用场景**:
- 支付方式选择
- 排序算法选择
- 压缩算法选择
- 游戏角色技能

**💡 实现要点**:
- 策略接口定义算法
- 具体策略实现不同算法
- 上下文持有策略引用

**▶️ 运行**: 
- `java com.skuu.design.strategy.interfaces.Test`
- `java com.skuu.design.strategy.extend.Test`

---

### 22. 模板方法模式 (Template Method Pattern)

**📁 位置**: `src/main/java/com/skuu/design/templatemethod/`

**📖 定义**: 在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。

**🎯 使用场景**:
- Servlet生命周期方法
- Spring的JdbcTemplate
- 框架的生命周期钩子
- 通用的业务流程

**💡 实现要点**:
- 模板方法定义算法骨架
- 抽象方法由子类实现
- 钩子方法提供可选扩展

**▶️ 运行**: `java com.skuu.design.templatemethod.Test`

---

### 23. 访问者模式 (Visitor Pattern)

**📁 位置**: `src/main/java/com/skuu/design/visitor/`

**📖 定义**: 表示一个作用于某对象结构中的各元素的操作，可以在不改变各元素类的前提下定义新操作。

**🎯 使用场景**:
- 编译器的AST遍历
- XML/DOM树操作
- 报表生成
- 文件系统遍历

**💡 实现要点**:
- 双分派机制
- 访问者为每种元素定义visit()方法
- 元素实现accept()方法

**▶️ 运行**: `java com.skuu.design.visitor.Test`

---

## 运行示例

### 编译项目
```bash
mvn clean compile
```

### 运行单个测试
```bash
# 单例模式
java -cp target/classes com.skuu.design.singleton.Test

# 工厂模式
java -cp target/classes com.skuu.design.factory.jdgc.Test

# 责任链模式
java -cp target/classes com.skuu.design.chain.Test

# 观察者模式
java -cp target/classes com.skuu.design.observer.Test

# ... 其他模式类似
```

### 批量运行所有测试
```bash
# 创建脚本运行所有测试
./run_all_patterns.sh
```

---

## 设计原则

### SOLID原则

1. **单一职责原则 (SRP)**
   - 一个类只负责一个功能领域
   - 示例：策略模式、装饰模式

2. **开闭原则 (OCP)**
   - 对扩展开放，对修改关闭
   - 示例：装饰模式、访问者模式、策略模式

3. **里氏替换原则 (LSP)**
   - 子类可以替换父类
   - 示例：所有使用继承的模式

4. **接口隔离原则 (ISP)**
   - 客户端不应该依赖它不需要的接口
   - 示例：适配器模式

5. **依赖倒置原则 (DIP)**
   - 依赖抽象而不依赖具体
   - 示例：工厂模式、依赖注入

### 其他原则

6. **迪米特法则 (LoD)**
   - 最少知识原则
   - 示例：外观模式、中介者模式

7. **合成复用原则**
   - 优先使用组合而不是继承
   - 示例：装饰模式、策略模式、桥接模式

---

## 设计模式分类总结

### 按目的分类

| 类型 | 数量 | 模式列表 |
|------|------|----------|
| **创建型** | 5 | 单例、工厂方法、抽象工厂、建造者、原型 |
| **结构型** | 7 | 适配器、桥接、组合、装饰、外观、享元、代理 |
| **行为型** | 11 | 责任链、命令、解释器、迭代器、中介者、备忘录、观察者、状态、策略、模板方法、访问者 |

### 常用程度排名

#### ⭐⭐⭐⭐⭐ 最常用（必须掌握）
- 单例模式
- 工厂模式
- 策略模式
- 观察者模式
- 装饰模式
- 代理模式

#### ⭐⭐⭐⭐ 常用（建议掌握）
- 建造者模式
- 适配器模式
- 模板方法模式
- 责任链模式
- 状态模式

#### ⭐⭐⭐ 一般（了解即可）
- 外观模式
- 组合模式
- 命令模式
- 迭代器模式
- 桥接模式

#### ⭐⭐ 较少使用
- 中介者模式
- 备忘录模式
- 原型模式
- 享元模式

#### ⭐ 很少使用
- 解释器模式
- 访问者模式

---

## 设计模式关系图

### 相似模式对比

#### 策略 vs 状态
- **策略模式**: 算法可以自由切换
- **状态模式**: 状态转换有规则限制

#### 装饰 vs 代理
- **装饰模式**: 增强功能，通常多层包装
- **代理模式**: 控制访问，通常单层代理

#### 适配器 vs 桥接 vs 装饰 vs 代理
- **适配器**: 转换接口，事后补救
- **桥接**: 分离抽象和实现，事前设计
- **装饰**: 增强功能，不改变接口
- **代理**: 控制访问，接口相同

#### 组合 vs 装饰
- **组合模式**: 部分-整体层次结构
- **装饰模式**: 动态添加职责

#### 抽象工厂 vs 建造者
- **抽象工厂**: 创建产品族
- **建造者**: 创建复杂对象

#### 观察者 vs 中介者
- **观察者**: 一对多通知
- **中介者**: 多对多协调

---

## 学习建议

### 1. 学习顺序
```
初级: 单例 → 工厂 → 策略 → 观察者
中级: 装饰 → 代理 → 适配器 → 模板方法 → 责任链
高级: 建造者 → 状态 → 命令 → 组合 → 外观
深入: 桥接 → 享元 → 迭代器 → 中介者 → 备忘录
专家: 解释器 → 访问者
```

### 2. 实践建议
- ✅ 先理解概念，再看代码
- ✅ 对比使用前后的差异
- ✅ 思考实际应用场景
- ✅ 尝试修改和扩展代码
- ✅ 结合实际项目使用

### 3. 重点掌握
- **创建型**: 单例、工厂、建造者
- **结构型**: 适配器、装饰、代理
- **行为型**: 策略、观察者、模板方法

---

## 项目结构

```
src/main/java/com/skuu/design/
├── adapter/          # 适配器模式
├── bridge/           # 桥接模式
├── builder/          # 建造者模式
├── chain/            # 责任链模式
├── command/          # 命令模式
├── composite/        # 组合模式
├── decorator/        # 装饰模式
├── facade/           # 外观模式
├── factory/          # 工厂模式（简单、工厂方法、抽象工厂）
├── flyweight/        # 享元模式
├── interpreter/      # 解释器模式
├── iterator/         # 迭代器模式
├── mediator/         # 中介者模式
├── memento/          # 备忘录模式
├── observer/         # 观察者模式
├── prototype/        # 原型模式
├── proxy2/           # 代理模式
├── singleton/        # 单例模式
├── status/           # 状态模式
├── strategy/         # 策略模式
├── templatemethod/   # 模板方法模式
└── visitor/          # 访问者模式
```

---

## 设计模式速查表

| 模式 | 类型 | 目的 | 关键词 |
|------|------|------|--------|
| 单例 | 创建型 | 唯一实例 | getInstance() |
| 工厂方法 | 创建型 | 延迟创建 | createProduct() |
| 抽象工厂 | 创建型 | 产品族 | createProductA/B() |
| 建造者 | 创建型 | 复杂对象 | build() |
| 原型 | 创建型 | 克隆对象 | clone() |
| 适配器 | 结构型 | 接口转换 | Adapter |
| 桥接 | 结构型 | 分离抽象实现 | Bridge |
| 组合 | 结构型 | 树形结构 | Component |
| 装饰 | 结构型 | 动态增强 | Decorator |
| 外观 | 结构型 | 简化接口 | Facade |
| 享元 | 结构型 | 共享对象 | Flyweight |
| 代理 | 结构型 | 控制访问 | Proxy |
| 责任链 | 行为型 | 请求链 | Chain |
| 命令 | 行为型 | 请求对象化 | Command |
| 解释器 | 行为型 | 文法解释 | Interpreter |
| 迭代器 | 行为型 | 遍历聚合 | Iterator |
| 中介者 | 行为型 | 对象协调 | Mediator |
| 备忘录 | 行为型 | 状态保存 | Memento |
| 观察者 | 行为型 | 发布订阅 | Observer |
| 状态 | 行为型 | 状态行为 | State |
| 策略 | 行为型 | 算法切换 | Strategy |
| 模板方法 | 行为型 | 算法骨架 | Template |
| 访问者 | 行为型 | 操作分离 | Visitor |

---

## 快速查找

### 按问题查找模式

#### 如何创建对象？
- 只要一个实例 → **单例模式**
- 创建复杂对象 → **建造者模式**
- 创建对象成本高 → **原型模式**
- 创建哪个类不确定 → **工厂模式**

#### 如何组织对象？
- 接口不兼容 → **适配器模式**
- 部分-整体结构 → **组合模式**
- 动态增加功能 → **装饰模式**
- 简化复杂接口 → **外观模式**
- 大量细粒度对象 → **享元模式**
- 控制对象访问 → **代理模式**
- 分离抽象和实现 → **桥接模式**

#### 如何让对象协作？
- 算法需要切换 → **策略模式**
- 对象状态影响行为 → **状态模式**
- 定义算法骨架 → **模板方法模式**
- 一对多通知 → **观察者模式**
- 请求需要排队处理 → **责任链模式**
- 请求需要封装 → **命令模式**
- 对象间交互复杂 → **中介者模式**
- 需要遍历聚合 → **迭代器模式**
- 需要撤销操作 → **备忘录模式**
- 需要定义语言 → **解释器模式**
- 操作与对象分离 → **访问者模式**

---

## 参考资料

### 书籍
- 《设计模式：可复用面向对象软件的基础》（GoF）
- 《Head First 设计模式》
- 《大话设计模式》

### 在线资源
- [Refactoring Guru](https://refactoring.guru/design-patterns)
- [菜鸟教程](https://www.runoob.com/design-pattern/)

---

## 贡献

欢迎提交问题和改进建议！

## 许可证

MIT License

---

## 作者

**dcx** - 2025

---

**⭐ 如果这个项目对你有帮助，请给个Star！**

