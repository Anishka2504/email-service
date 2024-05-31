package com.example.emailservice.mapper;

import com.example.emailservice.dto.ConfirmationTokenDto;
import com.example.emailservice.entity.ConfirmationToken;
import com.example.emailservice.mapper.uses.DateTimeMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ConfirmationTokenMapper {

    ConfirmationToken toEntity(ConfirmationTokenDto dto);

    ConfirmationTokenDto toDto(ConfirmationToken token);
}
