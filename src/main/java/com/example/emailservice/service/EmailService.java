package com.example.emailservice.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void confirmEmail(String token);
}
