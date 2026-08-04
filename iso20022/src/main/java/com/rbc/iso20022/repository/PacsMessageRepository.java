package com.rbc.iso20022.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.iso20022.entity.PacsMessageEntity;

public interface PacsMessageRepository
        extends JpaRepository<PacsMessageEntity, Long> {

    boolean existsByMessageId(String messageId);

    Optional<PacsMessageEntity> findByMessageId(String messageId);
}