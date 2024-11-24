package com.example.backenddesarrollodeapps2ecommerce.core.dtos;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.EstadoVenta;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class PedidoDTO {
    private long idPedido;
    private String nombreUsuario;
    private Date fecha;
    private double montoTotal;
    private Integer cantidadDeProductos;
    private List<String> productos;
    private String estado;

    public PedidoDTO (VentaEntity v, List<String> productos){
        idPedido = v.getIdVenta();
        this.nombreUsuario = v.getNombreUsuario();
        this.fecha = v.getFecha();
        this.montoTotal = v.getMontoTotal();
        this.cantidadDeProductos = v.getCantidadDeProductos();
        this.productos = productos;
        if(v.getEstado() == null){
            this.estado = EstadoVenta.PAGADO.toString();
            return;
        }
        this.estado = v.getEstado().toString();
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
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

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
