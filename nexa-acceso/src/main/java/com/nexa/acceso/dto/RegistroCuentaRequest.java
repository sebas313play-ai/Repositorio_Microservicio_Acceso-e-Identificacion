package com.nexa.acceso.dto;
import com.nexa.acceso.model.Rol;
import jakarta.validation.constraints.*;
public record RegistroCuentaRequest(
 @NotBlank @Size(max=60) String nombreUsuario,
 @NotBlank @Email @Size(max=120) String correoElectronico,
 @NotBlank @Size(min=8,max=72) String password,
 @NotNull Rol rol,
 Long propietarioId
) {}
