package com.salesianostriana.dam.RealEstate.controller;

import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianostriana.dam.RealEstate.dto.interesa.GetInteresaDTO;
import com.salesianostriana.dam.RealEstate.dto.interesa.GetInteresadoDTO;
import com.salesianostriana.dam.RealEstate.dto.interesa.InteresaDTOConverter;
import com.salesianostriana.dam.RealEstate.dto.interesa.InteresadoDTOConverter;
import com.salesianostriana.dam.RealEstate.dto.vivienda.GetInmobiliariaViviendaDto;
import com.salesianostriana.dam.RealEstate.dto.vivienda.GetViviendaDTO;
import com.salesianostriana.dam.RealEstate.dto.vivienda.GetViviendaPropietarioDTO;
import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Interesado;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.repository.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstate.repository.ViviendaRepository;
import com.salesianostriana.dam.RealEstate.services.InmobiliariaService;
import com.salesianostriana.dam.RealEstate.dto.vivienda.GetViviendaSummarizedDTO;
import com.salesianostriana.dam.RealEstate.dto.vivienda.ViviendaDTOConverter;
import com.salesianostriana.dam.RealEstate.model.*;
import com.salesianostriana.dam.RealEstate.services.InteresaService;
import com.salesianostriana.dam.RealEstate.services.InteresadoService;
import com.salesianostriana.dam.RealEstate.services.PropietarioService;
import com.salesianostriana.dam.RealEstate.services.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Vivienda", description = "Controller de las viviendas")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final ViviendaDTOConverter viviendaDTOConverter;
    private final InteresadoService interesadoService;
    private final InteresaService interesaService;
    private final InteresadoDTOConverter interesadoDTOConverter;
    private final ViviendaRepository viviendaRepository;
    private final InmobiliariaRepository inmobiliariaRepository;
    private final InteresaDTOConverter interesaDTOConverter;
    private final PropietarioService propietarioService;
    private final ViviendaDTOConverter dtoConverter;


    @Operation(summary = "Se listan todas las viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las viviendas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado las viviendas",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetViviendaSummarizedDTO>> findAll(@PageableDefault(page=0, size=9) Pageable pageable){

        Page<Vivienda> datos = viviendaService.findAll(pageable);

        if(datos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            List<GetViviendaSummarizedDTO> lista = datos.stream()
                    .map(viviendaDTOConverter::viviendaToGetViviendaSummarizedDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
    }


    @Operation(summary = "Crea una nueva vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la vivienda",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetViviendaPropietarioDTO> createVivienda (@RequestBody GetViviendaPropietarioDTO dto){

        Vivienda vivienda = viviendaDTOConverter.getViviendaPropietario(dto);
        Propietario propietario = viviendaDTOConverter.getPropietarioVivienda(dto);


        if(dto.getTitulo().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        else{

            if(propietario.getId()!=null)
                propietario = propietarioService.findById(propietario.getId()).get();

            propietarioService.save(propietario);
            vivienda.addPropietario(propietario);
            viviendaService.save(vivienda);

            GetViviendaPropietarioDTO nuevo = viviendaDTOConverter.createViviendaPropietarioDTO(vivienda);

            return  ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevo);

        }


    }

    @Operation(summary = "Crea un nuevo interesado e interesa asociado a una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el interesado y el interesa asociado a una vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado el interesado y el interesa asociado a una vivienda",
                    content = @Content),
    })
    @PostMapping("/{id}/meinteresa")
    public ResponseEntity<Interesado> createInteresa (@RequestBody GetInteresadoDTO dto, @PathVariable Long id){

        Interesado interesado = interesadoDTOConverter.a(dto);
        Interesa interesa = interesadoDTOConverter.b(dto);

        interesadoService.save(interesado);
        interesa.addInteresado(interesado);
        interesa.setVivienda(viviendaService.findById(id).get());

        interesaService.save(interesa);
        interesado.getInteresas().add(interesa);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(interesadoService.save(interesado));


    }



    @Operation(summary = "Crea un interesa asociado a una vivienda y a un interesado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el interesa asociado a una vivienda y a un interesado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el interesa",
                    content = @Content),
    })
    @PostMapping("/{id}/meinteresa/{id2}")
    public ResponseEntity<Interesa> create (@PathVariable Long id2, @RequestBody GetInteresaDTO nuevoInteresa, @PathVariable Long id){


        if(viviendaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {

            Interesa interesa = interesaDTOConverter.create(nuevoInteresa);

            Interesado interesado = interesadoService.getById(id2);

            Vivienda vivienda = viviendaService.getById(id);

            interesa.addVivienda(vivienda);
            interesa.addInteresado(interesado);
            Interesa result = interesaService.save(interesa);


            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        }

    }

    @Operation(summary = "Obtiene una vivienda creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetViviendaDTO> findOne(@PathVariable Long id) {

        Optional<Vivienda> vivienda = viviendaService.findById(id);

        if(vivienda.isEmpty()){
            return ResponseEntity.notFound().build();

        }else {
            GetViviendaDTO viviendaDTO = viviendaDTOConverter.viviendaToGetViviendaDTO(vivienda.get());
            return ResponseEntity.ok().body(viviendaDTO);
        }
    }


    @Operation(summary = "Obtiene un top de las 5 viviendas con más interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las viviendas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @GetMapping("/top")
    public ResponseEntity<List<GetViviendaSummarizedDTO>> top10Viviendas (@RequestParam("n") int n) {
        List<Vivienda> datos = viviendaService.findTop10ViviendaOrderByInteresas();

        List<GetViviendaSummarizedDTO> lista = datos.stream()
                .map(viviendaDTOConverter::viviendaToGetViviendaSummarizedDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(lista);


    }

    @Operation(summary = "Borra un interesa asociado a una vivienda y a un interesado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el interesa asociado a una vivienda y a un interesado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })

    @DeleteMapping("/{id1}/meinteresa/{id2}")
    public ResponseEntity<?> delete(@PathVariable Long id1, @PathVariable Long id2) {

    if(viviendaService.findById(id1).isEmpty() && interesadoService.findById(id2).isEmpty()){
        return ResponseEntity.notFound().build();
    } else {

        List<Interesa> interesaList = viviendaService.getById(id1).getInteresas();
        Vivienda vivienda = viviendaService.getById(id1);
        Interesado interesado = interesadoService.getById(id2);

        for (Interesa i : interesaList){
            interesaService.delete(i);

            return ResponseEntity.noContent().build();
        }
    }
    return ResponseEntity.badRequest().build();
    }






    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> edit (@RequestBody Vivienda v, @PathVariable Long id) {

        if(viviendaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();

        }else {
            return ResponseEntity.of(

                    viviendaService.findById(id).map(m -> {
                        m.setTitulo(v.getTitulo());
                        m.setDescripcion(v.getDescripcion());
                        m.setAvatar(v.getAvatar());
                        m.setCodigoPostal(v.getCodigoPostal());
                        m.setLatlng(v.getLatlng());
                        m.setMetrosCuadrados(v.getMetrosCuadrados());
                        m.setNumBanios(v.getNumBanios());
                        m.setNumHabitaciones(v.getNumHabitaciones());
                        m.setPoblacion(v.getPoblacion());
                        m.setPrecio(v.getPrecio());
                        m.setProvincia(v.getProvincia());
                        m.setDireccion(v.getDireccion());
                        m.setTipoVivienda(v.getTipoVivienda());
                        m.setTienePiscina(v.isTienePiscina());
                        m.setTieneAscensor(v.isTieneAscensor());
                        m.setTieneGaraje(v.isTieneGaraje());
                        viviendaService.save(m);

                        return m;
                    })
            );
        }
    }



    @Operation(summary = "Borra una vivienda por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        if(viviendaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else {
            viviendaService.deleteById(id);

            return ResponseEntity.noContent().build();
        }


    }

    @Operation(summary = "Borra la asociación entre una vivienda y una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda a la que le queremos quitar la inmobiliaria",
                    content = @Content),
    })

    @DeleteMapping("/{id}/inmobiliaria/")
    public ResponseEntity deleteInmobiliariaFromVivienda(@PathVariable Long id) {

        if(viviendaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else {

            Inmobiliaria inmobiliaria = viviendaService.findById(id).get().getInmobiliaria();
            viviendaService.findById(id).get().removeInmobiliaria(inmobiliaria);
            viviendaService.save(viviendaService.findById(id).get());

            return ResponseEntity.noContent().build();
        }


    }




    

}
