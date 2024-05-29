package com.example.emailservice.service;

import com.example.emailservice.dto.EmailSendEventDto;
import com.example.emailservice.dto.KafkaMessage;

public interface EmailSendEventService {

    EmailSendEventDto saveEvent(KafkaMessage kafkaMessage);
}
