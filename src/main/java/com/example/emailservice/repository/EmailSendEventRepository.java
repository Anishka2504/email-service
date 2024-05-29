package com.example.emailservice.repository;

import com.example.emailservice.entity.EmailSendEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSendEventRepository extends JpaRepository<EmailSendEvent, Long> {
}
