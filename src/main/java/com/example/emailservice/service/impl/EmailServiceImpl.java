package com.example.emailservice.service.impl;

import com.example.emailservice.dto.ConfirmationTokenDto;
import com.example.emailservice.mapper.ConfirmationTokenMapper;
import com.example.emailservice.repository.ConfirmationTokenRepository;
import com.example.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final ConfirmationTokenRepository tokenRepository;
    private final ConfirmationTokenMapper tokenMapper;

    @Override
    public void confirmEmail(String token) {
        ConfirmationTokenDto confirmationTokenDto = tokenMapper.toDto(tokenRepository.findByUuidToken(token));
        if (confirmationTokenDto != null) {

        }

    }
}
