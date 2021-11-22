package com.salesianostriana.dam.RealEstate.controller;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Interesado;
import com.salesianostriana.dam.RealEstate.model.Propietario;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.services.InteresaService;
import com.salesianostriana.dam.RealEstate.services.InteresadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/interesado")
@CrossOrigin(origins = "http://localhost:4200")
public class InteresadoController {

    private final InteresadoService interesadoService;
    private final InteresaService interesaService;


    @Operation(summary = "Buscamos todos los interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los interesados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesado.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los interesados",
                    content = @Content),
    })


    @GetMapping("/")
    public ResponseEntity<List<Interesado>> findAll(){

        if(interesadoService.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(interesadoService.findAll());
        }

    }

    @Operation(summary = "Obtiene un interesado creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el interesado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesado.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado el interesado",
                    content = @Content),
    })

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {

        if(interesadoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();

        }else {
            return ResponseEntity
                    .of(interesadoService.findById(id));
        }
    }






}
