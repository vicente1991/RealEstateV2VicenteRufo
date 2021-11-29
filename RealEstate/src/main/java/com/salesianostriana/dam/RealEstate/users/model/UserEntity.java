package com.salesianostriana.dam.RealEstate.users.model;



import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="USERS")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String email;

    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String avatar;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private UserRole rol;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "propietario", fetch = FetchType.EAGER)
    private List<Vivienda> viviendas= new ArrayList<>();

    @ManyToOne
    private Inmobiliaria inmobiliaria;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

       return List.of((new SimpleGrantedAuthority("ROLE_" + rol.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //HELPERS

    public void addToInmo(Inmobiliaria i){
    this.inmobiliaria=i;
    i.getGestores().add(this);
    }

    public void removeFromInmo(Inmobiliaria i){
        i.getGestores().remove(i);
        this.inmobiliaria=null;
    }
}




