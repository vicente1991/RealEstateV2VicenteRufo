package com.salesianostriana.dam.RealEstate.users.dto;

import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
        return GetUserDto.builder()
                .email(user.getEmail())
                .nombre(user.getNombre())
                .apellidos(user.getApellidos())
                .direccion(user.getDireccion())
                .avatar(user.getAvatar())
                .userRoles(user.getRol().name())
                .build();


    }

}
