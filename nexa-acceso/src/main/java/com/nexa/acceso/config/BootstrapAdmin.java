package com.nexa.acceso.config;

import com.nexa.acceso.model.*;
import com.nexa.acceso.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BootstrapAdmin {
 @Bean CommandLineRunner crearAdminInicial(CuentaRepository repo,PasswordEncoder encoder,
   @Value("${app.bootstrap.admin-username}") String username,
   @Value("${app.bootstrap.admin-email}") String email,
   @Value("${app.bootstrap.admin-password}") String password) {
  return args -> {
   if(repo.findByNombreUsuario(username).isEmpty()) {
    Cuenta c=new Cuenta(); c.setNombreUsuario(username); c.setCorreoElectronico(email);
    c.setPasswordHash(encoder.encode(password)); c.setRol(Rol.ADMINISTRADOR);
    repo.save(c);
    System.out.println("Administrador inicial creado: "+username+" (cambie la contraseña inicial).");
   }
  };
 }
}
