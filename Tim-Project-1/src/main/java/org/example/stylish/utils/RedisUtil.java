package org.example.stylish.utils;

import io.lettuce.core.RedisCommandTimeoutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

@Log4j2
@Configuration
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public <V> void set(String key, V value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (RedisCommandTimeoutException | QueryTimeoutException timoutException) {
            log.error("Set command timeout");
        } catch (RedisConnectionFailureException | RedisSystemException rcfe) {
            log.error("Redis connection failed", rcfe);
        }
    }

    public <V> V get(String key) {
        try {
            return (V) redisTemplate.opsForValue().get(key);
        } catch (RedisCommandTimeoutException | QueryTimeoutException timeoutException) {
            log.error("Get command timeout");
            return null;
        } catch (RedisConnectionFailureException | RedisSystemException rcfe) {
            log.error("Redis connection failed");
            return null;
        }
    }


    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (NullPointerException npe) {
            return false;
        } catch (RedisCommandTimeoutException | QueryTimeoutException timeoutException) {
            log.error("Command timeout", timeoutException);
            return false;
        } catch (RedisConnectionFailureException rcfe) {

            log.error("Redis connection failed", rcfe);
            return false;
        }
    }

    public boolean delete(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (NullPointerException npe) {
            return false;
        } catch (RedisCommandTimeoutException | QueryTimeoutException timeoutException) {
            log.error("Command timeout", timeoutException);
            return false;
        } catch (RedisConnectionFailureException rcfe) {

            log.error("Redis connection failed", rcfe);
            return false;
        }
    }

    public void addToStream(String streamKey, Map<String, Object> fields) {
        redisTemplate.opsForStream().add(
            StreamRecords.newRecord()
                .in(streamKey)
                .ofMap(fields)
        );
    }
}
