package com.salesianostriana.dam.RealEstate.users.controller;



import com.salesianostriana.dam.RealEstate.users.dto.CreateUserDto;
import com.salesianostriana.dam.RealEstate.users.dto.GetUserDto;
import com.salesianostriana.dam.RealEstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        UserEntity admin = userEntityService.saveAdmin(newUser);

        if (admin == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertUserEntityToGetUserDto(admin));
    }

    @PostMapping("/user")
    public ResponseEntity<GetUserDto> nuevoPropietario(@RequestBody CreateUserDto newUser) {
        UserEntity prop = userEntityService.savePropietario(newUser);

        if (prop == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertUserEntityToGetUserDto(prop));
    }

    @PostMapping("/gestor")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser) {

        UserEntity gestor = userEntityService.saveGestor(newUser);

        if (gestor == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertUserEntityToGetUserDto(gestor));
    }


}
