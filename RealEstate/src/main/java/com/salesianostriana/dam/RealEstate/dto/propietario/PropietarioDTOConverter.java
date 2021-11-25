package com.salesianostriana.dam.RealEstate.dto.propietario;

import com.salesianostriana.dam.RealEstate.model.Propietario;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropietarioDTOConverter {

    public GetPropietarioDTO2 PropietarioToGetPropietarioDTO2(UserEntity p){
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
                .avatar(p.getAvatar())
                .viviendasNombre(nombreVivienda)
                .build();

    }

    public GetPropietarioDTO propietarioToGetPropietarioDTO(UserEntity p) {

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
                .avatar(p.getAvatar())
                .viviendas(lista)
                .build();
    }

    public GetPropietarioViviendaDto propietarioToGetPropietarioViviendaDto(UserEntity p){

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
                .direccionVivienda(direccion)
                .build();
    }
}
