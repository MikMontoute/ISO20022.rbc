package com.rbc.iso20022.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.iso20022.entity.CustomerEntity;

public interface CustomerRepository
        extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity>
        findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
}