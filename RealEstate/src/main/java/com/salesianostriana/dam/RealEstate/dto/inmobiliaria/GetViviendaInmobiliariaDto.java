package com.salesianostriana.dam.RealEstate.dto.inmobiliaria;


import com.salesianostriana.dam.RealEstate.dto.vivienda.GetViviendaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder

public class GetViviendaInmobiliariaDto extends GetViviendaDTO {

    private Long idInmo;
    private String nombreInmo;

    public GetViviendaInmobiliariaDto(Long id, String titulo, String descripcion, String avatar, String ubicacion, String zona, int numHabitaciones, int numBanios, double metrosCuadrados, double precio, String tipo, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, String propietario, String inmobiliaria, String nombreInmo) {
        super(id, titulo, descripcion, avatar, ubicacion, zona, numHabitaciones, numBanios, metrosCuadrados, precio, tipo, tienePiscina, tieneAscensor, tieneGaraje, propietario, inmobiliaria);
        this.nombreInmo = nombreInmo;
    }
}
