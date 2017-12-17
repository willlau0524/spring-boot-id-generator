package com.will.test;

import com.alibaba.dubbo.config.annotation.Service;
import com.will.test.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 5000)
public class DemoServiceImpl implements DemoService {


    @Autowired
    private JedisClient jedisClient;

    @Override
    public String sayHello(String str) {
        return str+"hahaha";
    }

    @Override
    public long getId(String biz) {
        try {
            return jedisClient.incr(biz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
