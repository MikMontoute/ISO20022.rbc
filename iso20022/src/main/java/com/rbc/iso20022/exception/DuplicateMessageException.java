package com.rbc.iso20022.exception;

public class DuplicateMessageException
        extends RuntimeException {

    public DuplicateMessageException(String message) {
        super(message);
    }
}