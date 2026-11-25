package com.nexa.acceso.security;
import com.nexa.acceso.model.Rol;
public record UsuarioAutenticado(Long cuentaId, Long sesionId, Rol rol) {}
