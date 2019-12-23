package com.ly.oa.user.server.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.ly.oa.user.server.api.feign")
@ComponentScan(basePackages = {"com.ly.oa.user.server.edge", "com.ly.oa.user.server.api.feign"})
public class OAUserServerEdge {
    public static void main(String[] args) {
        SpringApplication.run(OAUserServerEdge.class, args);
    }
}
