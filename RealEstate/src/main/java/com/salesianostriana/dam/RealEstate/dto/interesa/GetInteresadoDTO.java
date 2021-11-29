package com.salesianostriana.dam.RealEstate.dto.interesa;

import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresadoDTO extends UserEntity {

    private String mensaje;
    private LocalDateTime createdDate;
}
