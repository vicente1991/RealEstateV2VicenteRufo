package com.salesianostriana.dam.RealEstate.users.dto;

import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
        return GetUserDto.builder()
                .avatar(user.getAvatar())
                .fullName(user.getUsername())
                .email(user.getEmail())
                .role(user.getRol().name())
                .build();


    }

}
