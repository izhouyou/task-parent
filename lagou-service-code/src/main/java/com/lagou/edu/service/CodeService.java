package com.lagou.edu.service;


public interface CodeService {

    boolean getCode(String email);

    Integer validate(String email, String code);
}
