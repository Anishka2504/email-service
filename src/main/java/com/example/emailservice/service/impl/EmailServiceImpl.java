package com.example.emailservice.service.impl;

import com.example.emailservice.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${email.subject}")
    private String subject;
    @Value("${email.encoding}")
    private String encoding;
    @Value("${email.confirmation.url}")
    private String confirmationUrl;

    private static final String VERIFICATION_URL_PARAM = "verificationURL";
    private static final String TEMPLATE_NAME = "confirm-email-template";

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendEmail(String email, String uuid) {
        Context context = new Context();
        context.setVariable(VERIFICATION_URL_PARAM, confirmationUrl + uuid);
        String htmlContent = templateEngine.process(TEMPLATE_NAME, context);

        mailSender.send(message -> {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, encoding);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(htmlContent, true);
        });
    }
}