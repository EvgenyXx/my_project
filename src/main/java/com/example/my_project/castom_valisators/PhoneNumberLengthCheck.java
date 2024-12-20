package com.example.my_project.castom_valisators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberLengthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberLengthCheck {

    String message() default "Номер телефона должен иметь длину {length}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int length(); // Добавляем параметр length для указания желаемой длины
}
