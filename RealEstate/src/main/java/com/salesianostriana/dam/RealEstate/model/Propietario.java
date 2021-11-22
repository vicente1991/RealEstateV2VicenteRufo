package com.salesianostriana.dam.RealEstate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    public Propietario(Long id, String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
        super(id, nombre, apellidos, direccion, email, telefono, avatar);
    }

    public Propietario(String nombre, String apellidos, String email, String telefono, String avatar, List<Vivienda> viviendas) {
    }
}
