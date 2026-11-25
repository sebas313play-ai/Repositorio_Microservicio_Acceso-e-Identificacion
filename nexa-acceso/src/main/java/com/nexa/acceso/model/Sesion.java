package com.nexa.acceso.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="sesion")
public class Sesion {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="cuenta_id", nullable=false)
    private Cuenta cuenta;
    @Column(name="fecha_inicio", nullable=false)
    private LocalDateTime fechaInicio = LocalDateTime.now();
    @Column(name="fecha_cierre")
    private LocalDateTime fechaCierre;
    @Column(name="ip_acceso", length=45)
    private String ipAcceso;
    @Column(length=255)
    private String dispositivo;

    public Long getId(){return id;}
    public Cuenta getCuenta(){return cuenta;}
    public void setCuenta(Cuenta v){cuenta=v;}
    public LocalDateTime getFechaInicio(){return fechaInicio;}
    public LocalDateTime getFechaCierre(){return fechaCierre;}
    public void setFechaCierre(LocalDateTime v){fechaCierre=v;}
    public String getIpAcceso(){return ipAcceso;}
    public void setIpAcceso(String v){ipAcceso=v;}
    public String getDispositivo(){return dispositivo;}
    public void setDispositivo(String v){dispositivo=v;}
    public boolean isActiva(){return fechaCierre==null;}
}
