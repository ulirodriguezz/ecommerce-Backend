package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "proveedores")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;
    private String nombre;
    private Long cuit;
    @OneToMany(mappedBy = "idProveedor")
    private List<ProductoEntity> productos;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }


}
