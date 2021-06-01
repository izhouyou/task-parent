package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author izhouy
 * @title LagouConfigApplication8140
 * @Decription 启动类
 * @CreateDate 2021/4/1 22:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class LagouConfigApplication8140 {
    public static void main(String[] args) {
        SpringApplication.run(LagouConfigApplication8140.class, args);
    }
}
