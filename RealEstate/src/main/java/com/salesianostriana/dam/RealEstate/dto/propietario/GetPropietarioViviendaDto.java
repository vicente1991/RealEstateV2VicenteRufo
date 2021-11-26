package com.salesianostriana.dam.RealEstate.dto.propietario;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class GetPropietarioViviendaDto {

    private UUID id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private List<String> direccionVivienda;
}
