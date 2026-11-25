package com.nexa.acceso.repository;
import com.nexa.acceso.model.SolicitudRestablecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SolicitudRepository extends JpaRepository<SolicitudRestablecimiento,Long> {}
