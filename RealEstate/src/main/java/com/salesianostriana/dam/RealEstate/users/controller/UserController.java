package com.salesianostriana.dam.RealEstate.users.controller;


import com.salesianostriana.dam.RealEstate.users.dto.CreateUserDto;
import com.salesianostriana.dam.RealEstate.users.dto.GetUserDto;
import com.salesianostriana.dam.RealEstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/register")
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/admin")
    public ResponseEntity<GetUserDto> nuevoAdmin(@RequestBody CreateUserDto newUser) {
        UserEntity saveAdmin = userEntityService.saveAdmin(newUser);

        if (saveAdmin == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saveAdmin));
    }

    @PostMapping("/propietario")
    public ResponseEntity<GetUserDto> nuevoPropietario(@RequestBody CreateUserDto newUser) {
        UserEntity savedProp = userEntityService.savePropietario(newUser);

        if (savedProp == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(savedProp));
    }

    @PostMapping("/gestor")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser) {

        UserEntity saveGestor = userEntityService.saveGestor(newUser);

        if (saveGestor == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(userDtoConverter.convertUserEntityToGetUserDto(saveGestor));
    }


}
