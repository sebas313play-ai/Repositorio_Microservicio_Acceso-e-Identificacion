package com.nexa.acceso.controller;
import com.nexa.acceso.model.Sesion;
import com.nexa.acceso.security.UsuarioAutenticado;
import com.nexa.acceso.service.SesionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/sesiones")
public class SesionController {
 private final SesionService service;
 public SesionController(SesionService s){service=s;}
 @GetMapping("/mias") public List<Map<String,Object>> mias(@AuthenticationPrincipal UsuarioAutenticado user){
  return service.listar(user.cuentaId()).stream().map(s->Map.<String,Object>of(
   "id",s.getId(),"fechaInicio",s.getFechaInicio(),
   "fechaCierre",s.getFechaCierre()==null?"ACTIVA":s.getFechaCierre(),
   "ipAcceso",s.getIpAcceso()==null?"":s.getIpAcceso(),
   "dispositivo",s.getDispositivo()==null?"":s.getDispositivo())).toList();
 }
}
