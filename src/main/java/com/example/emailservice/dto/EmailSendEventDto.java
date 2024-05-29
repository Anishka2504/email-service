package com.example.emailservice.dto;

import com.example.emailservice.mapper.uses.DateTimeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class EmailSendEventDto {

    private Long id;
    private String userEmail;
    private String messageDate;
    private String messageUuid;

    public EmailSendEventDto(String messageUuid, String userEmail) {
        this.messageUuid = messageUuid;
        this.userEmail = userEmail;
        messageDate = DateTimeMapper.localDateTime(LocalDateTime.now());
    }
}
