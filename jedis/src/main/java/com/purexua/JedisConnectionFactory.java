package com.purexua;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
  private static final JedisPool jedisPool;

  static {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    //设置最大连接数
    poolConfig.setMaxTotal(8);
    //设置最大空闲数
    poolConfig.setMaxIdle(8);
    //设置最小空闲数
    poolConfig.setMinIdle(0);

    jedisPool = new JedisPool(poolConfig, "47.99.87.63", 6379, 1000, "123456");

  }

  public static Jedis getJedisPool() {
    return jedisPool.getResource();
  }
}
