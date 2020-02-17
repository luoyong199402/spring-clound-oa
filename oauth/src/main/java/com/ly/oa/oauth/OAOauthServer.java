package com.ly.oa.oauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.ly.oa.user.server.api")
@ComponentScan(basePackages = {"com.ly.oa.oauth", "com.ly.oa.user.server.api"})
public class OAOauthServer {
	public static void main(String[] args) {
		SpringApplication.run(OAOauthServer.class, args);
	}
}
