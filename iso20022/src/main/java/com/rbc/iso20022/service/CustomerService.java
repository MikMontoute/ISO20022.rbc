package com.rbc.iso20022.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rbc.iso20022.entity.CustomerEntity;
import com.rbc.iso20022.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final AuditService auditService;

    public CustomerService(
            CustomerRepository repository,
            AuditService auditService) {

        this.repository = repository;
        this.auditService = auditService;
    }

    public void createOrUpdateCustomer(
            String customerName,
            String accountNumber,
            String bic,
            String customerType) {

        Optional<CustomerEntity> customerOpt =
                repository.findByAccountNumber(
                        accountNumber);

        if (customerOpt.isPresent()) {

            CustomerEntity customer =
                    customerOpt.get();

            customer.setCustomerName(
                    customerName);

            customer.setBic(
                    bic);

            customer.setLastUpdatedDate(
                    LocalDateTime.now());

            repository.save(customer);
            auditService.audit(
                    null,
                    null,
                    null,
                    "CUSTOMER_UPDATED",
                    customer.getAccountNumber(),
                    "SUCCESS",
                    null);

            return;
        }

        CustomerEntity customer =
                new CustomerEntity();

        customer.setCustomerName(
                customerName);

        customer.setAccountNumber(
                accountNumber);

        customer.setBic(
                bic);

        customer.setCustomerType(
                customerType);

        customer.setInstitutionType(
                customerType.equals("SENDER")
                        ? "FINANCIAL_INSTITUTION"
                        : "RECEIVING_INSTITUTION");

        customer.setCreatedDate(
                LocalDateTime.now());

        customer.setLastUpdatedDate(
                LocalDateTime.now());

        repository.save(customer);
        auditService.audit(
                null,
                null,
                null,
                "CUSTOMER_CREATED",
                customer.getAccountNumber(),
                "SUCCESS",
                null);
    }
}