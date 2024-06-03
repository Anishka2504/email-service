package com.example.emailservice.service.impl;

import com.example.emailservice.dto.EmailSendEventDto;
import com.example.emailservice.dto.KafkaMessage;
import com.example.emailservice.mapper.EmailSendEventMapper;
import com.example.emailservice.repository.EmailSendEventRepository;
import com.example.emailservice.service.EmailSendEventService;
import com.example.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSendEventServiceImpl implements EmailSendEventService {

    private final EmailSendEventRepository eventRepository;
    private final EmailSendEventMapper eventMapper;
    private final EmailService emailService;

    @Override
    @Transactional
    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public EmailSendEventDto saveEvent(KafkaMessage kafkaMessage) {

        EmailSendEventDto eventDto = new EmailSendEventDto(kafkaMessage.getUuid(), kafkaMessage.getUserEmail());
        try {
            emailService.sendEmail(kafkaMessage.getUserEmail(), kafkaMessage.getUuid());
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
