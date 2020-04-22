#### 20200226
feat: `sprinboot` 使用`jedis`操作`redis`
1. 使用 `RedisProperties` 统一获取配置
2. `jedisPool`作为`bean`注入
3. `JedisUtil`使用了`AutoCloseable`, 注意close操作

