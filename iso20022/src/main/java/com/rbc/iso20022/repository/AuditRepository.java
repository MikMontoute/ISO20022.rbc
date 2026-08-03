package com.rbc.iso20022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rbc.iso20022.entity.AuditEvent;



public interface AuditRepository 
extends JpaRepository<AuditEvent,Long>{

}
