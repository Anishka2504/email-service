package com.example.emailservice.dto;

import com.example.emailservice.mapper.uses.DateTimeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ConfirmationTokenDto {

    private String uuidToken;
    private String dateCreated;

    public ConfirmationTokenDto(String uuidToken) {
        this.uuidToken = uuidToken;
        dateCreated = DateTimeMapper.localDateTime(LocalDateTime.now());
    }
}
