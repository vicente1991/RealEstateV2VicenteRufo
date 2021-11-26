package com.salesianostriana.dam.RealEstate.repository;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InteresaRepository extends JpaRepository<Interesa, Long> {

}
