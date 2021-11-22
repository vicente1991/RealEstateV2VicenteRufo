package com.salesianostriana.dam.RealEstate.controller;

import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.services.InteresaService;
import com.salesianostriana.dam.RealEstate.services.InteresadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/interesa")

public class InteresaController {

    private final InteresadoService interesadoService;
    private final InteresaService interesaService;

    /*@GetMapping("/")
    public ResponseEntity<List<Interesa>> findAll(){

        if(interesadoService.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(interesaService.findAll());
        }

    }*/
}
