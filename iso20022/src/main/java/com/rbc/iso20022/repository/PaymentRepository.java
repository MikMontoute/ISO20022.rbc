package com.rbc.iso20022.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.rbc.iso20022.entity.PaymentTransaction;


public interface PaymentRepository 
extends JpaRepository<PaymentTransaction,Long>{


boolean existsByMessageId(String id);


}
