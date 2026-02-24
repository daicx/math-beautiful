# 🌟 主流框架中的设计模式应用

本文档详细介绍了Spring、MyBatis、JDK等主流框架和库中如何应用设计模式。

---

## 🍃 Spring框架中的设计模式

### 1. 单例模式 (Singleton Pattern)

**应用场景**：Spring Bean默认是单例的

```java
// Spring配置
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserService();  // 默认单例
    }
}

// 使用
@Autowired
private UserService userService;  // 注入的是同一个实例
```

**实现原理**：
- Spring容器维护一个Bean单例池（Map）
- 第一次获取时创建，后续直接从池中获取
- 类似我们实现的单例模式，但由容器管理

**对应代码**：`src/main/java/com/skuu/design/singleton/`

---

### 2. 工厂模式 (Factory Pattern)

**应用场景**：BeanFactory和ApplicationContext

```java
// BeanFactory - 简单工厂
BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
UserService service = (UserService) factory.getBean("userService");

// ApplicationContext - 工厂方法
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
UserService service = context.getBean(UserService.class);

// FactoryBean - 自定义工厂
public class MyFactoryBean implements FactoryBean<MyObject> {
    @Override
    public MyObject getObject() throws Exception {
        return new MyObject();  // 自定义创建逻辑
    }
}
```

**实现原理**：
- `BeanFactory`是顶层接口
- `ApplicationContext`是高级工厂
- `FactoryBean`允许自定义Bean创建逻辑

**对应代码**：`src/main/java/com/skuu/design/factory/`

---

### 3. 代理模式 (Proxy Pattern)

**应用场景**：Spring AOP的核心实现

```java
// 声明式事务 - 基于代理实现
@Service
public class UserService {
    @Transactional  // Spring会为这个类创建代理
    public void saveUser(User user) {
        // 实际执行：
        // 1. 代理：开启事务
        // 2. 真实对象：执行业务逻辑
        // 3. 代理：提交/回滚事务
        userDao.save(user);
    }
}

// 自定义AOP
@Aspect
@Component
public class LogAspect {
    @Around("execution(* com.skuu.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("方法执行前");
        Object result = pjp.proceed();  // 调用真实方法
        System.out.println("方法执行后");
        return result;
    }
}
```

**实现原理**：
- JDK动态代理（接口）
- CGLIB代理（类）
- 代理对象 = 增强逻辑 + 真实对象

**对应代码**：`src/main/java/com/skuu/design/proxy2/`

---

### 4. 模板方法模式 (Template Method Pattern)

**应用场景**：JdbcTemplate、RestTemplate、RedisTemplate

```java
// JdbcTemplate
@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public User findById(Long id) {
        // 模板方法封装了：
        // 1. 获取连接
        // 2. 创建Statement
        // 3. 执行SQL（这部分由我们定义）
        // 4. 处理结果集
        // 5. 关闭资源
        return jdbcTemplate.queryForObject(
            "SELECT * FROM users WHERE id = ?",
            new Object[]{id},
            (rs, rowNum) -> {  // 只需要定义结果映射
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                return user;
            }
        );
    }
}

// TransactionTemplate
public void doInTransaction() {
    transactionTemplate.execute(status -> {
        // 只需要定义业务逻辑
        // 事务的开启、提交、回滚由模板处理
        userDao.save(user);
        return null;
    });
}
```

**实现原理**：
- 模板类定义算法骨架
- 回调函数定义可变部分
- 资源管理、异常处理等由模板处理

**对应代码**：`src/main/java/com/skuu/design/templatemethod/`

---

### 5. 观察者模式 (Observer Pattern)

**应用场景**：ApplicationEvent和ApplicationListener

```java
// 1. 定义事件
public class UserRegisterEvent extends ApplicationEvent {
    private User user;
    
    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}

// 2. 发布事件（Subject）
@Service
public class UserService {
    @Autowired
    private ApplicationEventPublisher publisher;
    
    public void register(User user) {
        // 保存用户
        userDao.save(user);
        
        // 发布事件
        publisher.publishEvent(new UserRegisterEvent(this, user));
    }
}

// 3. 监听事件（Observer）
@Component
public class EmailListener {
    @EventListener
    public void handleUserRegister(UserRegisterEvent event) {
        User user = event.getUser();
        // 发送欢迎邮件
        emailService.sendWelcomeEmail(user);
    }
}

@Component
public class PointListener {
    @EventListener
    public void handleUserRegister(UserRegisterEvent event) {
        User user = event.getUser();
        // 赠送积分
        pointService.givePoints(user, 100);
    }
}
```

**实现原理**：
- ApplicationEventPublisher是主题
- @EventListener标注的方法是观察者
- Spring容器负责事件分发

**对应代码**：`src/main/java/com/skuu/design/observer/`

