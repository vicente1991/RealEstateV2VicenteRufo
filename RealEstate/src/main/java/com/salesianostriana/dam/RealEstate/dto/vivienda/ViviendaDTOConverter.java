package com.salesianostriana.dam.RealEstate.dto.vivienda;

import com.salesianostriana.dam.RealEstate.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate.dto.vivienda.*;
import com.salesianostriana.dam.RealEstate.model.Propietario;
import com.salesianostriana.dam.RealEstate.model.Tipo;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDTOConverter {

    String t = "";
    Tipo tipo = Tipo.VENTA;

    public Vivienda createViviendaSummarizedDtoToVivienda(CreateViviendaSummarizedDTO v){
        return new Vivienda (
                v.getTitulo(),
                v.getDescripcion(),
                v.getAvatar(),
                v.getPrecio(),
                v.getInteresas()
        );
    }

    public GetViviendaSummarizedDTO viviendaToGetViviendaSummarizedDTO(Vivienda m) {
        return GetViviendaSummarizedDTO
                .builder()
                .id(m.getId())
                .titulo(m.getTitulo())
                .descripcion(m.getDescripcion())
                .avatar(m.getAvatar())
                .precio(m.getPrecio())
                .interesas(m.getInteresas().size())
                .build();

    }

    public Vivienda createViviendaDtoToVivienda(CreateViviendaDTO v){
        return new Vivienda (
                v.getTitulo(),
                v.getAvatar(),
                v.getTipo(),
                v.getPrecio(),
                v.getUbicacion(),
                v.getMetrosCuadrados(),
                v.getNumBanios(),
                v.getNumHabitaciones(),
                v.isTieneAscensor(),
                v.isTieneGaraje(),
                v.isTienePiscina(),
                v.getPropietario(),
                v.getInmobiliaria()
        );
    }

    public GetViviendaDTO viviendaToGetViviendaDTO(Vivienda v) {
        return GetViviendaDTO
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .precio(v.getPrecio())
                .ubicacion(String.format("%s, %s", v.getDireccion(), v.getCodigoPostal()))
                .zona(String.format("%s (%s)", v.getPoblacion(), v.getProvincia()))
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .avatar(v.getAvatar())
                .tipo(v.getTipoVivienda().getTexto())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .tienePiscina(v.isTienePiscina())
                .propietario(v.getPropietario()==null?null:v.getPropietario().getNombre())
                .inmobiliaria(v.getInmobiliaria()==null?null:v.getInmobiliaria().getNombre())
                .build();
    }


    public GetViviendaInmobiliariaDto GetInmobiliariaViviendaDto(Vivienda v){


        return GetViviendaInmobiliariaDto.builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .precio(v.getPrecio())
                .propietario(v.getPropietario().getNombre())
                .tipo(v.getTipoVivienda().getTexto())
                .zona(String.format("%s (%s)", v.getPoblacion(), v.getProvincia()))
                .ubicacion(String.format("%s, %s", v.getDireccion(), v.getCodigoPostal()))
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .avatar(v.getAvatar())
                .nombreInmo(v.getInmobiliaria().getNombre())
                .build();
    }



    public GetViviendaPropietarioDTO createViviendaPropietarioDTO (Vivienda v){
        return GetViviendaPropietarioDTO
                .builder()
                .id(v.getId())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .metrosCuadrados(v.getMetrosCuadrados())
                .nombre(v.getPropietario().getNombre())
                .apellidos(v.getPropietario().getApellidos())
                .email(v.getPropietario().getEmail())
                .telefono(v.getPropietario().getTelefono())
                .avatar2(v.getPropietario().getAvatar())
                .build();
    }

    public Propietario getPropietarioVivienda (GetViviendaPropietarioDTO gv){
        return Propietario.builder()
                .id(gv.getId())
                .nombre(gv.getNombre())
                .apellidos(gv.getApellidos())
                .email(gv.getEmail())
                .telefono(gv.getTelefono())
                .avatar(gv.getAvatar2())
                .build();
    }

    public Vivienda getViviendaPropietario (GetViviendaPropietarioDTO gv){
        return Vivienda.builder()
                .titulo(gv.getTitulo())
                .descripcion(gv.getDescripcion())
                .direccion(gv.getDireccion())
                .codigoPostal(gv.getCodigoPostal())
                .precio(gv.getPrecio())
                .poblacion(gv.getPoblacion())
                .provincia(gv.getProvincia())
                .latlng(gv.getLatlng())
                .tipoVivienda(tipo)
                .metrosCuadrados(gv.getMetrosCuadrados())
                .numBanios(gv.getNumBanios())
                .numHabitaciones(gv.getNumHabitaciones())
                .avatar(gv.getAvatar())
                .tieneAscensor(gv.isTieneAscensor())
                .tieneGaraje(gv.isTieneGaraje())
                .tienePiscina(gv.isTienePiscina())
                .build();
    }

    /*No lo uso al final
    public Vivienda GetVivienda2 ( GetViviendaDTO2 v){
        return Vivienda.builder()
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .codigoPostal(v.getAvatar())
                .tipoVivienda(v.getTipoVivienda())
                .tieneGaraje(v.isTieneGaraje())
                .latlng(v.getLatlng())
                .tieneAscensor(v.isTieneAscensor())
                .tienePiscina(v.isTienePiscina())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .numBanios(v.getNumBanios())
                .metrosCuadrados(v.getMetrosCuadrados())
                .direccion(v.getDireccion())
                .build();
    }*/

}
