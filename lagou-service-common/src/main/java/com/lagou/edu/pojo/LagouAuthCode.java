package com.lagou.edu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author izhouy
 * @title LagouAuthCode
 * @Decription 用户信息实体类
 * @CreateDate 2021/4/1 22:30
 */
@Data
@Entity
@Table(name = "lagou_auth_code")
public class LagouAuthCode {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String code;
    private Date createtime;
    private Date expiretime;
}
