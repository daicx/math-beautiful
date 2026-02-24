package com.skuu.design.combination;

import com.skuu.design.combination.model.Order;
import com.skuu.design.combination.model.OrderItem;
import com.skuu.design.combination.proxy.OrderServiceProxy;
import com.skuu.design.combination.service.OrderService;
import com.skuu.design.combination.state.OrderContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dcx
 * @description 设计模式组合测试类
 * @create 2025-01-27
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=== 设计模式组合示例：订单处理系统 ===");
        System.out.println("本示例组合了7种设计模式");
        System.out.println("1. 工厂模式 - 创建订单");
        System.out.println("2. 建造者模式 - 构建订单对象");
        System.out.println("3. 策略模式 - 支付方式");
        System.out.println("4. 责任链模式 - 订单校验");
        System.out.println("5. 状态模式 - 订单状态流转");
        System.out.println("6. 观察者模式 - 事件通知");
        System.out.println("7. 代理模式 - (可扩展：缓存、日志)");

        // 创建订单服务（真实对象）
        OrderService realService = new OrderService();
        
        // 创建代理对象（添加缓存和日志功能）
        OrderServiceProxy orderService = new OrderServiceProxy(realService);
        
        System.out.println("\n💡 使用代理模式包装订单服务");
        System.out.println("   - 添加缓存功能");
        System.out.println("   - 添加日志功能");

        // 准备订单数据
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("P001", "iPhone 15 Pro", 1, new BigDecimal("7999")));
        items.add(new OrderItem("P002", "AirPods Pro", 1, new BigDecimal("1999")));

        // 测试1：使用支付宝支付
        System.out.println("\n" + repeatChar('=', 50));
        System.out.println("【测试1：使用支付宝支付】");
        System.out.println(repeatChar('=', 50));
        Order order1 = orderService.createAndPayOrder(
                "USER001",
                items,
                "北京市朝阳区xxx街道xxx号",
                "alipay"
        );

        // 测试2：使用微信支付
        System.out.println("\n\n" + repeatChar('=', 50));
        System.out.println("【测试2：使用微信支付】");
        System.out.println(repeatChar('=', 50));

        List<OrderItem> items2 = new ArrayList<>();
        items2.add(new OrderItem("P003", "MacBook Pro", 1, new BigDecimal("15999")));

        Order order2 = orderService.createAndPayOrder(
                "USER002",
                items2,
                "上海市浦东新区xxx路xxx号",
                "wechat"
        );

        // 测试3：代理模式 - 缓存测试
        System.out.println("\n\n" + repeatChar('=', 50));
        System.out.println("【测试3：代理模式 - 缓存功能测试】");
        System.out.println(repeatChar('=', 50));
        
        System.out.println("\n再次创建相同的订单（应该从缓存获取）:");
        Order cachedOrder = orderService.createAndPayOrder(
                "USER001",
                items,
                "北京市朝阳区xxx街道xxx号",
                "alipay"
        );
        
        System.out.println("\n缓存统计:");
        System.out.println("   缓存大小: " + orderService.getCacheSize());

        // 测试4：演示状态流转
        if (order1 != null) {
            System.out.println("\n\n" + repeatChar('=', 50));
            System.out.println("【测试4：演示订单状态流转】");
            System.out.println(repeatChar('=', 50));

            OrderContext context = new OrderContext(order1);
            System.out.println("\n当前状态: " + order1.getState().getStateName());

            System.out.println("\n尝试发货:");
            context.ship();

            System.out.println("\n当前状态: " + order1.getState().getStateName());
        }

        // 总结
        System.out.println("\n\n=== 设计模式组合优势 ===");
        System.out.println("✅ 每个模式解决特定问题，职责清晰");
        System.out.println("✅ 模式之间配合良好，相互增强");
        System.out.println("✅ 代码结构清晰，易于维护和扩展");
        System.out.println("✅ 符合SOLID原则和最佳实践");

        System.out.println("\n=== 模式在流程中的作用 ===");
        System.out.println("1. 工厂+建造者: 创建复杂订单对象");
        System.out.println("2. 责任链: 多个校验器串联，易于扩展");
        System.out.println("3. 策略: 支付方式灵活切换");
        System.out.println("4. 状态: 订单状态流转清晰可控");
        System.out.println("5. 观察者: 支付成功触发多个后续操作");
        System.out.println("6. 代理: 添加缓存和日志功能，不修改原有代码");
        
        System.out.println("\n=== 文件结构说明 ===");
        System.out.println("📁 model/      - 实体模型（建造者模式）");
        System.out.println("📁 state/      - 状态模式");
        System.out.println("📁 strategy/   - 策略模式");
        System.out.println("📁 validator/  - 责任链模式");
        System.out.println("📁 event/      - 观察者模式");
        System.out.println("📁 factory/    - 工厂模式");
        System.out.println("📁 proxy/      - 代理模式");
        System.out.println("📁 service/    - 服务层（组合所有模式）");
    }

    /**
     * 工具方法：重复字符
     */
    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}

