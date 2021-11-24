package com.salesianostriana.dam.RealEstate.services;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Propietario;
import com.salesianostriana.dam.RealEstate.repository.InteresaRepository;
import com.salesianostriana.dam.RealEstate.repository.PropietarioRepository;
import com.salesianostriana.dam.RealEstate.services.base.BaseService;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.repos.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PropietarioService  extends BaseService<UserEntity, UUID, UserEntityRepository> {
}
