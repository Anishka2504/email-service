package com.example.emailservice.repository;

import com.example.emailservice.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByUuidToken(String uuidToken);
}
