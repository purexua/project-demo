package com.purexua;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.purexua.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringDataRedisDemoApplicationTests {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  void testString() {
    redisTemplate.opsForValue().set("name", "purexua!");
    Object name = redisTemplate.opsForValue().get("name");
    System.out.println("name = " + name);
  }

  @Test
  void testString1() {
    stringRedisTemplate.opsForValue().set("name", "purexua!");
    Object name = stringRedisTemplate.opsForValue().get("name");
    System.out.println("name = " + name);
  }

  @Test
  void testUser() {
    User user = new User("purexua", 20, 1);
    redisTemplate.opsForValue().set("user", user);
    User user1 = (User) redisTemplate.opsForValue().get("user");
    System.out.println("user1 = " + user1);
  }

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void testUser1() throws Exception {
    User user = new User("purexua", 20, 1);
    String userStr = objectMapper.writeValueAsString(user);
    stringRedisTemplate.opsForValue().set("user", userStr);
    String user1Str = stringRedisTemplate.opsForValue().get("user");
    User user1 = objectMapper.readValue(user1Str, User.class);
    System.out.println("user1 = " + user1);
  }

  private static final Gson gson = new GsonBuilder().serializeNulls().create();

  @Test
  void testUser2() {
    User user = new User("purexua", 20, null);
    String userStr = gson.toJson(user);
    stringRedisTemplate.opsForValue().set("user", userStr);
    String user1Str = stringRedisTemplate.opsForValue().get("user");
    User user1 = gson.fromJson(user1Str, User.class);
    System.out.println("user1 = " + user1);
  }

  @Test
  void testHash() {
    stringRedisTemplate.opsForHash().put("user", "name", "徐健豪");
    stringRedisTemplate.opsForHash().put("user", "age", "20");

    Object name = stringRedisTemplate.opsForHash().get("user", "name");
    stringRedisTemplate.opsForHash().entries("user").forEach((k, v) -> {
      System.out.println(k + " = " + v);
    });
  }


}
