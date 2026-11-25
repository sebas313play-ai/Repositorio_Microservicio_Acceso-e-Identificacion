package com.nexa.acceso.service;

import com.nexa.acceso.model.Sesion;
import com.nexa.acceso.repository.SesionRepository;
import com.nexa.acceso.security.UsuarioAutenticado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SesionService {
 private final SesionRepository repo;
 public SesionService(SesionRepository repo){this.repo=repo;}
 @Transactional public void cerrar(UsuarioAutenticado usuario){
  Sesion s=repo.findById(usuario.sesionId()).orElseThrow();
  if(s.getFechaCierre()==null){s.setFechaCierre(LocalDateTime.now());repo.save(s);}
 }
 public List<Sesion> listar(Long cuentaId){return repo.findByCuentaIdOrderByFechaInicioDesc(cuentaId);}
}
