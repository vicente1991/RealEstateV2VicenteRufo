package com.salesianostriana.dam.RealEstate.dto.vivienda;

import com.salesianostriana.dam.RealEstate.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetViviendaDTO2 {

    private String titulo, descripcion, avatar, latlng;
    private String direccion, poblacion, provincia;
    private String codigoPostal;
    private int numHabitaciones;
    private int numBanios;
    private double precio;
    private double metrosCuadrados;
    private Tipo tipoVivienda;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
}
