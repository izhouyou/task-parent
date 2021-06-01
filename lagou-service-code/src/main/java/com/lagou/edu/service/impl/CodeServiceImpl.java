package com.lagou.edu.service.impl;

import com.lagou.edu.dao.LagouAuthCodeDao;
import com.lagou.edu.feign.EmailFeignClient;
import com.lagou.edu.feign.LagouTokenFeignClient;
import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.pojo.Result;
import com.lagou.edu.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

/**
 * @author izhouy
 * @title CodeServiceImpl
 * @Decription 验证码业务类
 * @CreateDate 2021/4/6 14:54
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    EmailFeignClient emailFeignClient;
    @Autowired
    LagouAuthCodeDao lagouAuthCodeDao;
    @Autowired
    LagouTokenFeignClient lagouTokenFeignClient;


    @Override
    public boolean getCode(String email) {
        // 校验邮箱是否注册
        boolean registered = lagouTokenFeignClient.isRegistered(email);
        if(registered){
            return false;
        }
        // 新增
        boolean flag = false;
        // 验证邮箱
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(email);
        Example<LagouAuthCode> example = Example.of(lagouAuthCode);
        Optional<LagouAuthCode> lagouAuthCodeDaoOne = lagouAuthCodeDao.findOne(example);
        if(lagouAuthCodeDaoOne.isPresent()){
            LagouAuthCode authCode = lagouAuthCodeDaoOne.get();
            Date expiretime = authCode.getExpiretime();
            if(System.currentTimeMillis() < expiretime.getTime()){
                return true;
            } else {
                flag = true;
            }
        }
        // 获取6位随机验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        lagouAuthCode.setCode(String.valueOf(code));
        Date createDate = new Date();
        lagouAuthCode.setCreatetime(createDate);
        lagouAuthCode.setExpiretime(new Date(createDate.getTime()+60*10*1000));
        if(flag) {
            // 修改
            lagouAuthCode.setId(lagouAuthCodeDaoOne.get().getId());
        }
        // 保存数据
        LagouAuthCode save = lagouAuthCodeDao.save(lagouAuthCode);
        // 发送邮件
        boolean sendEmail = emailFeignClient.sendEmail(lagouAuthCode.getEmail(), lagouAuthCode.getCode());
        System.out.println("0发送成功,-1发送失败:"+sendEmail);
        return true;
    }

    @Override
    public Integer validate(String email, String code) {
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(email);
        lagouAuthCode.setCode(code);
        Example<LagouAuthCode> example = Example.of(lagouAuthCode);
        Optional<LagouAuthCode> lagouAuthCodeDaoOne = lagouAuthCodeDao.findOne(example);
        if(lagouAuthCodeDaoOne.isPresent()){
            LagouAuthCode authCode = lagouAuthCodeDaoOne.get();
            Date expiretime = authCode.getExpiretime();
            if(System.currentTimeMillis() < expiretime.getTime()){
                return 0;
            } else {
                return 2;
            }
        }
        return 1;
    }
}
