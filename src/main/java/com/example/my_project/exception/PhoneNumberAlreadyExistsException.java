package com.example.my_project.exception;



public class PhoneNumberAlreadyExistsException extends RuntimeException{

    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
