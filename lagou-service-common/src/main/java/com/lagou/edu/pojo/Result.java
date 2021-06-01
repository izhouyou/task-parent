package com.lagou.edu.pojo;

import lombok.Data;

/**
 * @author izhouy
 * @title Result
 * @Decription 统一返回结果对象
 * @CreateDate 2021/4/6 14:49
 */
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;
    private String errMsg;

}
