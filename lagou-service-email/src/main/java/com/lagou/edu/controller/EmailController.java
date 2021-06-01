package com.lagou.edu.controller;

import com.lagou.edu.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author izhouy
 * @title SendEmailController
 * @Decription 邮件发送接口
 * @CreateDate 2021/2/24 17:03
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    SendEmailService sendEmailService;

    @GetMapping("/{email}/{code}")
    public boolean sendEmail(@PathVariable("email") String recipient, @PathVariable("code") String content){
        return sendEmailService.sendEmail(recipient, content);
    }

}
