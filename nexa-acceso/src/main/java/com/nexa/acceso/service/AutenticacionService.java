package com.nexa.acceso.service;

import com.nexa.acceso.dto.LoginRequest;
import com.nexa.acceso.model.*;
import com.nexa.acceso.repository.*;
import com.nexa.acceso.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class AutenticacionService {
 private final CuentaRepository cuentas; private final SesionRepository sesiones;
 private final PasswordEncoder encoder; private final JwtService jwt;
 public AutenticacionService(CuentaRepository c,SesionRepository s,PasswordEncoder e,JwtService j){
  cuentas=c;sesiones=s;encoder=e;jwt=j;
 }
 @Transactional public Map<String,Object> login(LoginRequest req,HttpServletRequest http){
  Cuenta c=cuentas.findByNombreUsuario(req.usuario()).or(()->cuentas.findByCorreoElectronico(req.usuario()))
    .orElseThrow(()->new IllegalArgumentException("Credenciales incorrectas."));
  if(!c.isActiva() || !encoder.matches(req.password(),c.getPasswordHash()))
    throw new IllegalArgumentException("Credenciales incorrectas.");
  Sesion s=new Sesion(); s.setCuenta(c); s.setIpAcceso(http.getRemoteAddr());
  s.setDispositivo(http.getHeader("User-Agent")); sesiones.save(s);
  String token=jwt.generar(c.getId(),s.getId(),c.getRol().name());
  String panel=switch(c.getRol()){
   case ADMINISTRADOR -> "/admin";
   case PROPIETARIO -> "/propietario";
   case GUARDA -> "/guarda";
  };
  return Map.of("token",token,"tipo","Bearer","cuentaId",c.getId(),
    "nombreUsuario",c.getNombreUsuario(),"rol",c.getRol(),"panel",panel,"sesionId",s.getId());
 }
}
