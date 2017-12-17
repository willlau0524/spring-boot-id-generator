package com.will.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("provider-test");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("multicast://239.255.255.255:12345");
        return registryConfig;
    }

//    @Bean
//    public ProtocolConfig protocolConfig(){
//        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setName("dubbo");
//        protocolConfig.setPort(12345);
//        return protocolConfig;
//    }
}
