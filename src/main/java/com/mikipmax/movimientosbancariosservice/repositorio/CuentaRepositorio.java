package com.mikipmax.movimientosbancariosservice.repositorio;

import com.mikipmax.movimientosbancariosservice.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {

    Cuenta findCuentaByNumeroCuenta(String numeroCuenta);
}
