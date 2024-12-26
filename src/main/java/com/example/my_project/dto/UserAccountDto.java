package com.example.my_project.dto;

import com.example.my_project.castom_valisators.PhoneNumberLengthCheck;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {


    private String name;

    @PhoneNumberLengthCheck(length = 11,message = "пример номера телефона 89181448199")

    private Long numberPhone;

    @Email(message = "не верный формат email")
    private String email;

    private BigDecimal purse;

    private LocalDate birthday;

    private Integer age;

    private String passport;
}
