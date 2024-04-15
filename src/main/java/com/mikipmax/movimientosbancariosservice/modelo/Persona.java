package com.mikipmax.movimientosbancariosservice.modelo;


import com.mikipmax.movimientosbancariosservice.modelo.enums.GeneroEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;

}
