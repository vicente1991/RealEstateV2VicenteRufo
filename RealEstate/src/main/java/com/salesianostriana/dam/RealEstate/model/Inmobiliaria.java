package com.salesianostriana.dam.RealEstate.model;


import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraph(
        name = Inmobiliaria.INMOBILIARIA_CON_VIVIENDA,
        attributeNodes = {
                @NamedAttributeNode("viviendas")
        }
)
@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder
public class Inmobiliaria {

    public static final String INMOBILIARIA_CON_VIVIENDA= "grafo-inmobiliaria-con-vivienda";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity user;


    @Builder.Default
    @OneToMany(mappedBy = "inmobiliaria", fetch = FetchType.EAGER)

    private List<Vivienda> viviendas=new ArrayList<>();

    public Inmobiliaria(String nombre, String email, String telefono) {
    }

    @PreRemove
    public void removeGestorInmo(){
        this.user.setInmobiliaria(null);
    }

}
