package com.salesianostriana.dam.RealEstate.controller;


import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioDTO2;
import com.salesianostriana.dam.RealEstate.dto.propietario.GetPropietarioViviendaDto;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
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
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/propietario")
public class PropietarioController {

    private final PropietarioService propietarioService;
    private final PropietarioDTOConverter propietarioDTOConverter;


    @Operation(summary = "Borra un Propietario creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        if(propietarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else {
            propietarioService.deleteById(id);

            return ResponseEntity.noContent().build();
        }


    }



    @Operation(summary = "Buscamos todos los propietarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetPropietarioDTO2>> findAll(){

        List <Propietario> datos = propietarioService.findAll();

        if(datos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            List<GetPropietarioDTO2> lista = datos.stream()
                    .map(propietarioDTOConverter::PropietarioToGetPropietarioDTO2)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GetPropietarioViviendaDto>> findOne(@PathVariable Long id){
        Optional<Propietario> prop = propietarioService.findById(id);
        if(propietarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<GetPropietarioViviendaDto> propietarioDTOS= prop.stream()
                    .map(propietarioDTOConverter::propietarioToGetPropietarioViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(propietarioDTOS);
        }
    }

}
