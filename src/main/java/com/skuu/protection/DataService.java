package com.skuu.protection;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author dcx
 * @description
 * @create 2025-12-01 18:39
 **/
@Service
public class DataService {
    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    private final RestTemplate restTemplate;

    public DataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 主方法：先尝试调用外部接口，失败则降级到本地,需要：spring-boot-starter-aop
     */
    @CircuitBreaker(name = "externalDataService", fallbackMethod = "fallbackToLocal")
    @Retry(name = "externalDataService", fallbackMethod = "fallbackToLocal")
    public DataResponse getDataFromExternal(String param) {
        log.info("Calling external API with param: {}", param);
        try {
            // 调用外部接口（假设返回 JSON）
            return restTemplate.getForObject(
                    "https://httpbin1.org/delay/21?param=" + param, // 模拟延迟
                    DataResponse.class
            );
        } catch (Exception e) {
            log.error("External API call failed", e);
            throw e; // 抛出异常触发 Retry/CB
        }
    }

    /**
     * 降级方法：必须和主方法参数一致 + 最后一个参数是 Exception
     */
    public DataResponse fallbackToLocal(String param, Exception ex) {
        log.warn("Falling back to local data due to: {}", ex.getMessage());

        // 本地降级逻辑：可以查缓存、数据库、返回默认值等
        return new DataResponse("safe_default_data_for_" + param, "local_fallback");
    }
}
