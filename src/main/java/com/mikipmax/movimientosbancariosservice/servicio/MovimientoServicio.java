package com.mikipmax.movimientosbancariosservice.servicio;


import com.mikipmax.movimientosbancariosservice.excepciones.SaldoNoDisponibleException;
import com.mikipmax.movimientosbancariosservice.modelo.Cliente;
import com.mikipmax.movimientosbancariosservice.modelo.Cuenta;
import com.mikipmax.movimientosbancariosservice.modelo.Movimiento;
import com.mikipmax.movimientosbancariosservice.modelo.Persona;
import com.mikipmax.movimientosbancariosservice.modelo.dto.EstadoCuentaDto;
import com.mikipmax.movimientosbancariosservice.repositorio.CuentaRepositorio;
import com.mikipmax.movimientosbancariosservice.repositorio.MovimientoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MovimientoServicio extends ServicioUtil {


    private final MovimientoRepositorio movimientoRepositorio;
    private final CuentaRepositorio cuentaRepositorio;


    public Flux<Movimiento> listar() {
        Map<Long, Cliente> hmClientes = consultaClientes();
        return Flux.defer(() -> Flux.fromStream(movimientoRepositorio.obtenerTodos().stream().peek(x -> {
            x.getCuenta().setCliente(hmClientes.get(x.getCuenta().getIdCliente()));
        })));
    }

    public Movimiento crearOActualizar(Movimiento entidad) {
        Cuenta cuenta = cuentaRepositorio.findCuentaByNumeroCuenta(entidad.getNumeroCuenta());
        entidad.setCuenta(cuenta);
        entidad.setFecha(LocalDate.now());

        if (entidad.getValor().compareTo(BigDecimal.ZERO) < 0 && cuenta.getSaldoInicial().compareTo(entidad.getValor().negate()) < 0) {
            throw new SaldoNoDisponibleException("Saldo no Disponible");
        }

        entidad.setSaldoInicialHistorico(cuenta.getSaldoInicial());

        cuenta.setSaldoInicial(cuenta.getSaldoInicial().add(entidad.getValor()));

        cuentaRepositorio.save(cuenta);

        entidad.setSaldoDisponibleHistorico(cuenta.getSaldoInicial());

        return movimientoRepositorio.save(entidad);
    }

    public void eliminar(Long id) {
        movimientoRepositorio.deleteById(id);
    }

    public List<EstadoCuentaDto> obtenerEstadoCuenta(String identificacion, String fechaDesde, String fechaHasta) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaDesdeAux = LocalDate.parse(fechaDesde, dtf);
        LocalDate fechaHastaAux = LocalDate.parse(fechaHasta, dtf);
        //Primero obtenemos el cliente dada la identificaicon
        Cliente cliente = consultaClientePorIdentificacion(identificacion);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        List<Movimiento> lista =  movimientoRepositorio.obtenerReporteEstadoCuenta(cliente.getId(), fechaDesdeAux, fechaHastaAux);
        return lista.stream().map(x-> new EstadoCuentaDto(x.getFecha(), cliente.getNombre(), x.getCuenta().getNumeroCuenta(),
                x.getCuenta().getTipoCuenta().name(), x.getSaldoInicialHistorico(), x.getCuenta().getEstado(), x.getValor(), x.getSaldoDisponibleHistorico())).toList();
    }
}
