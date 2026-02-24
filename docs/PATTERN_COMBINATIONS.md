# 🔗 设计模式组合应用

在实际项目中，设计模式很少单独使用，通常是多个模式组合使用。本文档展示常见的设计模式组合案例。

---

## 📋 目录

1. [MVC架构中的模式组合](#mvc架构中的模式组合)
2. [电商系统中的模式组合](#电商系统中的模式组合)
3. [日志系统中的模式组合](#日志系统中的模式组合)
4. [缓存系统中的模式组合](#缓存系统中的模式组合)
5. [Spring框架中的模式组合](#spring框架中的模式组合)

---

## MVC架构中的模式组合

### 涉及的模式
- 🎯 观察者模式
- 🎯 策略模式
- 🎯 组合模式
- 🎯 工厂模式

### 架构图
```
Controller (中介者) ←→ Model (观察者模式) → View (观察者)
    ↓                      ↓
Strategy (策略)      Composite (组合)
```

### 代码示例

```java
// 1. Model - 被观察者
public class UserModel extends Observable {
    private List<User> users;
    
    public void addUser(User user) {
        users.add(user);
        setChanged();
        notifyObservers(user);  // 通知观察者
    }
}

// 2. View - 观察者
public class UserListView implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        // Model变化时更新视图
        refreshUserList();
    }
}

// 3. Controller - 中介者 + 策略
public class UserController {
    private UserModel model;
    private UserView view;
    private ValidationStrategy validator;  // 策略模式
    
    public void createUser(UserDTO dto) {
        // 使用策略验证
        if (validator.validate(dto)) {
            User user = UserFactory.create(dto);  // 工厂模式
            model.addUser(user);  // Model会自动通知View
        }
    }
}
```

**模式配合**：
- **观察者模式**：Model和View解耦，Model变化自动更新View
- **策略模式**：Controller可以切换不同的验证策略
- **工厂模式**：统一创建User对象
- **中介者模式**：Controller协调Model和View

---

## 电商系统中的模式组合

### 订单处理流程

涉及的模式：
- 🎯 状态模式
- 🎯 策略模式
- 🎯 责任链模式
- 🎯 观察者模式
- 🎯 工厂模式
- 🎯 命令模式

### 完整示例代码

```java
// ========== 1. 订单状态（状态模式） ==========
public interface OrderState {
    void pay(OrderContext context);
    void ship(OrderContext context);
    void cancel(OrderContext context);
}

public class OrderContext {
    private Order order;
    private OrderState currentState;
    
    public void pay() {
        currentState.pay(this);
    }
}

// ========== 2. 支付策略（策略模式） ==========
public interface PaymentStrategy {
    PaymentResult pay(Order order);
}

@Component("alipay")
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order) {
        // 支付宝支付逻辑
        return new PaymentResult(true, "支付成功");
    }
}

@Component("wechat")
public class WechatPayStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order) {
        // 微信支付逻辑
        return new PaymentResult(true, "支付成功");
    }
}

// ========== 3. 订单校验（责任链模式） ==========
public abstract class OrderValidator {
    protected OrderValidator next;
    
    public void setNext(OrderValidator validator) {
        this.next = validator;
    }
    
    public void validate(Order order) {
        doValidate(order);
        if (next != null) {
            next.validate(order);
        }
    }
    
    protected abstract void doValidate(Order order);
}

public class StockValidator extends OrderValidator {
    @Override
    protected void doValidate(Order order) {
        // 库存校验
        if (!hasStock(order)) {
            throw new OutOfStockException();
        }
    }
}

public class PriceValidator extends OrderValidator {
    @Override
    protected void doValidate(Order order) {
        // 价格校验
        if (!isPriceValid(order)) {
            throw new PriceException();
        }
    }
}

public class AddressValidator extends OrderValidator {
    @Override
    protected void doValidate(Order order) {
        // 地址校验
        if (!isAddressValid(order)) {
            throw new AddressException();
        }
    }
}

// ========== 4. 订单事件（观察者模式） ==========
@Component
public class OrderEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;
    
    public void publishOrderPaid(Order order) {
        publisher.publishEvent(new OrderPaidEvent(this, order));
    }
}

// 监听器1：库存更新
@Component
public class InventoryListener {
    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        inventoryService.decreaseStock(event.getOrder());
    }
}

// 监听器2：发送通知
@Component
public class NotificationListener {
    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        smsService.sendPaymentSuccess(event.getOrder());
        emailService.sendReceipt(event.getOrder());
    }
}

// 监听器3：赠送积分
@Component
public class PointListener {
    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        pointService.givePoints(event.getOrder());
    }
}

// ========== 5. 订单创建（建造者模式 + 工厂模式） ==========
@Component
public class OrderFactory {
    public Order createOrder(OrderDTO dto) {
        return Order.builder()
            .userId(dto.getUserId())
            .items(dto.getItems())
            .totalAmount(calculateTotal(dto))
            .address(dto.getAddress())
            .state(OrderStateFactory.createPendingState())  // 工厂创建初始状态
            .build();
    }
}

// ========== 完整的订单处理流程 ==========
@Service
public class OrderService {
    @Autowired
    private OrderFactory orderFactory;
    @Autowired
    private OrderValidator validatorChain;
    @Autowired
    private Map<String, PaymentStrategy> paymentStrategies;
    @Autowired
    private OrderEventPublisher eventPublisher;
    
    public Order createAndPayOrder(OrderDTO dto, String paymentType) {
        // 1. 工厂模式 + 建造者模式：创建订单
        Order order = orderFactory.createOrder(dto);
        
        // 2. 责任链模式：校验订单
        validatorChain.validate(order);
        
        // 3. 保存订单
        orderRepository.save(order);
        
        // 4. 策略模式：执行支付
        PaymentStrategy strategy = paymentStrategies.get(paymentType);
        PaymentResult result = strategy.pay(order);
        
        if (result.isSuccess()) {
            // 5. 状态模式：改变订单状态
            order.getState().pay(order);
            
            // 6. 观察者模式：发布支付成功事件
            eventPublisher.publishOrderPaid(order);
        }
        
        return order;
    }
}
```

**模式配合分析**：
1. **工厂模式 + 建造者模式**：创建复杂订单对象
2. **责任链模式**：多个校验器串联，职责单一
3. **策略模式**：支付方式可以灵活切换
4. **状态模式**：订单状态流转清晰
5. **观察者模式**：订单事件触发多个后续操作，解耦

---

## 日志系统中的模式组合

### 涉及的模式
- 🎯 单例模式
- 🎯 工厂模式
- 🎯 装饰模式
- 🎯 代理模式
- 🎯 责任链模式

### 代码示例

```java
// 1. 单例模式：日志管理器
public class LoggerManager {
    private static LoggerManager instance = new LoggerManager();
    private Map<String, Logger> loggers = new ConcurrentHashMap<>();
    
    private LoggerManager() {}
    
    public static LoggerManager getInstance() {
        return instance;
    }
    
    public Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, k -> createLogger(name));
    }
    
    // 2. 工厂模式：创建Logger
    private Logger createLogger(String name) {
        return new ConsoleLogger(name);
    }
}

// 3. 装饰模式：增强Logger功能
public interface Logger {
    void log(String message);
}

public class ConsoleLogger implements Logger {
    private String name;
    
    @Override
    public void log(String message) {
        System.out.println("[" + name + "] " + message);
    }
}

// 装饰器：添加时间戳
public class TimestampLoggerDecorator implements Logger {
    private Logger logger;
    
    public TimestampLoggerDecorator(Logger logger) {
        this.logger = logger;
    }
    
    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logger.log("[" + timestamp + "] " + message);
    }
}

// 装饰器：添加日志级别
public class LevelLoggerDecorator implements Logger {
    private Logger logger;
    private String level;
    
    @Override
    public void log(String message) {
        logger.log("[" + level + "] " + message);
    }
}

// 装饰器：添加文件输出
public class FileLoggerDecorator implements Logger {
    private Logger logger;
    private String filename;
    
    @Override
    public void log(String message) {
        logger.log(message);
        writeToFile(message);
    }
}

// 4. 责任链模式：日志过滤链
public abstract class LogFilter {
    protected LogFilter next;
    protected int level;
    
    public void log(int msgLevel, String message) {
        if (msgLevel >= this.level) {
            write(message);
        }
        if (next != null) {
            next.log(msgLevel, message);
        }
    }
    
    protected abstract void write(String message);
}

public class ConsoleLogFilter extends LogFilter {
    public ConsoleLogFilter(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("Console: " + message);
    }
}

public class FileLogFilter extends LogFilter {
    public FileLogFilter(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        writeToFile(message);
    }
}

// 使用多个模式组合
public class LoggerSystem {
    public static void main(String[] args) {
        // 1. 单例模式：获取日志管理器
        LoggerManager manager = LoggerManager.getInstance();
        
        // 2. 工厂模式：创建Logger
        Logger logger = manager.getLogger("AppLogger");
        
        // 3. 装饰模式：多层装饰
        logger = new FileLoggerDecorator(
                   new LevelLoggerDecorator(
                     new TimestampLoggerDecorator(logger), "INFO"));
        
        // 4. 责任链模式：设置过滤链
        LogFilter consoleFilter = new ConsoleLogFilter(LogLevel.INFO);
        LogFilter fileFilter = new FileLogFilter(LogLevel.ERROR);
        consoleFilter.setNext(fileFilter);
        
        // 使用
        logger.log("应用启动成功");
    }
}
```

**模式配合分析**：
- **单例模式**：确保日志管理器唯一
- **工厂模式**：统一创建Logger
- **装饰模式**：动态添加功能（时间戳、级别、文件输出）
- **责任链模式**：日志按级别过滤和分发

---

## 缓存系统中的模式组合

### 涉及的模式
- 🎯 单例模式
- 🎯 工厂模式
- 🎯 装饰模式
- 🎯 代理模式
- 🎯 策略模式

### 代码示例

```java
// ========== 1. 缓存接口 ==========
public interface Cache {
    void put(String key, Object value);
    Object get(String key);
    void remove(String key);
}

// ========== 2. 基础缓存实现 ==========
public class MemoryCache implements Cache {
    private Map<String, Object> cache = new ConcurrentHashMap<>();
    
    @Override
    public void put(String key, Object value) {
        cache.put(key, value);
    }
    
    @Override
    public Object get(String key) {
        return cache.get(key);
    }
}

// ========== 3. 装饰模式：缓存增强 ==========
// 装饰器：LRU缓存
public class LRUCacheDecorator implements Cache {
    private Cache delegate;
    private LinkedHashMap<String, Object> lruMap;
    private int maxSize;
    
    public LRUCacheDecorator(Cache delegate, int maxSize) {
        this.delegate = delegate;
        this.maxSize = maxSize;
        this.lruMap = new LinkedHashMap<String, Object>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return size() > maxSize;
            }
        };
    }
    
    @Override
    public void put(String key, Object value) {
        lruMap.put(key, value);
        delegate.put(key, value);
    }
}

// 装饰器：过期时间缓存
public class TTLCacheDecorator implements Cache {
    private Cache delegate;
    private Map<String, Long> expireTimeMap;
    private long ttl;
    
    @Override
    public Object get(String key) {
        if (isExpired(key)) {
            delegate.remove(key);
            return null;
        }
        return delegate.get(key);
    }
}

// 装饰器：统计缓存
public class StatisticsCacheDecorator implements Cache {
    private Cache delegate;
    private AtomicLong hits = new AtomicLong();
    private AtomicLong misses = new AtomicLong();
    
    @Override
    public Object get(String key) {
        Object value = delegate.get(key);
        if (value != null) {
            hits.incrementAndGet();
        } else {
            misses.incrementAndGet();
        }
        return value;
    }
    
    public double getHitRate() {
        long total = hits.get() + misses.get();
        return total == 0 ? 0 : (double) hits.get() / total;
    }
}

// ========== 4. 工厂模式：创建不同类型的缓存 ==========
public class CacheFactory {
    public static Cache createCache(CacheType type) {
        switch (type) {
            case MEMORY:
                return new MemoryCache();
            case REDIS:
                return new RedisCache();
            case CAFFEINE:
                return new CaffeineCache();
            default:
                throw new IllegalArgumentException("Unknown cache type");
        }
    }
    
    // 创建增强缓存
    public static Cache createEnhancedCache(CacheType type, int maxSize, long ttl) {
        Cache cache = createCache(type);
        
        // 多层装饰
        cache = new StatisticsCacheDecorator(
                  new TTLCacheDecorator(
                    new LRUCacheDecorator(cache, maxSize), ttl));
        
        return cache;
    }
}

// ========== 5. 代理模式：缓存代理 ==========
@Aspect
@Component
public class CacheProxy {
    @Autowired
    private Cache cache;
    
    @Around("@annotation(Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        String key = generateKey(pjp);
        
        // 先从缓存获取
        Object value = cache.get(key);
        if (value != null) {
            System.out.println("缓存命中: " + key);
            return value;
        }
        
        // 缓存未命中，执行方法
        System.out.println("缓存未命中: " + key);
        value = pjp.proceed();
        
        // 放入缓存
        cache.put(key, value);
        return value;
    }
}

// ========== 6. 策略模式：缓存淘汰策略 ==========
public interface EvictionStrategy {
    String evict(Map<String, Object> cache);
}

public class LRUEvictionStrategy implements EvictionStrategy {
    @Override
    public String evict(Map<String, Object> cache) {
        // LRU淘汰最少使用的
        return findLeastRecentlyUsed(cache);
    }
}

public class LFUEvictionStrategy implements EvictionStrategy {
    @Override
    public String evict(Map<String, Object> cache) {
        // LFU淘汰最少访问的
        return findLeastFrequentlyUsed(cache);
    }
}

// ========== 完整使用示例 ==========
@Service
public class UserService {
    @Autowired
    private CacheFactory cacheFactory;
    
    public void init() {
        // 1. 单例模式：CacheManager
        CacheManager manager = CacheManager.getInstance();
        
        // 2. 工厂模式：创建缓存
        Cache cache = cacheFactory.createEnhancedCache(
            CacheType.MEMORY, 1000, 3600000);
        
        manager.registerCache("userCache", cache);
    }
    
    // 3. 代理模式：@Cacheable自动缓存
    @Cacheable(key = "#id")
    public User findById(Long id) {
        return userDao.findById(id);
    }
}
```

**模式配合分析**：
1. **单例模式**：CacheManager全局唯一
2. **工厂模式**：创建不同类型的缓存
3. **装饰模式**：LRU、TTL、统计等功能叠加
4. **代理模式**：透明地添加缓存功能
5. **策略模式**：缓存淘汰策略可切换

**实际框架应用**：
- Spring Cache
- MyBatis二级缓存
- Redis
- Caffeine

---

## 消息队列系统中的模式组合

### 涉及的模式
- 🎯 观察者模式
- 🎯 命令模式
- 🎯 策略模式
- 🎯 模板方法模式

### 代码示例

```java
// ========== 1. 命令模式：消息封装 ==========
public interface Message extends Command {
    String getTopic();
    String getContent();
    void execute();  // 命令接口
}

public class OrderMessage implements Message {
    private String topic = "order";
    private Order order;
    
    @Override
    public void execute() {
        // 处理订单消息
        orderService.process(order);
    }
}

// ========== 2. 观察者模式：发布-订阅 ==========
public interface MessagePublisher {
    void publish(String topic, Message message);
    void subscribe(String topic, MessageListener listener);
}

public interface MessageListener {
    void onMessage(Message message);
}

public class MessageBroker implements MessagePublisher {
    private Map<String, List<MessageListener>> subscribers = new ConcurrentHashMap<>();
    
    @Override
    public void subscribe(String topic, MessageListener listener) {
        subscribers.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>())
                   .add(listener);
    }
    
    @Override
    public void publish(String topic, Message message) {
        List<MessageListener> listeners = subscribers.get(topic);
        if (listeners != null) {
            for (MessageListener listener : listeners) {
                listener.onMessage(message);
            }
        }
    }
}

// ========== 3. 策略模式：消息序列化策略 ==========
public interface SerializationStrategy {
    byte[] serialize(Object obj);
    Object deserialize(byte[] data, Class<?> clazz);
}

@Component("json")
public class JsonSerializationStrategy implements SerializationStrategy {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}

@Component("protobuf")
public class ProtobufSerializationStrategy implements SerializationStrategy {
    @Override
    public byte[] serialize(Object obj) {
        // Protobuf序列化
        return protoData;
    }
}

// ========== 4. 模板方法模式：消息处理模板 ==========
public abstract class MessageProcessor {
    
    // 模板方法
    public final void process(Message message) {
        validate(message);
        preProcess(message);
        doProcess(message);  // 抽象方法
        postProcess(message);
        log(message);
    }
    
    protected abstract void doProcess(Message message);
    
    private void validate(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("消息不能为空");
        }
    }
    
    protected void preProcess(Message message) {
        // 钩子方法，子类可选实现
    }
    
    protected void postProcess(Message message) {
        // 钩子方法，子类可选实现
    }
}

public class OrderMessageProcessor extends MessageProcessor {
    @Override
    protected void doProcess(Message message) {
        OrderMessage orderMsg = (OrderMessage) message;
        orderMsg.execute();
    }
}

// ========== 完整使用 ==========
@Service
public class MessageService {
    @Autowired
    private MessageBroker broker;
    @Autowired
    private Map<String, SerializationStrategy> serializationStrategies;
    
    public void init() {
        // 1. 观察者模式：订阅消息
        broker.subscribe("order", message -> {
            System.out.println("订单监听器收到消息: " + message.getContent());
        });
        
        broker.subscribe("user", message -> {
            System.out.println("用户监听器收到消息: " + message.getContent());
        });
    }
    
    public void sendOrder(Order order) {
        // 2. 命令模式：创建消息命令
        Message message = new OrderMessage("order", order);
        
        // 3. 策略模式：选择序列化策略
        SerializationStrategy strategy = serializationStrategies.get("json");
        byte[] data = strategy.serialize(message);
        
        // 4. 观察者模式：发布消息
        broker.publish("order", message);
    }
}
```

---

## Spring Boot应用中的模式组合

### 完整的Web应用示例

```java
// ========== 1. 单例 + 工厂：Configuration ==========
@Configuration
public class AppConfig {
    
    // 工厂方法创建Bean（默认单例）
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

// ========== 2. 代理：AOP切面 ==========
@Aspect
@Component
public class LogAspect {
    // 代理模式：为所有Controller方法添加日志
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        System.out.println("调用方法: " + methodName);
        
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long duration = System.currentTimeMillis() - start;
        
        System.out.println("方法执行时间: " + duration + "ms");
        return result;
    }
}

// ========== 3. 策略：多种实现自动注入 ==========
@Service
public class NotificationService {
    @Autowired
    private Map<String, NotificationChannel> channels;  // 策略Map
    
    public void notify(String channel, User user, String content) {
        NotificationChannel strategy = channels.get(channel);
        if (strategy != null) {
            strategy.send(user, content);
        }
    }
}

@Component("sms")
public class SmsChannel implements NotificationChannel { }

@Component("email")
public class EmailChannel implements NotificationChannel { }

// ========== 4. 模板方法：AbstractController ==========
public abstract class BaseController<T, ID> {
    
    // 模板方法
    public ResponseEntity<T> save(@RequestBody T entity) {
        validate(entity);
        T saved = doSave(entity);  // 抽象方法
        afterSave(saved);
        return ResponseEntity.ok(saved);
    }
    
    protected abstract T doSave(T entity);
    
    protected void validate(T entity) {
        // 通用验证
    }
    
    protected void afterSave(T entity) {
        // 钩子方法
    }
}

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Long> {
    @Override
    protected User doSave(User user) {
        return userService.save(user);
    }
}

// ========== 5. 观察者：事件驱动 ==========
@Component
public class UserEventHandler {
    
    @EventListener
    @Async  // 异步处理
    public void onUserRegister(UserRegisterEvent event) {
        User user = event.getUser();
        
        // 发送欢迎邮件
        emailService.sendWelcome(user);
        
        // 赠送积分
        pointService.givePoints(user, 100);
        
        // 推送消息
        pushService.sendNotification(user);
    }
    
    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        // 更新库存、发送通知等
    }
}

// ========== 6. 责任链：拦截器链 ==========
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register");
        
        registry.addInterceptor(new PermissionInterceptor())
                .addPathPatterns("/admin/**");
        
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");
    }
}

// ========== 7. 装饰：全局异常处理 ==========
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        // 装饰异常响应
        ErrorResponse response = ErrorResponse.builder()
            .code(e.getCode())
            .message(e.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
```

---

## 实际项目案例：用户管理系统

### 涉及的模式组合

```java
// ========== 完整的用户注册流程 ==========
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterDTO dto) {
        // 整个流程涉及多个设计模式
        User user = userService.register(dto);
        return ResponseEntity.ok(UserDTO.from(user));
    }
}

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;  // 策略模式
    @Autowired
    private ApplicationEventPublisher publisher;  // 观察者模式
    @Autowired
    private UserFactory userFactory;  // 工厂模式
    
    @Transactional  // 代理模式
    public User register(RegisterDTO dto) {
        // 1. 责任链模式：验证
        new UsernameValidator()
            .setNext(new PasswordValidator())
            .setNext(new EmailValidator())
            .validate(dto);
        
        // 2. 工厂模式 + 建造者模式：创建用户
        User user = userFactory.createUser(dto);
        
        // 3. 策略模式：密码加密
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);
        
        // 4. 保存用户
        user = userRepository.save(user);
        
        // 5. 观察者模式：发布注册事件
        publisher.publishEvent(new UserRegisterEvent(this, user));
        
        return user;
    }
}

// 工厂类
@Component
public class UserFactory {
    public User createUser(RegisterDTO dto) {
        return User.builder()
            .username(dto.getUsername())
            .email(dto.getEmail())
            .status(UserStatus.ACTIVE)
            .registerTime(LocalDateTime.now())
            .build();
    }
}

// 事件监听器（观察者）
@Component
public class UserEventListener {
    
    @EventListener
    @Async
    public void onUserRegister(UserRegisterEvent event) {
        User user = event.getUser();
        
        // 异步处理多个任务
        sendWelcomeEmail(user);
        giveWelcomePoints(user);
        createDefaultSettings(user);
    }
}

// 责任链：验证器
public abstract class RegisterValidator {
    protected RegisterValidator next;
    
    public RegisterValidator setNext(RegisterValidator validator) {
        this.next = validator;
        return validator;
    }
    
    public void validate(RegisterDTO dto) {
        doValidate(dto);
        if (next != null) {
            next.validate(dto);
        }
    }
    
    protected abstract void doValidate(RegisterDTO dto);
}

public class UsernameValidator extends RegisterValidator {
    @Override
    protected void doValidate(RegisterDTO dto) {
        if (StringUtils.isEmpty(dto.getUsername())) {
            throw new ValidationException("用户名不能为空");
        }
        if (dto.getUsername().length() < 3) {
            throw new ValidationException("用户名长度至少3个字符");
        }
    }
}
```

**这个案例组合了7种设计模式**：
1. ✅ 工厂模式 - 创建User对象
2. ✅ 建造者模式 - User.builder()
3. ✅ 策略模式 - 密码加密策略
4. ✅ 责任链模式 - 注册验证链
5. ✅ 代理模式 - @Transactional事务代理
6. ✅ 观察者模式 - 注册事件发布订阅
7. ✅ 模板方法模式 - 抽象验证器

---

## 设计模式组合的经典案例

### 案例1：MVC + 观察者 + 策略 + 工厂

```
MVC架构
├── Model (观察者模式的Subject)
├── View (观察者模式的Observer)
└── Controller (策略模式 + 工厂模式)
```

### 案例2：AOP = 代理 + 责任链 + 装饰

```
Spring AOP
├── 代理模式：JDK动态代理或CGLIB
├── 责任链模式：拦截器链
└── 装饰模式：增强原有功能
```

### 案例3：ORM框架 = 代理 + 工厂 + 模板方法 + 装饰

```
MyBatis/Hibernate
├── 代理模式：Mapper接口代理
├── 工厂模式：SqlSessionFactory
├── 模板方法：BaseExecutor
└── 装饰模式：Cache装饰器
```

---

## 🎯 设计模式组合原则

### 1. 职责分离
- 每个模式解决特定问题
- 不要强行使用模式

### 2. 渐进式应用
- 从简单开始
- 逐步引入模式
- 重构优化

### 3. 适度使用
- 避免过度设计
- 权衡复杂度和收益

### 4. 保持简单
- 优先使用简单方案
- 必要时才引入模式

---

## 📊 常见组合套路

### 组合1：工厂 + 单例
```java
// 单例的工厂
public class CacheFactory {
    private static CacheFactory instance = new CacheFactory();
    
    public static CacheFactory getInstance() {
        return instance;
    }
    
    public Cache createCache(CacheType type) {
        // 工厂方法
    }
}
```

### 组合2：装饰 + 代理
```java
// 代理包装装饰器
Cache cache = new MemoryCache();
cache = new LRUCacheDecorator(cache);  // 装饰
cache = CacheProxy.createProxy(cache);  // 代理
```

### 组合3：策略 + 工厂
```java
// 工厂创建策略
public class StrategyFactory {
    public Strategy createStrategy(String type) {
        // 根据类型创建不同策略
    }
}
```

### 组合4：观察者 + 命令
```java
// 事件就是命令
public class OrderEvent extends ApplicationEvent implements Command {
    public void execute() {
        // 命令执行
    }
}
```

### 组合5：责任链 + 命令
```java
// 责任链处理命令
public class CommandChain {
    public void process(Command command) {
        validator.validate(command);
        executor.execute(command);
        logger.log(command);
    }
}
```

---

## 🔧 实际项目中的最佳实践

### 1. 分层架构中的模式应用

```
Controller层：
├── 模板方法模式 - BaseController
├── 策略模式 - 参数验证策略
└── 装饰模式 - 统一响应包装

Service层：
├── 工厂模式 - 对象创建
├── 策略模式 - 业务策略
├── 观察者模式 - 事件发布
├── 代理模式 - 事务、缓存
└── 责任链模式 - 业务校验

DAO层：
├── 代理模式 - Mapper代理
├── 模板方法模式 - JdbcTemplate
└── 单例模式 - 连接池
```

### 2. 微服务架构中的模式应用

```
API网关：
├── 外观模式 - 统一入口
├── 责任链模式 - 过滤器链
├── 装饰模式 - 请求/响应增强
└── 代理模式 - 负载均衡

服务层：
├── 单例模式 - 服务实例
├── 工厂模式 - 客户端创建
├── 代理模式 - RPC代理
└── 熔断器 - 状态模式

配置中心：
├── 单例模式 - 配置管理器
├── 观察者模式 - 配置变更通知
└── 策略模式 - 不同环境配置
```

---

## 💡 学习建议

### 1. 识别框架中的模式
- 阅读Spring源码
- 找出使用的设计模式
- 理解为什么这样设计

### 2. 实际项目应用
- 不要为了用模式而用模式
- 先解决问题，再考虑模式
- 模式是重构的结果

### 3. 模式组合
- 理解单个模式
- 学习模式组合
- 应用到项目

---

## 📖 相关文档

- [设计模式详细文档](../DESIGN_PATTERNS.md)
- [框架中的设计模式](FRAMEWORK_PATTERNS.md)
- [UML类图指南](UML_GUIDE.md)

---

**下一步**: 查看具体的[实战代码示例](../src/main/java/com/skuu/design/combination/)

