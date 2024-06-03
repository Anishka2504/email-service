package com.example.emailservice.service.impl;

import com.example.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${email.subject}")
    private String subject;
    @Value("${email.text}")
    private String text;
    @Value("${email.confirmation.url}")
    private String confirmationUrl;

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String email, String uuid) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setTo(email);
        mailMessage.setText(text + confirmationUrl + uuid);
        mailSender.send(mailMessage);
    }
}