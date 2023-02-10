package com.gymservice.web.mappers;


import com.gymservice.web.userAPI.CreateUserDto;
import com.gymservice.web.userAPI.User;
import com.gymservice.web.userAPI.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    User toUser(UserDto userDto);
    User toUser(CreateUserDto createUserDto);

    @Named("toUserDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    UserDto toUserDto(User user);

}
