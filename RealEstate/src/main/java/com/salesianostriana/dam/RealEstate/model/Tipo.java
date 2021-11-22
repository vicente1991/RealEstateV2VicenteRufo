package com.salesianostriana.dam.RealEstate.model;

public enum Tipo {

    ALQUILER("Alquiler"), VENTA("Venta"), OBRA_NUEVA("Obra nueva");

    private final String texto;

    /**
     * Constructor para poder mostrar el texto de cada Enum en el formulario
     * de reserva.
     */

    private Tipo(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

}
