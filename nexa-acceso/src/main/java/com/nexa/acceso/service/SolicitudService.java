package com.nexa.acceso.service;

import com.nexa.acceso.dto.*;
import com.nexa.acceso.model.*;
import com.nexa.acceso.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitudService {
 private final SolicitudRepository solicitudes; private final CuentaRepository cuentas;
 public SolicitudService(SolicitudRepository s,CuentaRepository c){solicitudes=s;cuentas=c;}
 @Transactional public SolicitudRestablecimiento crear(SolicitudRequest req){
  SolicitudRestablecimiento s=new SolicitudRestablecimiento();
  s.setSolicitante(req.solicitante()); s.setObservaciones(req.observaciones());
  cuentas.findByNombreUsuario(req.solicitante()).or(()->cuentas.findByCorreoElectronico(req.solicitante())).ifPresent(s::setCuenta);
  s.setFechaSolicitud(LocalDateTime.now()); return solicitudes.save(s);
 }
 public List<SolicitudRestablecimiento> listar(){return solicitudes.findAll();}
 @Transactional public SolicitudRestablecimiento gestionar(Long id,GestionSolicitudRequest req){
  SolicitudRestablecimiento s=solicitudes.findById(id).orElseThrow(()->new IllegalArgumentException("Solicitud no encontrada."));
  s.setResultado(req.resultado()); s.setObservaciones(req.observaciones()); return solicitudes.save(s);
 }
}
