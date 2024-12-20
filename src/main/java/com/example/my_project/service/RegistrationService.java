package com.example.my_project.service;

import com.example.my_project.dto.UserRegistrationDto;
import com.example.my_project.entities.Users;
import com.example.my_project.exception.RegistrationException;
import com.example.my_project.mappers.UserRegistrationMapper;
import com.example.my_project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Slf4j
@Service
public class RegistrationService  {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //сохранение пользователя в базу данных
    public UserRegistrationDto create (UserRegistrationDto users){
      List<String> error =  validateUserRegistration(users);
      if (!error.isEmpty()){
          throw new RegistrationException(error);
      }

        Users users1 = UserRegistrationMapper.INSTANCE.userRegistrationDtoToUsers(users);
        userRepository.save(users1);
        log.info("пользователь успешно сохранен: id№ {}",users1.getId());
        return UserRegistrationMapper.INSTANCE.usersToUserRegistrationDto(users1);
    }



    //проверка уникальности данных при регистрации пользователя
    List<String> validateUserRegistration(UserRegistrationDto users){
        List<String>error = new ArrayList<>();

        if (userRepository.existsByNumberPhone(users.getNumberPhone())){
            error.add("этот номер телефона уже зарегистрирован: " + users.getNumberPhone());
            log.error("этот номер телефона уже зарегистрирован: {}" , users.getNumberPhone());
        }
        if (userRepository.existsByEmail(users.getEmail())){
            log.error("Email '" + "{}" + "' уже зарегистрирован.",users.getEmail());
            error.add("Email '" + users.getEmail() + "' уже зарегистрирован.");

        }
        return error;

    }


}
