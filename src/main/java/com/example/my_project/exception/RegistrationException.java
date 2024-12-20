package com.example.my_project.exception;

import lombok.Data;

import java.util.List;


public class RegistrationException extends RuntimeException{
    private final List<String>errors;

    public RegistrationException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
