package com.example.my_project.exception;

import java.util.List;

public class PhoneNumberAlreadyExistsException extends RuntimeException{

    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
