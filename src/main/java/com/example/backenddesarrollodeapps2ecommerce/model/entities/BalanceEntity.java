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
    private long cantVentas;

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

    public long getCantVentas() {
        return cantVentas;
    }

    public void setCantVentas(long cantVentas) {
        this.cantVentas = cantVentas;
    }

    public void setId(int id) {
        this.id = id;
    }
}
