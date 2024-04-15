package com.mikipmax.movimientosbancariosservice.servicio;


import com.mikipmax.movimientosbancariosservice.modelo.Cliente;
import com.mikipmax.movimientosbancariosservice.modelo.Cuenta;
import com.mikipmax.movimientosbancariosservice.modelo.Persona;
import com.mikipmax.movimientosbancariosservice.repositorio.CuentaRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuentaServicio extends ServicioUtil {


    private final CuentaRepositorio cuentaRepositorio;

    public Flux<Cuenta> listar() {
        Map<Long, Cliente> hmClientes = consultaClientes();
        return Flux.defer(() -> Flux.fromStream(this.cuentaRepositorio.findAll().stream().peek(x -> {
            x.setCliente(hmClientes.get(x.getIdCliente()));
        })));
    }

    public Mono<Cuenta> buscarPorId(Long id) {
        Cuenta cuenta = this.cuentaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Entidad no encontrada"));
        Cliente cliente = consultaCliente(cuenta.getIdCliente());
        cuenta.setCliente(cliente);
        return Mono
                .defer(() -> Mono.just(cuenta));
    }

    public Cuenta crearOActualizar(Cuenta cuenta) {
        Cuenta c = cuentaRepositorio.save(cuenta);
        Cliente cliente = consultaCliente(cuenta.getIdCliente());
        c.setCliente(cliente);
        return c;
    }

    public void eliminar(Long id) {
        cuentaRepositorio.deleteById(id);
    }
}
