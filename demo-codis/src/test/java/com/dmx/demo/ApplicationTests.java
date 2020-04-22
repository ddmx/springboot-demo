package com.dmx.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private JedisPool jedisPool;

    @Test
    void contextLoads() {
        String key = "aaaa";
        String value = "ccccc";
        try (Jedis jedis = jedisPool.getResource();) {
            jedis.set(key, value);
        }
    }
}
