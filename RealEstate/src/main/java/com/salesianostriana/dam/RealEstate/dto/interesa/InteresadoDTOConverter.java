package com.salesianostriana.dam.RealEstate.dto.interesa;


import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Interesado;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InteresadoDTOConverter {

    public Interesado a(GetInteresadoDTO i) {
        return Interesado.builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .direccion(i.getDireccion())
                .avatar(i.getAvatar())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .build();
    }

    public Interesa b(GetInteresadoDTO i) {
        return Interesa.builder()
                .createdDate(LocalDateTime.now())
                .mensaje(i.getMensaje())
                .build();
    }
    


}