---

### 6. 策略模式 (Strategy Pattern)

**应用场景**：Resource加载策略

```java
// Spring的ResourceLoader使用策略模式
public interface ResourceLoader {
    Resource getResource(String location);
}

// 不同的资源加载策略
ClassPathResource      // classpath:config.xml
FileSystemResource     // file:/path/to/config.xml
UrlResource           // http://example.com/config.xml

// 使用
@Autowired
private ResourceLoader resourceLoader;

Resource resource = resourceLoader.getResource("classpath:application.yml");
```

**实际业务应用**：
```java
// 支付策略
public interface PaymentStrategy {
    void pay(BigDecimal amount);
}

@Component("alipay")
public class AlipayStrategy implements PaymentStrategy {
    public void pay(BigDecimal amount) {
        System.out.println("支付宝支付: " + amount);
    }
}

@Component("wechat")
public class WechatPayStrategy implements PaymentStrategy {
    public void pay(BigDecimal amount) {
        System.out.println("微信支付: " + amount);
    }
}

// 使用
@Service
public class PaymentService {
    @Autowired
    private Map<String, PaymentStrategy> strategyMap;  // Spring自动注入
    
    public void pay(String type, BigDecimal amount) {
        PaymentStrategy strategy = strategyMap.get(type);
        strategy.pay(amount);
    }
}
```

**对应代码**：`src/main/java/com/skuu/design/strategy/`

---

### 7. 装饰模式 (Decorator Pattern)

**应用场景**：Spring的BeanWrapper

```java
// Spring使用装饰模式包装Bean
BeanWrapper wrapper = new BeanWrapperImpl(user);
wrapper.setPropertyValue("name", "张三");
wrapper.setPropertyValue("age", 25);

// HTTP请求装饰
public class LoggingRequestWrapper extends HttpServletRequestWrapper {
    public LoggingRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        log.info("参数: {} = {}", name, value);
        return value;
    }
}
```

**对应代码**：`src/main/java/com/skuu/design/decorator/`

---

### 8. 适配器模式 (Adapter Pattern)

**应用场景**：Spring MVC的HandlerAdapter

```java
// Spring MVC使用适配器适配不同的Controller
public interface HandlerAdapter {
    boolean supports(Object handler);
    ModelAndView handle(HttpServletRequest request, 
                       HttpServletResponse response, 
                       Object handler);
}

// 不同的适配器
SimpleControllerHandlerAdapter      // 适配Controller接口
HttpRequestHandlerAdapter           // 适配HttpRequestHandler
RequestMappingHandlerAdapter        // 适配@RequestMapping注解
```

**对应代码**：`src/main/java/com/skuu/design/adapter/`

---

### 9. 责任链模式 (Chain of Responsibility)

**应用场景**：Spring Security过滤器链

```java
// Security过滤器链
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()  // 授权过滤器
            .antMatchers("/public/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()  // 登录过滤器
            .and()
            .logout()     // 登出过滤器
            .and()
            .csrf();      // CSRF过滤器
    }
}

// Servlet Filter Chain
@WebFilter("/*")
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        System.out.println("请求前");
        chain.doFilter(request, response);  // 传递给下一个过滤器
        System.out.println("请求后");
    }
}
```

**对应代码**：`src/main/java/com/skuu/design/chain/`

---

### 10. 建造者模式 (Builder Pattern)

**应用场景**：Spring的UriComponentsBuilder

```java
// Spring URI构建
UriComponents uri = UriComponentsBuilder
    .fromUriString("http://example.com")
    .path("/users/{id}")
    .queryParam("page", 1)
    .queryParam("size", 10)
    .build()
    .expand(123)
    .encode();

// Lombok的@Builder
@Builder
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

// 使用
User user = User.builder()
    .name("张三")
    .age(25)
    .email("zhangsan@example.com")
    .build();
```

**对应代码**：`src/main/java/com/skuu/design/builder/`

---

## 🗄️ MyBatis中的设计模式

### 1. 建造者模式

**应用场景**：SqlSessionFactoryBuilder

```java
// MyBatis使用建造者模式构建SqlSessionFactory
SqlSessionFactory factory = new SqlSessionFactoryBuilder()
    .build(inputStream);

// 内部实现
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        return build(inputStream, null, null);
    }
    
    public SqlSessionFactory build(InputStream inputStream, 
                                   String environment, 
                                   Properties properties) {
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
        return build(parser.parse());
    }
}
```

---

### 2. 工厂模式

**应用场景**：SqlSessionFactory创建SqlSession

```java
// 工厂接口
public interface SqlSessionFactory {
    SqlSession openSession();
    SqlSession openSession(boolean autoCommit);
    SqlSession openSession(Connection connection);
}

// 使用
SqlSessionFactory factory = ...;
SqlSession session = factory.openSession();  // 工厂方法创建会话
```

