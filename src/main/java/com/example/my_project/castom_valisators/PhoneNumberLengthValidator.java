package com.example.my_project.castom_valisators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberLengthValidator implements ConstraintValidator<PhoneNumberLengthCheck, Long> {

    private int length;


    @Override
    public void initialize(PhoneNumberLengthCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if (aLong == null )
            return true;

        String phoneStr = aLong.toString();

        return phoneStr.length() == length;
    }
}
