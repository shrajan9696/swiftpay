package transactiongateway.constants;

public final class RedisConstants {

    private RedisConstants() {
    }

    public static final String IDEMPOTENCY_PREFIX = "PAYMENT:";

    public static final long IDEMPOTENCY_TTL = 24L;

}