package com.salesianostriana.dam.RealEstate;

import com.salesianostriana.dam.RealEstate.model.*;
import com.salesianostriana.dam.RealEstate.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MainDePrueba {

    private final InteresadoService interesadoService;
    private final ViviendaService viviendaService;
    private final InteresaService interesaService;
    private final InmobiliariaService inmobiliariaService;
    private final PropietarioService propietarioService;


    @PostConstruct
    public void test() {




        Vivienda vivienda = Vivienda.builder()
                .titulo("Casa 1")
                .descripcion("Descripcion")
                .precio(234567.78)
                .build();

        Vivienda vivienda2 = Vivienda.builder()
                .titulo("Casa 2")
                .descripcion("Descripcion")
                .precio(2354567.78)
                .build();

        Vivienda vivienda3 = Vivienda.builder()
                .titulo("Casa 3")
                .descripcion("Descripcion")
                .precio(56767.78)
                .build();
        Propietario propietario = Propietario.builder()
                .nombre("a")
                .apellidos("b")
                .avatar("c")
                .direccion("d")
                .email("e")
                .viviendas(new ArrayList<>())
                .build();
        propietarioService.save(propietario);
        vivienda2.addPropietario(propietario);

        viviendaService.save(vivienda);
        viviendaService.save(vivienda2);
        viviendaService.save(vivienda3);



        Vivienda vivienda4= Vivienda.builder()
                .titulo("Casa Blanca")
                .descripcion("Casa del presi")
                .direccion("Calle ejemplo")
                .interesas(new ArrayList<>())
                .inmobiliaria(null)
                .numBanios(3)
                .numHabitaciones(4)
                .metrosCuadrados(200)
                .precio(300)
                .codigoPostal("20040")
                .avatar("img.jpg")
                .latlng("sdfnsdf")
                .poblacion("Sevilla")
                .provincia("Sevilla")
                .tieneAscensor(true)
                .tipoVivienda(null)
                .tieneGaraje(true)
                .tienePiscina(true)
                .build();

        viviendaService.save(vivienda4);

        Propietario propietario1= Propietario.builder()
                .nombre("bla")
                .apellidos("blo")
                .telefono("blo")
                .email("blo")
                .avatar("blo")
                .direccion("blo")
                .viviendas(null)
                .build();
        propietarioService.save(propietario1);


        Inmobiliaria inmobiliaria1= Inmobiliaria.builder()
                .nombre("InmoLuismi")
                .email("inmoluismi@blabla.com")
                .telefono("68590890")
                .build();
        inmobiliariaService.save(inmobiliaria1);


        System.out.println("LISTA DE INTERESA");
        interesaService.findAll()
                .forEach(interesa -> System.out.printf("%s %s %s",interesa.getCreatedDate(), interesa.getVivienda().getTitulo(), interesa.getInteresado().getNombre()));


        System.out.println("Lista de Viviendas");
        viviendaService.findAll()
                .forEach(viviendas -> System.out.printf("%s %s %s",vivienda.getId(), vivienda.getDescripcion(), vivienda.getDireccion()));


        interesadoService.findAll()
                .forEach(interesado -> System.out.printf("%s %s %s",interesado.getNombre(), interesado.getApellidos(), interesado.getEmail()));
       System.out.println(interesadoService.findAll().isEmpty());

       inmobiliariaService.findAll()
               .forEach(inmobiliaria -> System.out.printf("%s %s %s",inmobiliaria.getId(), inmobiliaria.getNombre(),inmobiliaria.getEmail(),inmobiliaria.getTelefono()));


    }
}
