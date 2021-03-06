package com.salesianostriana.dam.RealEstate.dto.vivienda;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
public class GetInmobiliariaViviendaDto extends GetViviendaDTO {

    private List<String> nombreVivienda;

    public GetInmobiliariaViviendaDto(Long id, String titulo, String descripcion, String avatar, String ubicacion, String provincia, int numHabitaciones, int numBanios, double metrosCuadrados, double precio, String tipo, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, String propietario, String inmobiliaria, List<String> nombreVivienda) {
        super(id,titulo,descripcion,avatar,ubicacion,provincia,numHabitaciones,numBanios,metrosCuadrados,precio,tipo,tienePiscina,tieneAscensor,tieneGaraje,propietario,inmobiliaria);
        this.nombreVivienda = nombreVivienda;
    }
}