---

### 3. 代理模式

**应用场景**：Mapper接口的动态代理

```java
// 定义Mapper接口（没有实现类）
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Long id);
    
    @Insert("INSERT INTO users(name, age) VALUES(#{name}, #{age})")
    int insert(User user);
}

// MyBatis为接口创建代理对象
UserMapper mapper = sqlSession.getMapper(UserMapper.class);
User user = mapper.findById(1L);  // 调用的是代理对象

// 内部实现原理
public class MapperProxy<T> implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 1. 获取SQL语句
        // 2. 执行SQL
        // 3. 处理结果
        return sqlSession.selectOne(method.getName(), args[0]);
    }
}
```

**实现原理**：
- MapperProxyFactory创建代理
- MapperProxy实现InvocationHandler
- 将方法调用转换为SQL执行

---

### 4. 模板方法模式

**应用场景**：BaseExecutor

```java
// 抽象模板类
public abstract class BaseExecutor implements Executor {
    
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) {
        // 模板方法
        BoundSql boundSql = ms.getBoundSql(parameter);
        CacheKey key = createCacheKey(ms, parameter, boundSql);
        return query(ms, parameter, boundSql, key);  // 调用抽象方法
    }
    
    // 抽象方法，由子类实现
    protected abstract <E> List<E> doQuery(MappedStatement ms, 
                                           Object parameter, 
                                           BoundSql boundSql);
}

// 具体实现
public class SimpleExecutor extends BaseExecutor {
    @Override
    protected <E> List<E> doQuery(...) {
        // 简单执行器的实现
    }
}

public class ReuseExecutor extends BaseExecutor {
    @Override
    protected <E> List<E> doQuery(...) {
        // 重用Statement的实现
    }
}
```

---

### 5. 装饰模式

**应用场景**：缓存装饰器

```java
// MyBatis的缓存使用装饰模式
public interface Cache {
    void putObject(Object key, Object value);
    Object getObject(Object key);
}

// 基础缓存
public class PerpetualCache implements Cache {
    private Map<Object, Object> cache = new HashMap<>();
    // 实现基本的缓存功能
}

// 装饰器：LRU缓存
public class LruCache implements Cache {
    private Cache delegate;  // 被装饰的缓存
    
    public LruCache(Cache delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public void putObject(Object key, Object value) {
        // LRU逻辑
        delegate.putObject(key, value);
    }
}

// 装饰器：定时清理缓存
public class ScheduledCache implements Cache {
    private Cache delegate;
    private long clearInterval = 60 * 60 * 1000;  // 1小时
    
    @Override
    public Object getObject(Object key) {
        clearWhenStale();  // 检查是否需要清理
        return delegate.getObject(key);
    }
}

// 多层装饰
Cache cache = new ScheduledCache(
                new LruCache(
                  new PerpetualCache("user-cache")));
```

---

## 📚 JDK中的设计模式

### 1. 迭代器模式

```java
// Java集合框架
List<String> list = new ArrayList<>();
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// for-each语法糖（基于迭代器）
for (String item : list) {
    System.out.println(item);
}
```

**对应代码**：`src/main/java/com/skuu/design/iterator/`

---

### 2. 装饰模式

```java
// Java I/O流的装饰模式
InputStream in = new FileInputStream("file.txt");
in = new BufferedInputStream(in);       // 添加缓冲功能
in = new DataInputStream(in);           // 添加读取基本类型功能

// 多层装饰
OutputStream out = new FileOutputStream("file.txt");
out = new BufferedOutputStream(out);
out = new GZIPOutputStream(out);        // 添加压缩功能
out = new DataOutputStream(out);
```

**对应代码**：`src/main/java/com/skuu/design/decorator/`

---

### 3. 适配器模式

```java
// Arrays.asList - 适配器
List<String> list = Arrays.asList("OrderCglibProxy", "b", "c");  // 数组适配为List

// InputStreamReader - 适配器
Reader reader = new InputStreamReader(inputStream, "UTF-8");
// 将字节流适配为字符流
```

**对应代码**：`src/main/java/com/skuu/design/adapter/`

---

### 4. 代理模式

```java
// JDK动态代理
InvocationHandler handler = new InvocationHandler() {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("方法调用前");
        Object result = method.invoke(target, args);
        System.out.println("方法调用后");
        return result;
    }
};

UserService proxy = (UserService) Proxy.newProxyInstance(
    classLoader, 
    new Class[]{UserService.class}, 
    handler
);
```

**对应代码**：`src/main/java/com/skuu/design/proxy2/`

---

### 5. 单例模式

```java
// Runtime类
Runtime runtime = Runtime.getRuntime();  // 单例

// Calendar类
Calendar calendar = Calendar.getInstance();  // 工厂方法返回单例
```

