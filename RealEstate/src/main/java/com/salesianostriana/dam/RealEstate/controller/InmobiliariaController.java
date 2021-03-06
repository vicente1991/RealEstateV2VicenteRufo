package com.salesianostriana.dam.RealEstate.controller;


import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.CreateInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianostriana.dam.RealEstate.dto.vivienda.ViviendaDTOConverter;
import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.repository.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstate.services.InmobiliariaService;
import com.salesianostriana.dam.RealEstate.services.ViviendaService;
import com.salesianostriana.dam.RealEstate.users.dto.CreateUserDto;
import com.salesianostriana.dam.RealEstate.users.dto.GetUserDto;
import com.salesianostriana.dam.RealEstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.model.UserRole;
import com.salesianostriana.dam.RealEstate.users.services.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/inmobiliaria")
public class InmobiliariaController {

    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;
    private final InmobiliariaService inmobiliariaService;
    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;
    private final ViviendaService viviendaService;
    private final ViviendaDTOConverter viviendaDTOConverter;


    @Operation(summary = "Crea una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha creado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la inmobiliaria",
                    content = @Content),
    })


    @PostMapping("/")
    public ResponseEntity<Inmobiliaria> create (@RequestBody  Inmobiliaria inmobiliaria){
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(inmobiliariaService.save(inmobiliaria));

    }




    @Operation(summary = "Obtiene todos las inmobiliarias creadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las inmobiliarias",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado las inmobiliarias",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetInmobiliariaDTO>> findAll(){
        List <Inmobiliaria> datos= inmobiliariaService.findAll();

        if (datos.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            List<GetInmobiliariaDTO> lista = datos.stream()
                    .map(inmobiliariaDtoConverter::getInmobiliariaToInmobiliariaDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }

    }

    @Operation(summary = "Obtiene una inmobiliaria creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado la inmobiliaria",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<GetInmobiliariaDTO>> findOne (@PathVariable Long id){
        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(id);
        if(inmobiliariaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<GetInmobiliariaDTO> inmobiliariaDTO= inmo.stream()
                    .map(inmobiliariaDtoConverter::getInmobiliariaToInmobiliariaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(inmobiliariaDTO);
        }
    }


    @Operation(summary = "Borra una inmobiliaria creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDTO> borrarInmobiliaria(@PathVariable Long id){

        if(inmobiliariaService.findById(id).isEmpty() ){
            return ResponseEntity.notFound().build();
        }else {
        inmobiliariaService.deleteById(id);
            return ResponseEntity.noContent().build();
         }
    }



    @Operation(summary = "A??ade una inmobiliaria con un gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha a??adido un gestor a la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })
    @PostMapping("/{id}/gestor")
    public ResponseEntity<GetUserDto> addGestor (@PathVariable Long id, @RequestBody CreateUserDto gestor,
                                                    @AuthenticationPrincipal UserEntity usuario) {

        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(id);


        if(inmo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else if (usuario.getRol().equals(UserRole.ADMIN) || usuario.getInmobiliaria().getId().equals(id)){

            UserEntity getUsuarioDTO = userEntityService.saveGestor(gestor);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userDtoConverter
                            .convertUserEntityToGetUserDto(getUsuarioDTO));

        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }


    @Operation(summary = "Borra una inmobiliaria con un gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha a??adido un gestor a la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })
    @DeleteMapping("gestor/{id}")
    public ResponseEntity<GetUserDto> removeGestor (@PathVariable UUID id, @AuthenticationPrincipal UserEntity gestor){

        Optional<UserEntity> gestorEliminado = userEntityService.findById(id);


        if (gestor.getRol().equals(UserRole.ADMIN) || gestorEliminado.get().getInmobiliaria().getId().equals(gestor.getInmobiliaria().getId())) {
            userEntityService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        else {
            return ResponseEntity.notFound().build();
        }
    }

}



