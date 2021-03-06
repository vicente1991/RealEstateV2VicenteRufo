package com.salesianostriana.dam.RealEstate.controller;

import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate.dto.interesa.GetInteresaDTO;
import com.salesianostriana.dam.RealEstate.dto.interesa.InteresaDTOConverter;
import com.salesianostriana.dam.RealEstate.dto.vivienda.*;
import com.salesianostriana.dam.RealEstate.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate.model.Interesa;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import com.salesianostriana.dam.RealEstate.repository.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstate.repository.ViviendaRepository;
import com.salesianostriana.dam.RealEstate.services.InmobiliariaService;
import com.salesianostriana.dam.RealEstate.services.InteresaService;
import com.salesianostriana.dam.RealEstate.services.PropietarioService;
import com.salesianostriana.dam.RealEstate.services.ViviendaService;
import com.salesianostriana.dam.RealEstate.users.dto.UserDtoConverter;
import com.salesianostriana.dam.RealEstate.users.model.UserEntity;
import com.salesianostriana.dam.RealEstate.users.model.UserRole;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UserDtoConverter userDtoConverter;
    private final InmobiliariaService inmobiliariaService;


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
    public ResponseEntity<GetViviendaDTO> createVivienda (@RequestBody CreateViviendaDTO dto, @AuthenticationPrincipal UserEntity user){

        GetViviendaDTO getViviendaDTO = saveGetViviendaDto(dto,user);

        viviendaService.saveVivienda(getViviendaDTO,user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(getViviendaDTO);
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


    @Operation(summary = "Obtiene un top de las 5 viviendas con m??s interesados")
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



    @PutMapping("/{id}")
    public ResponseEntity<GetViviendaDTO> edit (@RequestBody GetViviendaDTO v,
                                                @PathVariable Long id,
                                                @AuthenticationPrincipal UserEntity prop) {

        Optional <Vivienda> viviendaEditada = viviendaService.findById(id);

        if(viviendaEditada.isEmpty()){
            return ResponseEntity.notFound().build();

        }

        if (!viviendaEditada.get().getPropietario().getId().equals(prop.getId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        else {
            return ResponseEntity.of(

                    viviendaService.findById(id).map(m -> {
                        m.setTitulo(v.getTitulo());
                        m.setDescripcion(v.getDescripcion());
                        m.setAvatar(v.getAvatar());
                        m.setLatlng(v.getLatlng());
                        m.setMetrosCuadrados(v.getMetrosCuadrados());
                        m.setNumBanios(v.getNumBanios());
                        m.setNumHabitaciones(v.getNumHabitaciones());
                        m.setPoblacion(v.getPoblacion());
                        m.setPrecio(v.getPrecio());
                        m.setProvincia(v.getProvincia());
                        m.setTipoVivienda(v.getTipo());
                        m.setTienePiscina(v.isTienePiscina());
                        m.setTieneAscensor(v.isTieneAscensor());
                        m.setTieneGaraje(v.isTieneGaraje());
                        viviendaService.save(m);

                        return viviendaDTOConverter.viviendaToGetViviendaDTO(m);
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
    public ResponseEntity delete(@PathVariable Long id,@AuthenticationPrincipal UserEntity user){

        Optional<Vivienda> vivienda = viviendaService.findById(id);
        if(vivienda.isPresent() && vivienda.get().getPropietario().getId().equals(user.getId())){
            viviendaService.findById(id).get().removeMuchasCosas();
            viviendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        if(vivienda.isEmpty() || !vivienda.get().getId().equals(id)){
            return ResponseEntity.notFound().build();
        }
        else {
            viviendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }


    }




    @Operation(summary = " Asocia una vivienda con una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha asociado la vivienda con una inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda a la que le queremos asociar la inmobiliaria",
                    content = @Content),
    })
    @PostMapping("/{id}/inmobiliaria/{id2}")
    public ResponseEntity<?> asociarConInmo(@PathVariable Long id,@PathVariable Long id2,@AuthenticationPrincipal UserEntity user) {
        if (viviendaService.findById(id).isEmpty() || inmobiliariaService.findById(id2).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Vivienda v = viviendaService.findById(id).get();
            Inmobiliaria i = inmobiliariaService.findById(id2).get();
            v.setInmobiliaria(i);
            v.addInmobiliaria(i);
            viviendaService.save(v);
            GetViviendaInmobiliariaDto get = viviendaDTOConverter.viviendaToGetViviendaInmobiliariaDto(v, i);
            return ResponseEntity.status(HttpStatus.CREATED).body(get);


        }

    }


    @Operation(summary = " Borra una vivienda asociada con una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha asociado la vivienda con una inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda a la que le queremos asociar la inmobiliaria",
                    content = @Content),
    })
    @DeleteMapping("/{id}/inmobiliaria")
    @CrossOrigin
    public ResponseEntity<?> deleteInmoVivienda(@PathVariable Long id, @AuthenticationPrincipal UserEntity user){

        if(viviendaService.findById(id).isPresent() && viviendaService.findById(id).get().getPropietario().getId().equals(user.getId()) || user.getRol().equals(UserRole.ADMIN)){
            Vivienda v = viviendaService.findById(id).get();
            Inmobiliaria i = new Inmobiliaria();
            v.setInmobiliaria(i);
            v.removeMuchasCosas();
            v.removeInmobiliaria(i);
            viviendaService.save(v);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    public GetViviendaDTO saveGetViviendaDto(CreateViviendaDTO createViviendaDto, UserEntity user){
        GetViviendaDTO getViviendaDto = GetViviendaDTO.builder()
                .titulo(createViviendaDto.getTitulo())
                .descripcion(createViviendaDto.getDescripcion())
                .latlng(createViviendaDto.getLatlng())
                .tienePiscina(createViviendaDto.isTienePiscina())
                .tieneAscensor(createViviendaDto.isTieneAscensor())
                .tieneGaraje(createViviendaDto.isTieneGaraje())
                .precio(createViviendaDto.getPrecio())
                .poblacion(createViviendaDto.getPoblacion())
                .provincia(createViviendaDto.getProvincia())
                .avatar(createViviendaDto.getAvatar())
                .tipo(createViviendaDto.getTipo())
                .provincia(createViviendaDto.getProvincia())
                .poblacion(createViviendaDto.getPoblacion())
                .numHabitaciones(createViviendaDto.getNumHabitaciones())
                .metrosCuadrados(createViviendaDto.getMetrosCuadrados())
                .numBanios(createViviendaDto.getNumBanios())
                .propietario(userDtoConverter.convertUserEntityToGetUserDto(user))
                .build();

        return getViviendaDto;
    }

}
