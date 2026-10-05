package com.example.eventBooking.mapper;

import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    RegisterResponse toRegisterResponse(User user);
}