package com.salesianostriana.dam.RealEstate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Interesa {
    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @JsonIgnore
    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name="vivienda_id", foreignKey = @ForeignKey(name = "FK_INTERESA_VIVIENDA"))
    private Vivienda vivienda;


    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();
    private String mensaje;



    public void addVivienda(Vivienda v) {
        this.vivienda = v;
        v.getInteresas().add(this);
    }

    public void removeVivienda(Vivienda v) {
        v.getInteresas().remove(this);
        this.vivienda = null;

    }




}
