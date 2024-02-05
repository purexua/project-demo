import com.purexua.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class TestJedis {
  private Jedis jedis;

  @BeforeEach
  public void setUp() {
//    jedis = new Jedis("47.99.87.63", 6379);
    jedis = JedisConnectionFactory.getJedisPool();
    jedis.select(0);
  }

  @Test
  public void testString() {
    String result = jedis.set("name", "purexua!");
    System.out.println("result = " + result);

    String name = jedis.get("name");
    System.out.println("name = " + name);
  }

  @Test
  public void testHash() {
    jedis.hset("user", "name", "purexua");
    jedis.hset("user", "age", "18");
    jedis.hgetAll("user").forEach((k, v) -> {
      System.out.println(k + " = " + v);
    });
  }

  @AfterEach
  public void tearDown() {
    if (jedis != null) {
      jedis.close();
    }
  }


}
