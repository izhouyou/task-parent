package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author izhouy
 * @title LagouEurekaApplication8121
 * @Decription 启动类
 * @CreateDate 2021/4/1 22:01
 */
@SpringBootApplication
@EnableEurekaServer
public class LagouEurekaApplication8121 {
    public static void main(String[] args) {
        SpringApplication.run(LagouEurekaApplication8121.class, args);
    }
}
