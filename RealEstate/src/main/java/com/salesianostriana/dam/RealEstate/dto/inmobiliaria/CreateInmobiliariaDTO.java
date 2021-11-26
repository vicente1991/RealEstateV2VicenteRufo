package com.salesianostriana.dam.RealEstate.dto.inmobiliaria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor

public class CreateInmobiliariaDTO {

    private Long id;

    private String nombre,telefono,email;
    private List<String> viviendas;

}
