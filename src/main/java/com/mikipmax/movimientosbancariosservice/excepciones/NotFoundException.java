package com.mikipmax.movimientosbancariosservice.excepciones;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entidadNoEncontrada) {
        super(entidadNoEncontrada);
    }
}
