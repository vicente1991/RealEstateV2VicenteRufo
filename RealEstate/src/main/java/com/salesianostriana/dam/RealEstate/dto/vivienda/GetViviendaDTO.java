package com.salesianostriana.dam.RealEstate.dto.vivienda;

import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate.model.Tipo;
import com.salesianostriana.dam.RealEstate.users.dto.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder
public class GetViviendaDTO {

    private String titulo, descripcion, avatar;
    private String poblacion, provincia, latlng;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private Tipo tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    private GetUserDto propietario;
    private Inmobiliaria inmobiliaria;



    public GetViviendaDTO( String titulo, String descripcion, String avatar, String ubicacion, String zona, int numHabitaciones, int numBanios, double metrosCuadrados, double precio, String tipo, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, String propietario, String inmobiliaria) {
    }
}
