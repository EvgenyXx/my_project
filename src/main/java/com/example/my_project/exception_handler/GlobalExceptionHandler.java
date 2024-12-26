package com.example.my_project.exception_handler;

import com.example.my_project.exception.NotFoundException;
import com.example.my_project.exception.RegistrationException;
import com.example.my_project.exception.UniqueBrandException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice

public class GlobalExceptionHandler  {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Map<String,Object>>registrationException(RegistrationException e){
      Map<String,Object>errors = new HashMap<>();

      errors.put("time: ",new Date().toString());
      errors.put("status: ", HttpStatus.CONFLICT);
      errors.put("message: ", e.getErrors());

      return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>validException(MethodArgumentNotValidException e){
        Map<String,Object>message = new HashMap<>();
        List<String>error = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError:: getDefaultMessage)
                .toList();
        message.put("time",new Date().toString());
        message.put("status",HttpStatus.BAD_REQUEST);
        message.put("message",error);
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>>runtime(RuntimeException e){
        Map<String,Object>error = new HashMap<>();

        error.put("message",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>>notFoundException(NotFoundException e){
        Map<String,Object>message = new HashMap<>();
        message.put("time",new Date().toString());
        message.put("message",e.getMessage());
        message.put("status",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueBrandException.class)
    public ResponseEntity<Map<String,Object>>uniqueBrandException(UniqueBrandException e){
        Map<String,Object>message = new HashMap<>();
        message.put("time",new Date().toString());
        message.put("message",e.getMessage());
        message.put("status",HttpStatus.CONFLICT);
        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }






}
