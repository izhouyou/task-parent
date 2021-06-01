package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author izhouy
 * @title LagouCodeApplication8112
 * @Decription 启动类
 * @CreateDate 2021/4/2 15:36
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LagouCodeApplication8112 {
    public static void main(String[] args) {
        SpringApplication.run(LagouCodeApplication8112.class, args);
    }
}
