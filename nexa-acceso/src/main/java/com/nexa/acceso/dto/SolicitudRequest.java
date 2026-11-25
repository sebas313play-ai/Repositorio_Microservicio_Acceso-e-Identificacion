package com.nexa.acceso.dto;
import jakarta.validation.constraints.NotBlank;
public record SolicitudRequest(@NotBlank String solicitante, String observaciones) {}
