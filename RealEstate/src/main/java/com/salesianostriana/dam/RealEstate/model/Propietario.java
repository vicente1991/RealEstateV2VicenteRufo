package com.salesianostriana.dam.RealEstate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Propietario extends Persona{


    @Builder.Default
    @OneToMany(mappedBy = "propietario", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Vivienda> viviendas=new ArrayList<>();


    public Propietario(UUID id, String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
        super(id, nombre, apellidos, direccion, email, telefono, avatar);
    }

    public Propietario(String nombre, String apellidos, String email, String telefono, String avatar, List<Vivienda> viviendas) {
    }
}
