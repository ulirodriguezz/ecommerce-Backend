package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "balances")
public class BalanceEntity {
    @Id
    private int id;
    private double montoVentas;
    private double montoCompras;
    private double cantVentas;
    private double vendidoCamisetas;
    private double vendidoCalzados;
    private double vendidoCamperas;
    private double vendidoBuzos;

    public BalanceEntity() {
    }

    public double getMontoVentas() {
        return montoVentas;
    }

    public void setMontoVentas(double montoVentas) {
        this.montoVentas = montoVentas;
    }

    public double getMontoCompras() {
        return montoCompras;
    }

    public void setMontoCompras(double montoCompras) {
        this.montoCompras = montoCompras;
    }

    public double getCantVentas() {
        return cantVentas;
    }

    public void setCantVentas(double cantVentas) {
        this.cantVentas = cantVentas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVendidoCamisetas() {
        return vendidoCamisetas;
    }

    public void setVendidoCamisetas(double vendidoCamisetas) {
        this.vendidoCamisetas = vendidoCamisetas;
    }

    public double getVendidoCalzados() {
        return vendidoCalzados;
    }

    public void setVendidoCalzados(double vendidoCalzados) {
        this.vendidoCalzados = vendidoCalzados;
    }

    public double getVendidoCamperas() {
        return vendidoCamperas;
    }

    public void setVendidoCamperas(double vendidoCamperas) {
        this.vendidoCamperas = vendidoCamperas;
    }

    public double getVendidoBuzos() {
        return vendidoBuzos;
    }

    public void setVendidoBuzos(double vendidoBuzos) {
        this.vendidoBuzos = vendidoBuzos;
    }
}
