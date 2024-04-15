package com.mikipmax.movimientosbancariosservice.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GeneroEnum {

    M("Masculino"), F("Femenino");

    private String valor;
}
