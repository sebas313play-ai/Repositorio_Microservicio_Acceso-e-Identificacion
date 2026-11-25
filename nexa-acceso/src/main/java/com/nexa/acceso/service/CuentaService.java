package com.nexa.acceso.service;

import com.nexa.acceso.dto.RegistroCuentaRequest;
import com.nexa.acceso.model.*;
import com.nexa.acceso.repository.CuentaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CuentaService {
 private final CuentaRepository repo; private final PasswordEncoder encoder;
 public CuentaService(CuentaRepository repo,PasswordEncoder encoder){this.repo=repo;this.encoder=encoder;}
 @Transactional public Cuenta crear(RegistroCuentaRequest req){
  if(repo.existsByNombreUsuario(req.nombreUsuario())) throw new IllegalArgumentException("El nombre de usuario ya existe.");
  if(repo.existsByCorreoElectronico(req.correoElectronico())) throw new IllegalArgumentException("El correo ya está registrado.");
  if(req.rol()==Rol.PROPIETARIO && req.propietarioId()==null) throw new IllegalArgumentException("El rol PROPIETARIO requiere propietarioId.");
  if(req.rol()!=Rol.PROPIETARIO && req.propietarioId()!=null) throw new IllegalArgumentException("propietarioId solo aplica al rol PROPIETARIO.");
  Cuenta c=new Cuenta(); c.setNombreUsuario(req.nombreUsuario()); c.setCorreoElectronico(req.correoElectronico());
  c.setPasswordHash(encoder.encode(req.password())); c.setRol(req.rol()); c.setPropietarioId(req.propietarioId());
  return repo.save(c);
 }
 public List<Cuenta> listar(){return repo.findAll();}
}
