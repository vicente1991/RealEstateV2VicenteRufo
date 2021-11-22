package com.salesianostriana.dam.RealEstate.dto.interesa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresaDTO {

    private String mensaje;
    private LocalDateTime createdDate;
}
