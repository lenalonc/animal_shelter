package com.shelter.service;

import com.shelter.dtos.EmailData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(EmailData emailData) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailData.getToEmail());
        message.setSubject(emailData.getSubject());
        message.setText(emailData.getBody());

        mailSender.send(message);
    }
}