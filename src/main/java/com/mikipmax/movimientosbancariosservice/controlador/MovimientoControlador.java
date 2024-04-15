package com.mikipmax.movimientosbancariosservice.controlador;


import com.mikipmax.movimientosbancariosservice.modelo.Movimiento;
import com.mikipmax.movimientosbancariosservice.modelo.dto.EstadoCuentaDto;
import com.mikipmax.movimientosbancariosservice.servicio.MovimientoServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovimientoControlador {

    private final MovimientoServicio movimientoServicio;

    public MovimientoControlador(MovimientoServicio movimientoServicio) {
        this.movimientoServicio = movimientoServicio;
    }

    @GetMapping
    public Flux<Movimiento> obtenerClientesComunicacion() {
        return movimientoServicio.listar();
    }

    @PostMapping
    public Movimiento crear(@RequestBody Movimiento e) {
        return movimientoServicio.crearOActualizar(e);
    }

    @PutMapping
    public Movimiento actualizar(@RequestBody Movimiento e) {
        return movimientoServicio.crearOActualizar(e);
    }

    @DeleteMapping("/{id}")
    public void actualizar(@PathVariable("id") Long id) {
        movimientoServicio.eliminar(id);
    }

    @GetMapping("/reportes/{identificacion}")
    public List<EstadoCuentaDto> reporte(@PathVariable("identificacion") String identificacion, @RequestParam("fechaDesde") String fechaDesde, @RequestParam("fechaHasta") String fechaHasta) {
        return movimientoServicio.obtenerEstadoCuenta(identificacion, fechaHasta, fechaHasta);
    }
}
