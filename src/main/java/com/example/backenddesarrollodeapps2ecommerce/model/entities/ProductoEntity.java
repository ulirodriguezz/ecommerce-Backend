package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long idProducto;
    private String nombre;
    private String descripcion;
    private Double precioVenta;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMinimo;
    private Float descuentoEfectivo;
    private Float descuentoSocios;
    private Float descuentoNoSocios;
    private Categoria categoria;
    private Long idProveedor;
    @ElementCollection
    private Set<String> caracteristicas;
    @ElementCollection
    private Set<TallesEnum> talles;
    @ManyToOne
    @JoinColumn(name = "idProveedor",referencedColumnName = "idProveedor",insertable = false,updatable = false)
    private ProveedorEntity proveedor;


    @ManyToMany
    private List<VentaEntity> ventas;

    public ProductoEntity() {
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

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }


    public Set<TallesEnum> getTalles() {
        return talles;
    }

    public void setTalles(Set<TallesEnum> talles) {
        this.talles = talles;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

}