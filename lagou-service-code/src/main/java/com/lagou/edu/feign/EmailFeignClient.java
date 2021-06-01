package com.lagou.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lagou-service-email")
@RequestMapping("/email")
public interface EmailFeignClient {

    @GetMapping("/{email}/{code} ")
    boolean sendEmail(@PathVariable("email") String email, @PathVariable("code") String code);

}
