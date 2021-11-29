package com.salesianostriana.dam.RealEstate.dto.interesa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInteresadoDTO {

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String avatar;
}
