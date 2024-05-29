//package com.example.emailservice.kafka;
//
//import com.example.emailservice.dto.EmailSendEventDto;
//import com.example.emailservice.dto.KafkaMessage;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@AllArgsConstructor
//public class EmailServiceConsumer {
//
//    private static final String EMAIL_SEND_TOPIC = "${spring.kafka.template.default-topic}";
//    private static final String CONSUMER_GROUP_ID = "${email-send}";
//
//    @KafkaListener(topics = EMAIL_SEND_TOPIC, groupId = CONSUMER_GROUP_ID)
//    public EmailSendEventDto createEmailSendEventDtoFromKafkaMessage(KafkaMessage message) {
//        log.info("Message consumed {}", message.getUuid());
//        return new EmailSendEventDto(message.getUuid(), message.getUserEmail());
//    }
//
//
//}
