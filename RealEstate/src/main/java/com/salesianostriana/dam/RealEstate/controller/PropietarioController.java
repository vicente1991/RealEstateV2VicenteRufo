package com.salesianostriana.dam.RealEstate.controller;


import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioDTO2;
import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioViviendaDto;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.security.jwt.JwtAuthorizationFilter;
import com.salesianostriana.dam.RealEstate.security.jwt.JwtProvider;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.model.UserRole;
import com.salesianostriana.dam.RealEstate.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioDTO;
import com.salesianostriana.dam.RealEstate.dto.propietario.PropietarioDTOConverter;
import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate.model.Propietario;
import com.salesianostriana.dam.RealEstate.services.PropietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/propietario")
public class PropietarioController {

    private final PropietarioService propietarioService;
    private final PropietarioDTOConverter propietarioDTOConverter;
    private final UserEntityService userEntityService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtProvider jwtProvider;


    @Operation(summary = "Borra un Propietario creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal UserEntity usuario) {

        Optional<UserEntity> prop = propietarioService.findById(id);

        if(!prop.isEmpty() && usuario.getId().equals(id)){
            propietarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> data = userEntityService.loadUserByRole(UserRole.PROPIETARIO);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<UserEntity> lista = data.stream().collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
    }

    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el propietario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado el propietario",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<GetPropietarioViviendaDto>> findOne(@PathVariable UUID id, @AuthenticationPrincipal UserEntity user) {
        Optional<UserEntity> p= propietarioService.findById(id);


        if (user.getId().equals(id) ){
            List<GetPropietarioViviendaDto> prop= p.stream()
                    .map(propietarioDTOConverter::propietarioToGetPropietarioViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(prop);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
