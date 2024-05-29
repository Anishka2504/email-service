package com.example.emailservice.mapper;

import com.example.emailservice.dto.EmailSendEventDto;
import com.example.emailservice.entity.EmailSendEvent;
import org.mapstruct.Mapper;

import javax.management.DescriptorAccess;

@Mapper(componentModel = "spring", uses = {DescriptorAccess.class})
public interface EmailSendEventMapper {

    EmailSendEventDto toDto(EmailSendEvent event);
    EmailSendEvent toEntity(EmailSendEventDto dto);
}
