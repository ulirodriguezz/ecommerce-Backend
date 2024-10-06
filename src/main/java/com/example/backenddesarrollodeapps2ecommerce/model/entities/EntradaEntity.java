package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "entrdas")
public class EntradaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long idEntrada;
    private String nombre;
    private String descripcion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private float descuentoEfectivo;
    private float descuentoSocios;
    private float descuentoNoSocios;
    private String sector;
}
