package com.example.emailservice.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(String email, String uuid) throws MessagingException;
}
