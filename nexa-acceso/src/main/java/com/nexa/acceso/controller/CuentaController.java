package com.nexa.acceso.controller;
import com.nexa.acceso.dto.RegistroCuentaRequest;
import com.nexa.acceso.model.Cuenta;
import com.nexa.acceso.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/cuentas")
public class CuentaController {
 private final CuentaService service;
 public CuentaController(CuentaService s){service=s;}
 @PostMapping public Map<String,Object> crear(@Valid @RequestBody RegistroCuentaRequest req){
  Cuenta c=service.crear(req); return Map.of("id",c.getId(),"nombreUsuario",c.getNombreUsuario(),"correo",c.getCorreoElectronico(),"rol",c.getRol());
 }
 @GetMapping public List<Map<String,Object>> listar(){
  return service.listar().stream().map(c->Map.<String,Object>of("id",c.getId(),"nombreUsuario",c.getNombreUsuario(),
   "correo",c.getCorreoElectronico(),"rol",c.getRol(),"activa",c.isActiva())).toList();
 }
}
