package com.lagou.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("lagou-service-user")
@RequestMapping("/user")
public interface LagouTokenClient {

    @GetMapping("/info/{token}")
    String selectToken(@PathVariable("token") String token);

}
