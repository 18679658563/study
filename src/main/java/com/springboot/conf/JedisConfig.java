package com.springboot.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-12-05
 * Time: 上午9:46
 */
@Configuration
@ConfigurationProperties(prefix = "myredis")
@Data
public class JedisConfig {

    private String host;

    private Integer port;

    private String password;

    private Integer timeout;

    private Integer poolMaxTotal;

    private Integer poolMaxIdle;

    private Integer poolMaxWait;

    @Bean
    public JedisPool jedisPoolFactory(){
        System.out.println("\n\n\n\n\n");
        System.out.println(host);
        System.out.println("\n\n\n\n\n");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(poolMaxTotal);
        jedisPoolConfig.setMaxIdle(poolMaxIdle);
        jedisPoolConfig.setMaxWaitMillis(poolMaxWait * 1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout*1000, password);
        return jedisPool;
    }

}
