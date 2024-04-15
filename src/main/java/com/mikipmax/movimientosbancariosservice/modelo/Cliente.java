package com.mikipmax.movimientosbancariosservice.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente extends Persona {

    private String contrasenia;

    @NotNull
    private Boolean estado = true;

}
