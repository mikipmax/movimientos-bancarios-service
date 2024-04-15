package com.mikipmax.movimientosbancariosservice.controlador;


import com.mikipmax.movimientosbancariosservice.modelo.Cuenta;
import com.mikipmax.movimientosbancariosservice.servicio.CuentaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/cuentas")
public class CuentaControlador {

    private final CuentaServicio cuentaServicio;

    public CuentaControlador(CuentaServicio cuentaServicio) {
        this.cuentaServicio = cuentaServicio;
    }

    @GetMapping
    public Flux<Cuenta> obtenerClientesComunicacion() {
       return cuentaServicio.listar();
    }

    @GetMapping("/{id}")
    public Mono<Cuenta> obtenerCuenta(@PathVariable Long id) {
        return cuentaServicio.buscarPorId(id);
    }

    @PostMapping
    public Cuenta crear(@RequestBody Cuenta e) {
        return cuentaServicio.crearOActualizar(e);
    }

    @PutMapping
    public Cuenta actualizar(@RequestBody Cuenta e) {
        return cuentaServicio.crearOActualizar(e);
    }

    @DeleteMapping("/{id}")
    public void actualizar(@PathVariable("id") Long id) {
        cuentaServicio.eliminar(id);
    }
}
