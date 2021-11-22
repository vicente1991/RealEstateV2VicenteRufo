package com.salesianostriana.dam.RealEstate.services;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.repository.ViviendaRepository;
import com.salesianostriana.dam.RealEstate.services.base.BaseService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<Vivienda> findTop10ViviendaOrderByInteresas (){

        return repository.top5ViviendasInteresas();

    }

}