package com.will.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

public class ClientTest {

    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("yyy");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("multicast://239.255.255.255:12345");

// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

// 引用远程服务
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
        reference.setUrl("dubbo://127.0.0.1:20880/com.will.test.DemoService");

// 和本地bean一样使用xxxService
        DemoService service = reference.get();
        System.out.println(service.getId("aaaa"));
    }
}
