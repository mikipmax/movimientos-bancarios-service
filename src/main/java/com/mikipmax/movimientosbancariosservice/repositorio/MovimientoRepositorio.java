package com.mikipmax.movimientosbancariosservice.repositorio;

import com.mikipmax.movimientosbancariosservice.modelo.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m FROM Movimiento m INNER JOIN FETCH m.cuenta c ")
    List<Movimiento> obtenerTodos();

    @Query("SELECT m FROM Movimiento m INNER JOIN FETCH m.cuenta c WHERE c.idCliente= ?1 AND m.fecha between ?2 AND ?3 ORDER BY c.tipoCuenta")
    List<Movimiento> obtenerReporteEstadoCuenta(Long idCliente, LocalDate fechaDesde, LocalDate fechaHasa);
}
