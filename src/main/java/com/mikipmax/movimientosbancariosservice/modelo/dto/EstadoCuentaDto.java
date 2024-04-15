package com.mikipmax.movimientosbancariosservice.modelo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;
import java.time.LocalDate;

//Solo para efectos de simulaciòn de reporte en json se agrega json alias con ESPACIOS Y MAYÙSCULAS, dado que, es una pèsima pràctica
public record EstadoCuentaDto(
        @JsonAlias("Fecha")
        LocalDate fecha,
        @JsonAlias("Cliente")
        String cliente,
        @JsonAlias("Numero Cuenta")
        String numeroCuenta,

        @JsonAlias("Tipo")
        String tipo,

        @JsonAlias("Saldo Inicial")
        BigDecimal saldoInicial,

        @JsonAlias("Estado")
        Boolean estado,

        @JsonAlias("Movimiento")
        BigDecimal movimiento,

        @JsonAlias("Saldo Disponible")
        BigDecimal saldoDisponible) {
}
