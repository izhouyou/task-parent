package com.lagou.edu.service.impl;

import com.lagou.edu.feign.LagouAuthCodeFeignClient;
import com.lagou.edu.dao.LagouTokenDao;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.service.LagouTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * @author izhouy
 * @title LagouTokenServiceImpl
 * @Decription
 * @CreateDate 2021/4/6 16:18
 */
@Service
public class LagouTokenServiceImpl implements LagouTokenService {
    @Autowired
    LagouTokenDao lagouTokenDao;
    @Autowired
    LagouAuthCodeFeignClient lagouAuthCodeFeignClient;

    @Override
    public Integer register(String email, String password, String code) {
        // 校验code
        Integer validate = lagouAuthCodeFeignClient.validate(email, code);
        if(validate != 0){
            return validate;
        }
        // 保存token
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        lagouToken.setPassword(password);
        LagouToken save = lagouTokenDao.save(lagouToken);
        return 0;
    }

    @Override
    public String selectOne(String token) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setToken(token);
        Example<LagouToken> example = Example.of(lagouToken);
        Optional<LagouToken> optional = lagouTokenDao.findOne(example);
        if(!optional.isPresent()){
            return "";
        }
        return optional.get().getEmail();
    }

    @Override
    public boolean login(HttpServletResponse response, String email, String password) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        lagouToken.setPassword(password);
        Example<LagouToken> example = Example.of(lagouToken);
        Optional<LagouToken> optional = lagouTokenDao.findOne(example);
        if(optional.isPresent()){
            String token = UUID.randomUUID().toString();
            LagouToken addLagouToken = optional.get();
            addLagouToken.setToken(token);
            lagouTokenDao.save(addLagouToken);
            // 缓存到cookie中
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            // 返回
            response.addCookie(cookie);
            return true;
        }
        return false;
    }

    @Override
    public boolean isRegistered(String email) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        Example<LagouToken> example = Example.of(lagouToken);
        Optional<LagouToken> optional = lagouTokenDao.findOne(example);
        return optional.isPresent();
    }
}
