package com.example.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KafkaMessage {

    private String uuid;
    private String userEmail;
}
