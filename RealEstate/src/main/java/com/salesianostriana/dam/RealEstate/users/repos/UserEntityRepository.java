package com.salesianostriana.dam.RealEstate.users.repos;

import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findFirstByEmail(String email);

}
