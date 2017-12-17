package com.will.test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.xml.ws.soap.Addressing;

@Component
public class JedisClient {



    /**
     * @Description
     * @author butterfly
     * @date 2017年8月3日 下午1:51:16
    public class JedisClient {

        /**
         * 连接池对象
         */
        @Autowired
        private JedisPool jedisPool;

//        @Autowired
//        private JedisCluster jedisCluster;

        /**
         * @Description
         *
         * @author butterfly
         * @param key
         * @param value
         * @throws Exception
         */
        public void set(String key, String value) throws Exception {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.set(key, value);
            } catch (Exception ex) {
                throw ex;
            } finally {
                // 返还到连接池
                if (null != jedis)
                    jedis.close();
            }
        }

        /**
         * @Description 自增生成ID
         *
         * @author butterfly
         * @param key
         * @throws Exception
         */
        public Long incr(String key) throws Exception {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                Long index = jedis.incr(key);
                return index;
//                return jedisCluster.incr(key);
            } catch (Exception ex) {
                throw ex;
            } finally {
                // 返还到连接池
                if (null != jedis)
                    jedis.close();
            }
        }


        /**
         * @Description
         *
         * @author butterfly
         * @param key
         * @return
         * @throws Exception
         */
        public String get(String key) throws Exception {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                return jedis.get(key);
            } catch (Exception ex) {
                throw ex;
            } finally {
                // 返还到连接池
                if (null != jedis)
                    jedis.close();
            }
        }

        public JedisPool getJedisPool() {
            return jedisPool;
        }


        /**
         * @Description
         *
         * @author butterfly
         * @param jedisPool
         */
        public void setJedisPool(JedisPool jedisPool) {
            this.jedisPool = jedisPool;
        }
}
