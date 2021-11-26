package com.salesianostriana.dam.RealEstate.dto.propietario;

import com.salesianostriana.dam.RealEstate.model.Vivienda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePropietarioDTO {

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String avatar;
    private List<Vivienda> viviendas;
}
