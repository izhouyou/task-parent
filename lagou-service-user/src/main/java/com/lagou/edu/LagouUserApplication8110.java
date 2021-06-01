package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author izhouy
 * @title LagouUserApplication8110
 * @Decription 启动类
 * @CreateDate 2021/4/2 10:06
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.lagou.edu.pojo")
@EnableFeignClients
public class LagouUserApplication8110 {
    public static void main(String[] args) {
        SpringApplication.run(LagouUserApplication8110.class, args);
    }
}
