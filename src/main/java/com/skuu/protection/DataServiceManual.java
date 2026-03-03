package com.skuu.protection;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * 非注解版：重试、熔断（不限流）。超时由 RestTemplate 的 connectTimeout/readTimeout 控制，不再使用 TimeLimiter。
 */
@Service
public class DataServiceManual {
    private static final Logger log = LoggerFactory.getLogger(DataServiceManual.class);

    private static final String INSTANCE_NAME = "externalDataServiceManual";

    private final RestTemplate restTemplate;
    private final Retry retry;
    private final CircuitBreaker circuitBreaker;

    public DataServiceManual(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.retry = createRetry();
        this.circuitBreaker = createCircuitBreaker();
    }

    private static Retry createRetry() {
        RetryConfig config = RetryConfig.<DataResponse>custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(1000))
            .build();
        return Retry.of(INSTANCE_NAME, config);
    }

    private static CircuitBreaker createCircuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50f)
            .minimumNumberOfCalls(5)
            .waitDurationInOpenState(Duration.ofSeconds(10))
            .slidingWindowSize(10)
            .permittedNumberOfCallsInHalfOpenState(3)
            .build();
        return CircuitBreaker.of(INSTANCE_NAME, config);
    }

    /**
     * 顺序：熔断 → 重试。超时依赖 RestTemplate 的 connectTimeout/readTimeout，重试都失败后熔断器记一次失败。
     */
    public DataResponse getDataFromExternal(String param) {
        String url = "https://httpbin.org/delay/2?param=" + param;
        Supplier<DataResponse> call = () -> {
            log.info("Calling external API with param: {}", param);
            try {
                return restTemplate.getForObject(url, DataResponse.class);
            } catch (Exception e) {
                log.error("External API call failed", e);
                throw e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e);
            }
        };

        Supplier<DataResponse> decorated = Decorators.ofSupplier(call)
            .withCircuitBreaker(circuitBreaker)
            .withRetry(retry)
            .decorate();

        try {
            return decorated.get();
        } catch (Exception e) {
            log.warn("Call failed: {}", e.getMessage());
            return fallbackToLocal(param, e);
        }
    }

    public DataResponse fallbackToLocal(String param, Exception ex) {
        log.warn("Falling back to local data due to: {}", ex.getMessage());
        return new DataResponse("safe_default_data_for_" + param, "local_fallback");
    }
}
