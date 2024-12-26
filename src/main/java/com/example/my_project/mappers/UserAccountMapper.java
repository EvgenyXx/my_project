package com.example.my_project.mappers;

import com.example.my_project.dto.UserAccountDto;
import com.example.my_project.entities.Users;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);

    Users userAccountDtoToUsers(UserAccountDto userAccountDto);

    UserAccountDto usersToUserAccountDto(Users users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserAccountDto userAccountDto, @MappingTarget Users users);
}
