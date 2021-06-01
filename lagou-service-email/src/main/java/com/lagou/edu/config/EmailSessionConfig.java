package com.lagou.edu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * @author izhouy
 * @title EmailSessionConfig
 * @Decription 邮件发送配置
 * @CreateDate 2021/3/4 16:35
 */
@Configuration
public class EmailSessionConfig {

    /**邮箱服务*/
    @Value("${email.host}")
    private String emailHost;
    /**用户名*/
    @Value("${email.username}")
    private String emailUsername;
    /**密码*/
    @Value("${email.password}")
    private String emailPassword;

    @Bean
    public Session session() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailHost);
        props.put("mail.smtp.port", "25");
        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailUsername, emailPassword);
                    }
                });
    }


}
