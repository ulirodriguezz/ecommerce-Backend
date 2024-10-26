package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="logs")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdLog;
    private String mensaje;

    public LogEntity() {
    }

    public Long getIdLog() {
        return IdLog;
    }

    public void setIdLog(Long idLog) {
        IdLog = idLog;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
