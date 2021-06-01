package com.lagou.edu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author izhouy
 * @title SendEmailServiceImpl
 * @Decription 邮件发送
 * @CreateDate 2021/2/24 17:11
 */
@Service
@Slf4j
public class SendEmailService {

    /**发送人*/
    @Value("${email.from}")
    private String emailFrom;
    @Value("${email.nick}")
    private String emailNick;
    @Autowired
    private Session session;

    public boolean sendEmail(String recipient, String content) {
        try {
            // 创建MimeMessage实例对象
            Message message = new MimeMessage(session);
            // 设置发件人
            message.setFrom(new InternetAddress(emailFrom, emailNick));
            // 设置收信人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            // 设置邮件主题
            message.setSubject("注册验证码");
            //设置发送日期
            message.setSentDate(new Date());
            // 设置邮件正文
            message.setText("【拉勾教育】验证码：" + content + "(10分钟内有效)。\n如非本人操作，请忽略本邮件。\n提示：请勿泄露验证码给他人。");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
