package com.lagou.edu.controller;

import com.lagou.edu.feign.EmailFeignClient;
import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.pojo.Result;
import com.lagou.edu.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author izhouy
 * @title CodeController
 * @Decription 验证码接口类
 * @CreateDate 2021/4/6 14:45
 */
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    CodeService codeService;

    @GetMapping("/create/{email}")
    public boolean getCode(@PathVariable("email") String email){
        return codeService.getCode(email);
    }

    @GetMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable("email") String email, @PathVariable("code") String code){
        return codeService.validate(email, code);
    }
}
