package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author izhouy
 * @title LagouEmailApplication
 * @Decription 启动类
 * @CreateDate 2021/4/2 10:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LagouEmailApplication8111 {
    public static void main(String[] args) {
        SpringApplication.run(LagouEmailApplication8111.class,args);
    }
}
