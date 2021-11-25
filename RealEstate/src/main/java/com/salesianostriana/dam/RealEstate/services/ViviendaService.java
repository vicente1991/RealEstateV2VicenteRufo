package com.salesianostriana.dam.RealEstate.services;

import com.salesianostriana.dam.RealEstate.dto.vivienda.GetViviendaDTO;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.repository.ViviendaRepository;
import com.salesianostriana.dam.RealEstate.services.base.BaseService;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<Vivienda> findTop10ViviendaOrderByInteresas (){

        return repository.top5ViviendasInteresas();

    }

    public Vivienda saveVivienda(GetViviendaDTO getViviendaDTO, UserEntity user){
        Vivienda vivienda = Vivienda.builder()
                .id(getViviendaDTO.getId())
                .titulo(getViviendaDTO.getTitulo())
                .descripcion(getViviendaDTO.getDescripcion())
                .avatar(getViviendaDTO.getAvatar())
                .latlng(getViviendaDTO.getLatlng())
                .poblacion(getViviendaDTO.getPoblacion())
                .provincia(getViviendaDTO.getProvincia())
                .tipoVivienda(getViviendaDTO.getTipo())
                .precio(getViviendaDTO.getPrecio())
                .numHabitaciones(getViviendaDTO.getNumHabitaciones())
                .metrosCuadrados(getViviendaDTO.getMetrosCuadrados())
                .numBanios(getViviendaDTO.getNumBanios())
                .tienePiscina(getViviendaDTO.isTienePiscina())
                .tieneAscensor(getViviendaDTO.isTieneAscensor())
                .tieneGaraje(getViviendaDTO.isTieneGaraje())
                .propietario(user)
                .build();

        return save(vivienda);
    }

}