package com.salesianostriana.dam.RealEstate.users.services;

import com.salesianostriana.dam.RealEstate.services.base.BaseService;
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

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }


    public UserEntity saveAdmin(CreateUserDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .nombre(newUser.getFullname())
                    .email(newUser.getEmail())
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
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .nombre(newUser.getFullname())
                    .email(newUser.getEmail())
                    .rol(UserRole.PROPIETARIO)
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }
    public UserEntity saveGestor(CreateUserDto newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder()
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .nombre(newUser.getFullname())
                    .email(newUser.getEmail())
                    .rol(UserRole.GESTOR)
                    .build();
            return save(userEntity);
        } else {
            return null;
        }
    }


}
