package com.nexa.acceso.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="solicitud_restablecimiento")
public class SolicitudRestablecimiento {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cuenta_id")
    private Cuenta cuenta;
    @Column(nullable=false, length=150)
    private String solicitante;
    @Column(name="fecha_solicitud", nullable=false)
    private LocalDateTime fechaSolicitud=LocalDateTime.now();
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=30)
    private ResultadoSolicitud resultado=ResultadoSolicitud.PENDIENTE;
    @Column(columnDefinition="TEXT")
    private String observaciones;

    public Long getId(){return id;}
    public Cuenta getCuenta(){return cuenta;}
    public void setCuenta(Cuenta v){cuenta=v;}
    public String getSolicitante(){return solicitante;}
    public void setSolicitante(String v){solicitante=v;}
    public LocalDateTime getFechaSolicitud(){return fechaSolicitud;}
    public ResultadoSolicitud getResultado(){return resultado;}
    public void setResultado(ResultadoSolicitud v){resultado=v;}
    public String getObservaciones(){return observaciones;}
    public void setObservaciones(String v){observaciones=v;}
    public void setFechaSolicitud(LocalDateTime now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFechaSolicitud'");
    }
}
