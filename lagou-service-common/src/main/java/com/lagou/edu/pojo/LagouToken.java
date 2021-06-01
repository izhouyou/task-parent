package com.lagou.edu.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author izhouy
 * @title LagouToken
 * @Decription 令牌信息实体类
 * @CreateDate 2021/4/1 22:27
 */
@Data
@Entity
@Table(name = "lagou_token")
public class LagouToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String token;
    private String password;

}
