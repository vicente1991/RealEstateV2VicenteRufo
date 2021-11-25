package com.salesianostriana.dam.RealEstate.users.services;


import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate.services.InmobiliariaService;
import com.salesianostriana.dam.RealEstate.users.dto.CreateUserDto;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.model.UserRole;
import com.salesianostriana.dam.RealEstate.users.repos.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.RealEstate.services.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, UUID, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }

    public List<UserEntity> loadUserByRole(UserRole rols) throws UsernameNotFoundException{
        return this.repository.findByRol(rols);
    }

    public UserEntity loadUserById(UUID uuid) throws UsernameNotFoundException{
        return this.repository.findById(uuid)
                .orElseThrow(()-> new UsernameNotFoundException(uuid + " no encontrado"));
    }

    public UserEntity saveAdmin(CreateUserDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .email(newUser.getEmail())
                    .direccion(newUser.getDireccion())
                    .avatar(newUser.getAvatar())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .rol(UserRole.ADMIN)
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }

    public UserEntity savePropietario(CreateUserDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newUser.getNombre())
                    .apellidos(newUser.getApellidos())
                    .email(newUser.getEmail())
                    .direccion(newUser.getDireccion())
                    .avatar(newUser.getAvatar())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .rol(UserRole.PROPIETARIO)
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }

    public UserEntity saveGestor(CreateUserDto newuser){
        if (newuser.getPassword().contentEquals(newuser.getPassword2())){
            UserEntity userEntity = UserEntity.builder()
                    .nombre(newuser.getNombre())
                    .apellidos(newuser.getApellidos())
                    .direccion(newuser.getDireccion())
                    .telefono(newuser.getTelefono())
                    .email(newuser.getEmail())
                    .avatar(newuser.getAvatar())
                    .rol(UserRole.GESTOR)
                    .password(passwordEncoder.encode(newuser.getPassword()))
                    .inmobiliaria(null)
                    .build();
            return save(userEntity);
        }else{
            return null;
        }
    }


}
