package com.salesianostriana.dam.RealEstate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class Interesado extends Persona{

    @Builder.Default
    @OneToMany(mappedBy = "interesado")
    private List<Interesa> interesas = new ArrayList<>();


    public Interesado(String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
    }


    public void removeInteresa(List<Interesa>  i) {
        this.getInteresas().remove(i);

    }

    @PreRemove
    public void preRemove(){
        interesas.forEach(v -> v.setInteresado(null));
    }




}
