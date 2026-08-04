package com.rbc.iso20022.validation;

import org.springframework.stereotype.Component;

import com.rbc.iso20022.exception.ValidationException;

@Component
public class HeaderValidator {

    public void validate(
            String requestId,
            String correlationId) {

        if (requestId == null ||
                requestId.isBlank()) {

            throw new ValidationException(
                    "Missing X-Request-ID");
        }

        if (correlationId == null ||
                correlationId.isBlank()) {

            throw new ValidationException(
                    "Missing X-Correlation-ID");
        }
    }
}