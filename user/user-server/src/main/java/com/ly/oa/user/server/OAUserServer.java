package com.ly.oa.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "com.ly.oa")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableWebSecurity
public class OAUserServer {
    public static void main(String[] args) {
        SpringApplication.run(OAUserServer.class, args);
    }
}
