package com.mikipmax.movimientosbancariosservice.servicio;


import com.mikipmax.movimientosbancariosservice.modelo.Cliente;
import com.mikipmax.movimientosbancariosservice.modelo.Cuenta;
import com.mikipmax.movimientosbancariosservice.modelo.Persona;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

public class ServicioUtil {

    private final WebClient wc = WebClient.create("http://localhost:9001");

    public Map<Long, Cliente> consultaClientes() {
        Flux<Cliente> clientes = wc.get().uri("/clientes")
                .retrieve()
                .bodyToFlux(Cliente.class);

        return clientes.collectMap(Persona::getId).block();
    }

    public Cliente consultaCliente(Long id) {
        return wc.get().uri("/clientes/" + id)
                .retrieve()
                .bodyToMono(Cliente.class).block();
    }

    public Cliente consultaClientePorIdentificacion(String identificacion) {
        return wc.get().uri("/clientes/identificacion/" + identificacion)
                .retrieve()
                .bodyToMono(Cliente.class).block();
    }
}
