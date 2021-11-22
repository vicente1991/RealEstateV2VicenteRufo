package com.salesianostriana.dam.RealEstate.dto.propietario;

import com.salesianostriana.dam.RealEstate.model.Propietario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropietarioDTOConverter {

    public GetPropietarioDTO2 PropietarioToGetPropietarioDTO2(Propietario p){
        List<String> nombreVivienda = new ArrayList<>();
        for (int i=0; i<p.getViviendas().size();i++){
            nombreVivienda.add(p.getViviendas().get(i).getTitulo());
        }
        return GetPropietarioDTO2
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .avatar(p.getAvatar())
                .viviendasNombre(nombreVivienda)
                .build();

    }

    public GetPropietarioDTO propietarioToGetPropietarioDTO(Propietario p) {

        List <String> lista = new ArrayList<>();

        for(int i = 0; i < p.getViviendas().size(); i++){

            lista.add(p.getViviendas().get(i).getTitulo());

        }
        return GetPropietarioDTO
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .avatar(p.getAvatar())
                .viviendas(lista)
                .build();
    }

    public GetPropietarioViviendaDto propietarioToGetPropietarioViviendaDto(Propietario p){

        List<String> direccion= new ArrayList<>();

        for (int i = 0;i<p.getViviendas().size();i++){
            direccion.add(p.getViviendas().get(i).getDireccion());
        }
        return GetPropietarioViviendaDto
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .direccionVivienda(direccion)
                .build();
    }
}
