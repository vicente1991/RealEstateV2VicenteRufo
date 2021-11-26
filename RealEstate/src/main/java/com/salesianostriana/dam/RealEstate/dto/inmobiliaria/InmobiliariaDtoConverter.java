package com.salesianostriana.dam.RealEstate.dto.inmobiliaria;

import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InmobiliariaDtoConverter {

    public Inmobiliaria createInmobiliariaDtoToInmobiliaria(CreateInmobiliariaDTO c){
        return new Inmobiliaria(
          c.getNombre(),
          c.getEmail(),
          c.getTelefono()
        );
    }
    public GetInmobiliariaDTO getInmobiliariaToInmobiliariaDto(Inmobiliaria in){

        List<String> nombreVivienda = new ArrayList<>();
        for (int i=0; i<in.getViviendas().size();i++){
            nombreVivienda.add(in.getViviendas().get(i).getTitulo());
        }

        return GetInmobiliariaDTO
                .builder()
                .id(in.getId())
                .nombre(in.getNombre())
                .email(in.getEmail())
                .telefono(in.getTelefono())
                .viviendas(nombreVivienda)
                .build();

    }


}
