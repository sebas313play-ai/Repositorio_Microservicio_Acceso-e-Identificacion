package com.nexa.acceso.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre_usuario", nullable=false, unique=true, length=60)
    private String nombreUsuario;
    @Column(name="correo_electronico", nullable=false, unique=true, length=120)
    private String correoElectronico;
    @Column(name="password_hash", nullable=false, length=100)
    private String passwordHash;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20)
    private Rol rol;
    @Column(name="propietario_id")
    private Long propietarioId;
    @Column(nullable=false)
    private boolean activa = true;
    @Column(name="fecha_creacion", nullable=false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public Long getId(){return id;}
    public String getNombreUsuario(){return nombreUsuario;}
    public void setNombreUsuario(String v){nombreUsuario=v;}
    public String getCorreoElectronico(){return correoElectronico;}
    public void setCorreoElectronico(String v){correoElectronico=v;}
    public String getPasswordHash(){return passwordHash;}
    public void setPasswordHash(String v){passwordHash=v;}
    public Rol getRol(){return rol;}
    public void setRol(Rol v){rol=v;}
    public Long getPropietarioId(){return propietarioId;}
    public void setPropietarioId(Long v){propietarioId=v;}
    public boolean isActiva(){return activa;}
    public void setActiva(boolean v){activa=v;}
    public LocalDateTime getFechaCreacion(){return fechaCreacion;}
}
