package com.nexa.acceso.controller;
import com.nexa.acceso.dto.LoginRequest;
import com.nexa.acceso.security.UsuarioAutenticado;
import com.nexa.acceso.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AutenticacionService auth; private final SesionService sesiones;
 public AuthController(AutenticacionService a,SesionService s){auth=a;sesiones=s;}
 @PostMapping("/login") public Map<String,Object> login(@Valid @RequestBody LoginRequest req,HttpServletRequest http){return auth.login(req,http);}
 @PostMapping("/logout") public Map<String,String> logout(@AuthenticationPrincipal UsuarioAutenticado user){
  sesiones.cerrar(user); return Map.of("mensaje","Sesión cerrada correctamente.");
 }
 @GetMapping("/yo") public Map<String,Object> yo(@AuthenticationPrincipal UsuarioAutenticado user){
  String panel=switch(user.rol()){case ADMINISTRADOR->"/admin";case PROPIETARIO->"/propietario";case GUARDA->"/guarda";};
  return Map.of("cuentaId",user.cuentaId(),"sesionId",user.sesionId(),"rol",user.rol(),"panel",panel);
 }
}
