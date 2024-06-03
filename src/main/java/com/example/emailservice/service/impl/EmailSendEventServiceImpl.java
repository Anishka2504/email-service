package com.example.emailservice.service.impl;

import com.example.emailservice.dto.EmailSendEventDto;
import com.example.emailservice.dto.KafkaMessage;
import com.example.emailservice.mapper.EmailSendEventMapper;
import com.example.emailservice.repository.EmailSendEventRepository;
import com.example.emailservice.service.EmailSendEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSendEventServiceImpl implements EmailSendEventService {

    private final EmailSendEventRepository eventRepository;
    private final EmailSendEventMapper eventMapper;
    private final JavaMailSender mailSender;

    @Override
    @Transactional
    @KafkaListener(topics = EMAIL_SEND_TOPIC, groupId = CONSUMER_GROUP_ID)
    public EmailSendEventDto saveEvent(KafkaMessage kafkaMessage) {

        EmailSendEventDto eventDto = new EmailSendEventDto(kafkaMessage.getUuid(), kafkaMessage.getUserEmail());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(SUBJECT);
        mailMessage.setTo(eventDto.getUserEmail());
        mailMessage.setText(TEXT_MESSAGE + LINK + eventDto.getMessageUuid());
        try {
            mailSender.send(mailMessage);
            var savedEvent = eventMapper.toDto(eventRepository.save(eventMapper.toEntity(eventDto)));
            if (savedEvent != null) {
                log.info(String.format("Event %d is successfully saved in database", savedEvent.getId()));
            }
            return savedEvent;
        } catch (MailException exception) {
            log.error(String.format("Error! Activation message is not sent to email %s", kafkaMessage.getUserEmail()));
            return null;
        }
    }
}
