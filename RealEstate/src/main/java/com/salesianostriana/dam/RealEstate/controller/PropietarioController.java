package com.salesianostriana.dam.RealEstate.controller;


import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioDTO2;
import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioViviendaDto;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.model.UserRole;
import com.salesianostriana.dam.RealEstate.users.services.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    @Operation(summary = "Borra un Propietario creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        if (propietarioService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            propietarioService.deleteById(id);
            return ResponseEntity.noContent().build();
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
    @GetMapping("")
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
    @GetMapping("{id}")
    public ResponseEntity<UserEntity> findOne(@PathVariable UUID id) {
        UserEntity prop = userEntityService.loadUserById(id);
        if (prop == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(prop);
        }

    }
}
