package com.example.my_project.service;

import com.example.my_project.dto.UserAccountDto;
import com.example.my_project.dto.UserRegistrationDto;
import com.example.my_project.entities.Users;
import com.example.my_project.exception.RegistrationException;
import com.example.my_project.mappers.UserAccountMapper;
import com.example.my_project.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserAccountService {

    private final UserRepository userRepository;

    public UserAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserAccountDto updateUser (UserAccountDto userAccountDto,Long id){
        return userRepository.findById(id).map(users -> {
            List<String>message = validateUserUpdate(userAccountDto,users);
            if (!message.isEmpty()){
                throw new RegistrationException(message);
            }
            UserAccountMapper.INSTANCE.updateUserFromDto(userAccountDto,users);
            var saveUser = userRepository.save(users);

            return UserAccountMapper.INSTANCE.usersToUserAccountDto(saveUser);

        }).orElseThrow(()-> new RuntimeException("пользователь не найден"));
    }

    // Изменяем валидацию, чтобы она проверяла новые данные
    public List<String> validateUserUpdate(UserAccountDto updateUserDto, Users existingUser) {
        List<String> errors = new ArrayList<>();

        if (updateUserDto.getNumberPhone() != null && !updateUserDto.getNumberPhone().equals(existingUser.getNumberPhone()) && userRepository.existsByNumberPhone(updateUserDto.getNumberPhone())) {
            errors.add("Этот номер телефона уже зарегистрирован: " + updateUserDto.getNumberPhone());
            log.error("Этот номер телефона уже зарегистрирован: {}", updateUserDto.getNumberPhone());
        }

        if(updateUserDto.getEmail() != null && !updateUserDto.getEmail().equals(existingUser.getEmail()) && userRepository.existsByEmail(updateUserDto.getEmail())) {
            log.error("Email '{}' уже зарегистрирован.", updateUserDto.getEmail());
            errors.add("Email '" + updateUserDto.getEmail() + "' уже зарегистрирован.");
        }

        return errors;
    }
}
