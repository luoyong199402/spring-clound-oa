package com.ly.oa.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "com.ly.oa")
@EnableDiscoveryClient
@EnableFeignClients
public class OAUserServer {
    public static void main(String[] args) {
        SpringApplication.run(OAUserServer.class, args);
    }
}
