package com.example.my_project.dto;

import com.example.my_project.castom_valisators.PhoneNumberLengthCheck;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private Long id;

    @NotEmpty(message = "имя обязательно для заполнения")
    private String name;

    @PhoneNumberLengthCheck(length = 11,message = "пример номера телефона 89181448199")
    @NotNull(
            message = "номер телефона  обязателен  для заполнения")
    private Long numberPhone;

    @Email(message = "не верный формат email")
    @NotEmpty(message = "email обязателен для заполнения")
    private String email;


}
