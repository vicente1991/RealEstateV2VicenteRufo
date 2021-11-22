package com.salesianostriana.dam.RealEstate.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {

    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String avatar;
    private String userRoles;


}
