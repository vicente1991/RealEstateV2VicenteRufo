package com.salesianostriana.dam.RealEstate.users.dto;

import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String telefono;
    private String avatar;
    private String password;
    private String password2;
    private Inmobiliaria inmobiliaria;

}
