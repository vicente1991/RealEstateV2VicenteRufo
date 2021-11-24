package com.salesianostriana.dam.RealEstate.dto.inmobiliaria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class GetInmobiliariaDTO {

    private UUID id;

    private String nombre,telefono,email;
    private List<String> viviendas;



}
