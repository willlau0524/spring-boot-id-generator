package com.will.test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import com.google.common.base.Strings;

import javax.management.MXBean;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Description
 * @author butterfly
 * @date 2017年8月3日 下午1:51:16
 */
@Configuration
@PropertySource(value = "classpath:jedis.properties")
@EnableConfigurationProperties(JedisProperties.class)// 开启属性注入,通过@autowired注入
@ConditionalOnClass(JedisClient.class) // 判断这个类是否在classpath中存在
public class JedisSpringConfig {

    /**
     * 属性配置对象
     */
    @Autowired
    private JedisProperties prop;


    /**
     * @Description
     *
     * @author butterfly
     * @return
     */
    @Bean(name = "jedisPool")
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(prop.getMaxTotal());
        config.setMaxIdle(prop.getMaxIdle());
        config.setMaxWaitMillis(prop.getMaxWait());
        config.setTestOnBorrow(prop.getTestOnBorrow());
        JedisPool jedisPool = null;
        if (!Strings.isNullOrEmpty(prop.getPassword())) {
            jedisPool = new JedisPool(config, prop.getHost(), prop.getPort(), prop.getTimeout(), prop.getPassword());
        } else {
            jedisPool = new JedisPool(config, prop.getHost(), prop.getPort(), prop.getTimeout());
        }
        return jedisPool;
    }
//    使用集群的方式构建  可以直接用JedisCluster来操作redis
//    @Bean(name="jedisCluster")
//    public JedisCluster jedisCluster(){
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        // 最大连接数
//        poolConfig.setMaxTotal(1);
//        // 最大空闲数
//        poolConfig.setMaxIdle(1);
//        poolConfig.setMaxWaitMillis(1000);
//
//        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
//        nodes.add(new HostAndPort("127.0.0.1", 6379));
//        JedisCluster cluster = new JedisCluster(nodes,poolConfig);
//        return  cluster;
//    }


    /**
     * @Description
     *
     * @author butterfly
     * @param pool
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(JedisClient.class) // 容器中如果没有RedisClient这个类,那么自动配置这个RedisClient
    public JedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
        JedisClient jedisClient = new JedisClient();
        jedisClient.setJedisPool(pool);
        return jedisClient;
    }
}
