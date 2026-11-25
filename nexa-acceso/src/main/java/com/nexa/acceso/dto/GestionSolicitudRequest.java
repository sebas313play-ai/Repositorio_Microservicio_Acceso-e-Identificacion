package com.nexa.acceso.dto;
import com.nexa.acceso.model.ResultadoSolicitud;
import jakarta.validation.constraints.NotNull;
public record GestionSolicitudRequest(@NotNull ResultadoSolicitud resultado, String observaciones) {}
