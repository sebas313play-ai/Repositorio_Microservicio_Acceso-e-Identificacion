package com.nexa.acceso.controller;
import com.nexa.acceso.dto.*;
import com.nexa.acceso.model.SolicitudRestablecimiento;
import com.nexa.acceso.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/solicitudes-restablecimiento")
public class SolicitudController {
 private final SolicitudService service;
 public SolicitudController(SolicitudService s){service=s;}
 @PostMapping public Map<String,Object> crear(@Valid @RequestBody SolicitudRequest req){
  SolicitudRestablecimiento s=service.crear(req);
  return Map.of("id",s.getId(),"solicitante",s.getSolicitante(),"resultado",s.getResultado(),"mensaje","Solicitud registrada.");
 }
 @GetMapping public List<Map<String,Object>> listar(){return service.listar().stream().map(s->Map.<String,Object>of(
  "id",s.getId(),"cuentaId",s.getCuenta()==null?"SIN_CUENTA":s.getCuenta().getId(),
  "solicitante",s.getSolicitante(),"fechaSolicitud",s.getFechaSolicitud(),
  "resultado",s.getResultado(),"observaciones",s.getObservaciones()==null?"":s.getObservaciones())).toList();}
 @PatchMapping("/{id}") public Map<String,Object> gestionar(@PathVariable Long id,@Valid @RequestBody GestionSolicitudRequest req){
  SolicitudRestablecimiento s=service.gestionar(id,req);
  return Map.of("id",s.getId(),"resultado",s.getResultado(),"observaciones",s.getObservaciones()==null?"":s.getObservaciones());
 }
}
