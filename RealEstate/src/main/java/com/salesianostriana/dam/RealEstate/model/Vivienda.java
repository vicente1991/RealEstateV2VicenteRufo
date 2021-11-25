package com.salesianostriana.dam.RealEstate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder

public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo, descripcion, avatar, latlng;
    private String direccion, poblacion, provincia;

    @Column(name = "codigoPostal")
    private String codigoPostal;

    @Column(name = "numHabitaciones")
    private int numHabitaciones;

    @Column(name = "numBanios")
    private int numBanios;
    private double precio;
    private double metrosCuadrados;

    @Column(name = "tipoVivienda")
    @Enumerated(EnumType.STRING)
    private Tipo tipoVivienda;

    @Column(name = "tienePiscina")
    private boolean tienePiscina;

    @Column(name = "tieneAscensor")
    private boolean tieneAscensor;

    @Column(name = "tieneGaraje")
    private boolean tieneGaraje;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria_id", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"), nullable = true)
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    @JoinColumn(name = "propietario_id", foreignKey = @ForeignKey(name = "FK_VIVIENDA_PROPIETARIO"))
    @JsonIgnore
    private UserEntity propietario;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda")
    private List<Interesa> interesas = new ArrayList<>();


 
    public Vivienda(String titulo, String descripcion, double precio, int interesas) {
    }

    public Vivienda(String titulo, String descripcion, double precio) {
    }

    public Vivienda(String titulo, String avatar, Tipo tipo, double precio, String ubicacion, double metrosCuadrados, int numBanios, int numHabitaciones, boolean tieneAscensor, boolean tieneGaraje, boolean tienePiscina, String propietario, String inmobiliaria) {
    }

    public Vivienda(String titulo, String descripcion, String avatar, double precio, int interesas) {
    }


    ///// HELPERS /////

    public void addPropietario(UserEntity p) {
        this.propietario = p;
        if (p.getViviendas() == null)
            p.setViviendas(new ArrayList<>());
        p.getViviendas().add(this);
    }

    public void removePropietario(UserEntity p) {
        p.getViviendas().remove(this);
        this.propietario = null;
    }

    public void addInmobiliaria(Inmobiliaria i) {
        this.inmobiliaria = i;
        i.getViviendas().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i) {
        i.getViviendas().remove(this);
        this.inmobiliaria = null;
    }

}
