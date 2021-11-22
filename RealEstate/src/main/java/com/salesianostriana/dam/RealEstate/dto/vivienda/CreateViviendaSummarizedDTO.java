package com.salesianostriana.dam.RealEstate.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateViviendaSummarizedDTO {

    private String titulo, descripcion, avatar;
    private double precio;
    private int interesas;
}
