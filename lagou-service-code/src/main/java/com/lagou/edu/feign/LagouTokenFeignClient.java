package com.lagou.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("lagou-service-user")
@RequestMapping("/user")
public interface LagouTokenFeignClient {

    @GetMapping("/isRegistered/{email}")
    boolean isRegistered(@PathVariable("email") String email);

}
