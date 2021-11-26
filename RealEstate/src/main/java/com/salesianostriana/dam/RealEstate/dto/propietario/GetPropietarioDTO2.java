package com.salesianostriana.dam.RealEstate.dto.propietario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPropietarioDTO2 {

    private UUID id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String avatar;
    private List<String> viviendasNombre;




}
