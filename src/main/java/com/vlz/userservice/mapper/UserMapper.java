package com.vlz.userservice.mapper;

import com.vlz.userservice.dto.UserDto;
import com.vlz.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);
}