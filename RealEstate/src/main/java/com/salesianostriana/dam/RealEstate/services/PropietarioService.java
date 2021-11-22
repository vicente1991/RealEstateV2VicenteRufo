package com.salesianostriana.dam.RealEstate.services;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Propietario;
import com.salesianostriana.dam.RealEstate.repository.InteresaRepository;
import com.salesianostriana.dam.RealEstate.repository.PropietarioRepository;
import com.salesianostriana.dam.RealEstate.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService  extends BaseService<Propietario, Long, PropietarioRepository> {
}
