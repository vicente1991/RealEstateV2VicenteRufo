package com.salesianostriana.dam.RealEstate.repository;

import com.salesianostriana.dam.RealEstate.model.Persona;
import com.salesianostriana.dam.RealEstate.model.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropietarioRepository extends JpaRepository<Propietario, UUID> {
}
