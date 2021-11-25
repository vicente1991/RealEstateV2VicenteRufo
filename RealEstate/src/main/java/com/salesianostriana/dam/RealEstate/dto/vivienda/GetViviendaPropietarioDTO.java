package com.salesianostriana.dam.RealEstate.dto.vivienda;

import com.salesianostriana.dam.RealEstate.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetViviendaPropietarioDTO {


    private UUID id;
    private String titulo, descripcion, avatar;
    private String direccion, codigoPostal, provincia, poblacion, latlng;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private Tipo tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String avatar2;
}
