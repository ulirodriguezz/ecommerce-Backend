package com.example.backenddesarrollodeapps2ecommerce.core.dtos;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.TallesEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductoDTO {

    private long idProducto;
    private String nombre;
    private String descripcion;
    private Double precioVenta;
    private Integer stockActual;
    private Float descuentoEfectivo;
    private Float descuentoSocios;
    private Float descuentoNoSocios;
    private String categoria;
    private Set<String> caracteristicas;
    private Set<String> talles;

    public ProductoDTO() {
    }

    public ProductoDTO(ProductoEntity producto){

        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precioVenta = producto.getPrecioVenta();
        this.stockActual = producto.getStockActual();
        this.descuentoEfectivo = producto.getDescuentoEfectivo();
        this.descuentoSocios = producto.getDescuentoSocios();
        this.descuentoNoSocios = producto.getDescuentoNoSocios();
        this.categoria = producto.getCategoria().toString();
        this.caracteristicas = new HashSet<>();
        Set<String> talles = new HashSet<>();
        this.talles = talles;
     }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Float getDescuentoEfectivo() {
        return descuentoEfectivo;
    }

    public void setDescuentoEfectivo(Float descuentoEfectivo) {
        this.descuentoEfectivo = descuentoEfectivo;
    }

    public Float getDescuentoSocios() {
        return descuentoSocios;
    }

    public void setDescuentoSocios(Float descuentoSocios) {
        this.descuentoSocios = descuentoSocios;
    }

    public Float getDescuentoNoSocios() {
        return descuentoNoSocios;
    }

    public void setDescuentoNoSocios(Float descuentoNoSocios) {
        this.descuentoNoSocios = descuentoNoSocios;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<String> getTalles() {
        return talles;
    }

    public void setTalles(Set<String> talles) {
        this.talles = talles;
    }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", stockActual=" + stockActual +
                ", descuentoEfectivo=" + descuentoEfectivo +
                ", descuentoSocios=" + descuentoSocios +
                ", descuentoNoSocios=" + descuentoNoSocios +
                ", categoria='" + categoria + '\'' +
                ", caracteristicas=" + caracteristicas +
                ", talles=" + talles +
                '}';
    }
}


