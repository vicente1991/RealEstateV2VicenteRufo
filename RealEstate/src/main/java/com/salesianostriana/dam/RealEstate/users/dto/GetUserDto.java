package com.salesianostriana.dam.RealEstate.users.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private UUID id;
    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String avatar;
    private String userRoles;


}
