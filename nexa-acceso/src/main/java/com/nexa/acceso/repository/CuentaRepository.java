package com.nexa.acceso.repository;
import com.nexa.acceso.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    Optional<Cuenta> findByNombreUsuario(String nombreUsuario);
    Optional<Cuenta> findByCorreoElectronico(String correo);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByCorreoElectronico(String correo);
}
