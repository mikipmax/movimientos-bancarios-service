package com.mikipmax.movimientosbancariosservice.modelo;

import com.mikipmax.movimientosbancariosservice.modelo.enums.TipoCuentaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cuenta", uniqueConstraints = @UniqueConstraint(columnNames = {"id_cliente", "tipo_cuenta"}))
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idCliente;

    @NotNull
    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta")
    private TipoCuentaEnum tipoCuenta;

    @NotNull
    @Min(0)
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @NotNull
    private Boolean estado = true;

    @Transient
    private Cliente cliente;

}
