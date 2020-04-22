package com.dmx.demo.config;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisUtil implements AutoCloseable {

    private Jedis jedis;

    private final JedisPool jedisPool;
    public JedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Jedis getJedis() {
        jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 注意: 每次使用完jedis, 都需要关闭连接
     */
    @Override
    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }

    public void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
