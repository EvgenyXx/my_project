package com.example.my_project.mappers;

import com.example.my_project.dto.UserRegistrationDto;
import com.example.my_project.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    Users userRegistrationDtoToUsers(UserRegistrationDto userRegistrationDto);


    UserRegistrationDto usersToUserRegistrationDto(Users users);
}
