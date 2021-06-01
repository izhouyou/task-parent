package com.lagou.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("lagou-service-code")
@RequestMapping("/code")
public interface LagouAuthCodeFeignClient {

    @GetMapping("/validate/{email}/{code}")
    Integer validate(@PathVariable("email") String email, @PathVariable("code") String code);
}
