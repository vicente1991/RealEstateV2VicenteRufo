package com.salesianostriana.dam.RealEstate.dto.interesa;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresadoDTO {

    private UUID id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;
    private String mensaje;
    private Date createdDate;

    public GetInteresadoDTO(Long id, String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
    }
}
