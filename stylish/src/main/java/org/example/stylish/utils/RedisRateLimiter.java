package org.example.stylish.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Log4j2
@Component
public class RedisRateLimiter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final int maxRequests = 10;
    private final Duration timeWindow = Duration.ofSeconds(1);

    public boolean isAllowed(String key) {
        String requestCountKey = "rate_limit:" + key;

        Long requestCount = redisTemplate.opsForValue().increment(requestCountKey);
        if (requestCount != null && requestCount == 1) {

            redisTemplate.expire(requestCountKey, timeWindow);
        }

        return requestCount <= maxRequests;
    }
}