**对应代码**：`src/main/java/com/skuu/design/singleton/`

---

## 🎮 实际业务场景应用

### 场景1：电商订单系统（组合多个模式）

```java
// 1. 状态模式 - 订单状态流转
@Service
public class OrderService {
    public void processOrder(Order order) {
        OrderState state = order.getState();
        state.process(order);  // 根据状态执行不同逻辑
    }
}

// 2. 策略模式 - 支付方式
@Service
public class PaymentService {
    @Autowired
    private Map<String, PaymentStrategy> strategies;
    
    public void pay(String type, BigDecimal amount) {
        strategies.get(type).pay(amount);
    }
}

// 3. 责任链模式 - 订单校验
public class OrderValidationChain {
    public void validate(Order order) {
        new StockValidator()
            .setNext(new PriceValidator())
            .setNext(new AddressValidator())
            .validate(order);
    }
}

// 4. 观察者模式 - 订单事件
@EventListener
public void onOrderPaid(OrderPaidEvent event) {
    // 发送通知、更新库存、赠送积分等
}
```

---

### 场景2：用户权限系统

```java
// 1. 装饰模式 - 权限增强
@Component
public class PermissionDecorator {
    public void checkPermission(User user, String resource) {
        // 基础权限检查
        if (!hasBasicPermission(user, resource)) {
            throw new PermissionDeniedException();
        }
        
        // 装饰：IP白名单检查
        if (!isInWhiteList(user.getIp())) {
            throw new IpNotAllowedException();
        }
        
        // 装饰：时间段检查
        if (!isInAllowedTime()) {
            throw new TimeNotAllowedException();
        }
    }
}

// 2. 责任链模式 - 权限过滤链
@Component
public class PermissionFilterChain {
    public void filter(User user) {
        new LoginFilter()
            .setNext(new RoleFilter())
            .setNext(new ResourceFilter())
            .filter(user);
    }
}
```

---

### 场景3：消息通知系统

```java
// 1. 观察者模式 - 事件监听
@Component
public class NotificationService {
    @EventListener
    public void onUserRegister(UserRegisterEvent event) {
        sendSMS(event.getUser());
        sendEmail(event.getUser());
        sendPush(event.getUser());
    }
}

// 2. 模板方法模式 - 消息发送模板
public abstract class MessageSender {
    public final void send(Message message) {
        validate(message);
        connect();
        doSend(message);  // 抽象方法
        disconnect();
        log(message);
    }
    
    protected abstract void doSend(Message message);
}

// 3. 策略模式 - 不同渠道
public interface NotificationChannel {
    void notify(User user, String content);
}

@Component("sms")
public class SmsChannel implements NotificationChannel { }

@Component("email")
public class EmailChannel implements NotificationChannel { }

@Component("push")
public class PushChannel implements NotificationChannel { }
```

---

## 🔗 设计模式在开源框架中的应用

### Spring框架
| 模式 | 应用 |
|------|------|
| 单例模式 | Bean默认作用域 |
| 工厂模式 | BeanFactory、ApplicationContext |
| 代理模式 | AOP、@Transactional |
| 模板方法 | JdbcTemplate、RestTemplate |
| 观察者模式 | ApplicationEvent |
| 策略模式 | Resource加载策略 |
| 适配器模式 | HandlerAdapter |
| 装饰模式 | BeanWrapper |
| 责任链模式 | Filter Chain |

### MyBatis框架
| 模式 | 应用 |
|------|------|
| 建造者模式 | SqlSessionFactoryBuilder |
| 工厂模式 | SqlSessionFactory |
| 代理模式 | Mapper接口代理 |
| 模板方法 | BaseExecutor |
| 装饰模式 | Cache装饰器 |

### Tomcat
| 模式 | 应用 |
|------|------|
| 外观模式 | Request/Response封装 |
| 观察者模式 | 生命周期监听 |
| 责任链模式 | Pipeline、Valve |
| 模板方法 | LifecycleBase |

---

## 💡 学习建议

### 1. 理论与实践结合
- 先学习模式原理
- 再看框架源码
- 最后应用到项目

### 2. 从简单到复杂
- 先看单个模式的应用
- 再看模式的组合
- 最后理解整体架构

### 3. 调试源码
```java
// 在Spring源码中打断点
AbstractAutowireCapableBeanFactory.doCreateBean()
AbstractApplicationContext.refresh()
```

### 4. 画出类图
- 理解框架的类结构
- 识别使用的设计模式
- 加深理解

---

## 📖 推荐阅读

- 《Spring源码深度解析》
- 《MyBatis技术内幕》
- 《深入理解Java虚拟机》

---

**下一步**: 查看[设计模式组合示例](PATTERN_COMBINATIONS.md)

