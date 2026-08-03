package com.rbc.iso20022.repository;

import com.rbc.iso20022.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface CustomerRepository 
extends JpaRepository<Customer,Long>{


Optional<Customer> 
findByCustomerName(String name);


}