package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author izhouy
 * @title LagouGatewayApplication8130
 * @Decription 启动类
 * @CreateDate 2021/4/1 22:09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LagouGatewayApplication8130 {
    public static void main(String[] args) {
        SpringApplication.run(LagouGatewayApplication8130.class, args);
    }
}
