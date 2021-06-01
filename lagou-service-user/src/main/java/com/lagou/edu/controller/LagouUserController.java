package com.lagou.edu.controller;

import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.service.LagouTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author izhouy
 * @title LagouAuthCodeController
 * @Decription 用户接口
 * @CreateDate 2021/4/2 10:12
 */
@RestController
@RequestMapping("/user")
public class LagouUserController {

    @Autowired
    private LagouTokenService lagouTokenService;

    /**
     * 注册接口
     *
     * @param email
     * @param password
     * @param code
     * @return
     */
    @GetMapping("/register/{email}/{password}/{code}")
    public Integer register(@PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("code") String code) {
        return lagouTokenService.register(email, password, code);
    }

    /**
     * 根据token查询⽤户登录邮箱接⼝
     *
     * @param token
     * @return
     */
    @GetMapping("/info/{token}")
    public String selectOne(@PathVariable("token") String token) {
        return lagouTokenService.selectOne(token);
    }

    /**
     * 登录接⼝
     *
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/login/{email}/{password}")
    public boolean login(HttpServletResponse response, @PathVariable String email, @PathVariable String password) {
        return lagouTokenService.login(response, email, password);
    }

    /**
     * 是否已注册，根据邮箱判断
     *
     * @param email
     * @return
     */
    @GetMapping("/isRegistered/{email}")
    public boolean isRegistered(@PathVariable String email) {
        return lagouTokenService.isRegistered(email);
    }

}
