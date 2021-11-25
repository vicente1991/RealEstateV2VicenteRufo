package com.salesianostriana.dam.RealEstate.dto.vivienda;

import com.salesianostriana.dam.RealEstate.model.Tipo;
import com.salesianostriana.dam.RealEstate.users.dto.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateViviendaDTO {

    private String titulo, descripcion, avatar;
    private String provincia, poblacion, latlng;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private Tipo tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    private GetUserDto propietario;
    private String inmobiliaria;
}
