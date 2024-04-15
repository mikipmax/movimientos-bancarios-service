package com.mikipmax.movimientosbancariosservice.excepciones;

public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String entidadNoEncontrada) {
        super(entidadNoEncontrada);
    }
}
