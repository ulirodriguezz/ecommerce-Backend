package com.example.backenddesarrollodeapps2ecommerce.core.dtos;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.EstadoVenta;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDTO {

    private long idVenta;
    private String nombreUsuario;
    private Date fecha;
    private double montoTotal;
    private Integer cantidadDeProductos;
    private List<Integer> productos;
    private String estado;

    public VentaDTO() {
    }

    public VentaDTO(VentaEntity v) {
        this.idVenta = v.getIdVenta();
        this.nombreUsuario = v.getNombreUsuario();
        this.fecha = v.getFecha();
        this.montoTotal = v.getMontoTotal();
        this.cantidadDeProductos = v.getCantidadDeProductos();
        if(v.getProductos() != null){
            this.productos = v.getProductos();
        }
        else{
            this.productos=new ArrayList<>();
        }

        if(v.getEstado() == null)
            v.setEstado(EstadoVenta.PAGADO);
        this.estado = v.getEstado().toString();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "VentaDTO{" +
                "idVenta=" + idVenta +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", fecha=" + fecha +
                ", montoTotal=" + montoTotal +
                ", cantidadDeProductos=" + cantidadDeProductos +
                ", productos=" + productos +
                ", estado='" + estado + '\'' +
                '}';
    }
}
