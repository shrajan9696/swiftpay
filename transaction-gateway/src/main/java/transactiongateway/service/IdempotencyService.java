package transactiongateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import transactiongateway.constants.RedisConstants;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final RedisTemplate<String, String> redisTemplate;

    public boolean acquireTransaction(String transactionId) {

        String key = RedisConstants.IDEMPOTENCY_PREFIX + transactionId;

        Boolean success = redisTemplate
                .opsForValue()
                .setIfAbsent(
                        key,
                        "PROCESSING",
                        Duration.ofHours(RedisConstants.IDEMPOTENCY_TTL)
                );

        return Boolean.TRUE.equals(success);
    }


}