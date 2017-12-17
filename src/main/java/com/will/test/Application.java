package com.will.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
@DubboComponentScan("com.will.test")
public class Application {

    public static void main(String[] args) throws IOException {

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
//        context.start();
//        System.in.read(); // 按任意键退出
        SpringApplication.run(Application.class);
        while(true){
            System.in.read(); // 按任意键退出
        }

        // 服务实现
//        DemoService demoService = new DemoServiceImpl();
//
//// 当前应用配置
//        ApplicationConfig application = new ApplicationConfig();
//        application.setName("xxx");
//
//// 连接注册中心配置
//        RegistryConfig registry = new RegistryConfig();
//        registry.setAddress("multicast://239.255.255.255:12345");
//
//// 服务提供者协议配置
//        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("dubbo");
//        protocol.setPort(12345);
//        protocol.setThreads(200);
//
//// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
//
//// 服务提供者暴露服务配置
//        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
//        service.setApplication(application);
//        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
//        service.setProtocol(protocol); // 多个协议可以用setProtocols()
//        service.setInterface(DemoService.class);
//        service.setRef(demoService);
//        service.setVersion("1.0.0");
//
//// 暴露及注册服务
//        service.export();
//        System.in.read(); // 按任意键退出
    }
}
