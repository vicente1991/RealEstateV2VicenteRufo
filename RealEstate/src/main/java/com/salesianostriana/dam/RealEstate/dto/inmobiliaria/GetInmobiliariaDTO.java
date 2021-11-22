package com.salesianostriana.dam.RealEstate.dto.inmobiliaria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class GetInmobiliariaDTO {

    private Long id;

    private String nombre,telefono,email;
    private List<String> viviendas;



}
