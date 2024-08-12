package org.example.stylish.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.stylish.exception.RateLimitExceededException;
import org.example.stylish.utils.RedisRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Log4j2
public class RatelimiterAop {

    @Autowired
    private RedisRateLimiter redisRateLimiter;

    @Before("within(org.example.stylish.controller..*)")
    public void checkRateLimit() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();

        if (!redisRateLimiter.isAllowed(ipAddress)) {
            log.warn("Rate limit reached for IP: {}", ipAddress);
            throw new RateLimitExceededException("Rate limit reached for IP: " + ipAddress);
        }
    }
}
