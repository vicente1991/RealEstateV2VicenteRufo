package com.salesianostriana.dam.RealEstate.dto.interesa;

import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class InteresadoDTOConverter {


    public UserEntity create(GetInteresadoDTO i){
        return UserEntity.builder()
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .viviendas(i.getViviendas())
                .build();
    }
}
