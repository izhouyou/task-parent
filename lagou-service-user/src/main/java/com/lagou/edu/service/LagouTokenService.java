package com.lagou.edu.service;

import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.pojo.Result;

import javax.servlet.http.HttpServletResponse;

public interface LagouTokenService {
    Integer register(String email, String password, String code);

    String selectOne(String token);

    boolean login(HttpServletResponse response, String email, String password);

    boolean isRegistered(String email);
}
