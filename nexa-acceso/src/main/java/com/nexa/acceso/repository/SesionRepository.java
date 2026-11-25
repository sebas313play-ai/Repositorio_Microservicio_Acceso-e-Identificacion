package com.nexa.acceso.repository;
import com.nexa.acceso.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SesionRepository extends JpaRepository<Sesion,Long> {
    List<Sesion> findByCuentaIdOrderByFechaInicioDesc(Long cuentaId);
}
