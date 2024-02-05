import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LettuceTest {

  private RedisClient redisClient;
  private StatefulRedisConnection<String, String> connection;
  private RedisCommands<String, String> syncCommands;

  @BeforeEach
  public void setUp() {
    RedisURI redisURI = RedisURI.builder()
        .withHost("47.99.87.63").withPort(6379)
        .withPassword("123456")
        .build();

    redisClient = RedisClient.create(redisURI);
    connection = redisClient.connect();
    syncCommands = connection.sync();
  }

  @Test
  public void testString() {
    // set key-value
    syncCommands.set("testKey", "testValue");

    // get value
    String value = syncCommands.get("testKey");
    assertEquals("testValue", value);

    // delete key
    Long deleted = syncCommands.del("testKey");
    assertEquals(1, deleted);
  }

  @AfterEach
  public void tearDown() {
    connection.close();
    redisClient.shutdown();
  }
}
