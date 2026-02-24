# 订单状态系统 (Order System)

## 📚 概述

这是一个基于多种设计模式构建的可扩展订单状态管理系统。系统采用主流的设计模式和最佳实践，确保代码的可维护性、可扩展性和健壮性。

## 🏗️ 架构设计

### 使用的设计模式

1. **状态模式 (State Pattern)** - 核心模式
   - 将订单状态封装为独立的状态对象
   - 每个状态对象负责处理该状态下的所有操作

2. **工厂模式 (Factory Pattern)**
   - `StateFactory`: 创建和管理状态对象
   - 使用单例模式 + 对象缓存，提高性能

3. **观察者模式 (Observer Pattern)**
   - `OrderObserver`: 监听订单状态变化
   - 实现日志记录、通知发送、库存管理等

4. **责任链模式 (Chain of Responsibility)**
   - `StateTransitionValidator`: 验证状态转换的合法性
   - 支持扩展验证规则

5. **模板方法模式 (Template Method)**
   - `AbstractOrderState`: 提供状态操作的默认实现
   - 减少重复代码

6. **策略模式 (Strategy Pattern)**
   - 不同状态下的处理策略可以独立变化

## 📁 项目结构

```
ordersystem/
├── model/                          # 领域模型
│   ├── Order.java                  # 订单实体
│   └── OrderStatus.java            # 订单状态枚举
├── state/                          # 状态模式核心
│   ├── OrderState.java             # 状态接口
│   ├── AbstractOrderState.java    # 抽象状态类
│   ├── StateContext.java           # 状态上下文
│   └── states/                     # 具体状态实现
│       ├── PendingPaymentState.java
│       ├── PaidState.java
│       ├── ShippedState.java
│       ├── DeliveredState.java
│       ├── CompletedState.java
│       ├── CancelledState.java
│       ├── RefundingState.java
│       └── RefundedState.java
├── factory/                        # 工厂模式
│   └── StateFactory.java           # 状态工厂
├── validator/                      # 验证器
│   └── StateTransitionValidator.java
├── observer/                       # 观察者模式
│   ├── OrderObserver.java         # 观察者接口
│   └── impl/                       # 具体观察者
│       ├── LoggingObserver.java
│       ├── NotificationObserver.java
│       └── InventoryObserver.java
├── service/                        # 服务层
│   └── OrderService.java           # 订单服务
├── OrderSystemTest.java            # 测试类
└── README.md                       # 本文档
```

## 🔄 状态流转图

```
PENDING_PAYMENT (待支付)
    ├── pay() → PAID (已支付)
    └── cancel() → CANCELLED (已取消)

PAID (已支付)
    ├── ship() → SHIPPED (已发货)
    └── refund() → REFUNDING (退款中)

SHIPPED (已发货)
    └── confirm() → DELIVERED (已送达)

DELIVERED (已送达)
    └── complete() → COMPLETED (已完成)

REFUNDING (退款中)
    └── refund() → REFUNDED (已退款)

终态（不可再转换）:
    - COMPLETED (已完成)
    - CANCELLED (已取消)
    - REFUNDED (已退款)
```

## ✨ 核心特性

### 1. 状态转换验证
- 自动验证状态转换的合法性
- 防止非法状态转换
- 支持业务规则验证

### 2. 状态变化通知
- 自动通知所有观察者
- 支持日志记录、用户通知、库存管理等

### 3. 可扩展性
- 新增状态：只需实现 `OrderState` 接口
- 新增观察者：实现 `OrderObserver` 接口并注册
- 新增验证规则：扩展 `StateTransitionValidator`

### 4. 类型安全
- 使用枚举定义状态，避免魔法字符串
- 编译时类型检查

## 💡 使用示例

### 基本使用

```java
// 1. 创建订单服务
OrderService orderService = new OrderService();

// 2. 创建订单
StateContext context = orderService.createOrder(
    "张三", "USER001", "PROD001", "MacBook Pro", 12999.00
);

// 3. 支付订单
context.pay();

// 4. 发货
context.ship();

// 5. 确认收货
context.confirm();

// 6. 完成订单
context.complete();
```

### 异常处理

```java
try {
    // 尝试对已完成订单进行支付（会抛出异常）
    context.pay();
} catch (UnsupportedOperationException e) {
    System.out.println("操作不支持: " + e.getMessage());
}
```

## 🔧 扩展指南

### 添加新状态

1. 在 `OrderStatus` 枚举中添加新状态
2. 创建新的状态类继承 `AbstractOrderState`
3. 在 `StateFactory` 中注册新状态

```java
// 1. 添加枚举
PARTIAL_REFUND("PARTIAL_REFUND", "部分退款", 0)

// 2. 创建状态类
public class PartialRefundState extends AbstractOrderState {
    // 实现必要的方法
}

// 3. 在工厂中注册
stateCache.put(OrderStatus.PARTIAL_REFUND, new PartialRefundState());
```

### 添加新观察者

```java
public class EmailObserver implements OrderObserver {
    @Override
    public void onStatusChanged(Order order, OrderStatus previousStatus, OrderStatus newStatus) {
        // 发送邮件通知
    }
}

// 在 OrderService 中注册
context.addObserver(new EmailObserver());
```

### 添加验证规则

```java
// 在 StateTransitionValidator 中添加
private boolean validateBusinessRules(...) {
    // 添加新的验证逻辑
    if (targetStatus == OrderStatus.PAID) {
        // 检查用户信用额度等
    }
    return true;
}
```

## 🎯 设计优势

1. **开闭原则**: 对扩展开放，对修改封闭
2. **单一职责**: 每个类只负责一个功能
3. **依赖倒置**: 依赖抽象而非具体实现
4. **接口隔离**: 接口设计精简，职责明确
5. **里氏替换**: 所有状态对象可以互相替换

## 🚀 运行测试

```bash
# 编译
mvn compile

# 运行测试
mvn exec:java -Dexec.mainClass="com.skuu.design.ordersystem.OrderSystemTest"
```

## 📝 最佳实践

1. **状态对象无状态**: 状态对象应该是无状态的，可以复用
2. **状态转换原子性**: 状态转换应该是原子的，要么成功要么失败
3. **观察者解耦**: 观察者之间应该相互独立，不相互依赖
4. **验证规则集中**: 所有验证逻辑集中在验证器中
5. **异常处理**: 使用明确的异常类型，便于错误处理

## 🔍 与现有 status 包的区别

- **更完善**: 结合了多种设计模式
- **更可扩展**: 易于添加新状态和功能
- **更健壮**: 包含验证、观察者等机制
- **更规范**: 遵循主流的设计模式和最佳实践

## 📚 参考

- [状态模式 - GoF设计模式](https://refactoring.guru/design-patterns/state)
- [观察者模式 - GoF设计模式](https://refactoring.guru/design-patterns/observer)
- [工厂模式 - GoF设计模式](https://refactoring.guru/design-patterns/factory-method)
