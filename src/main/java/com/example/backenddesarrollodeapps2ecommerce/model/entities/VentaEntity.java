package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ventas")
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_venta")
    private long idVenta;
    private String nombreUsuario;
    private Date fecha;
    private double montoTotal;
    private Integer cantidadDeProductos;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> productos;
    @Enumerated(EnumType.ORDINAL)
    private EstadoVenta estado;
    @ManyToMany
    List<ProductoEntity> productosRel;

    public VentaEntity() {
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadDeProductos() {
        return cantidadDeProductos;
    }

    public void setCantidadDeProductos(Integer cantidadDeProductos) {
        this.cantidadDeProductos = cantidadDeProductos;
    }

    public List<Integer> getProductos() {
        return productos;
    }

    public void setProductos(List<Integer> productos) {
        this.productos = productos;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "VentaEntity{" +
                "idVenta=" + idVenta +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", fecha=" + fecha +
                ", montoTotal=" + montoTotal +
                ", cantidadDeProductos=" + cantidadDeProductos +
                ", productos=" + productos +
                ", estado=" + estado +
                '}';
    }
}
