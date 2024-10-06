package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "compras")
public class CompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_compra")
    private long idCompra;
    private String nombreUsuario;
    private LocalDate fechaOrden;
    private double montoTotal;
    private Integer cantidadDeUnidades;
    private long idProducto;
    public CompraEntity() {

    }

    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(long idCompra) {
        this.idCompra = idCompra;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDate getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(LocalDate fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadDeUnidades() {
        return cantidadDeUnidades;
    }

    public void setCantidadDeUnidades(Integer cantidadDeUnidades) {
        this.cantidadDeUnidades = cantidadDeUnidades;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
}
