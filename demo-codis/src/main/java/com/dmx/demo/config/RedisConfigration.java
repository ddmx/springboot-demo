package com.dmx.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfigration {
    private static final Logger log = LoggerFactory.getLogger(RedisConfigration.class);

    private final RedisProperties redisProperties;

    public RedisConfigration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(),
                redisProperties.getPort(), (int) redisProperties.getTimeout().toMillis(), redisProperties.getPassword());
        log.info("JedisPool init success!");
        return jedisPool;
    }
}
