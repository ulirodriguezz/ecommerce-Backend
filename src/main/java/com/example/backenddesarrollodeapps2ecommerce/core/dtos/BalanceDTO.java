package com.example.backenddesarrollodeapps2ecommerce.core.dtos;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;

public class BalanceDTO {
    private double montoVentas;
    private double montoCompras;

    public BalanceDTO() {
    }
    public BalanceDTO(BalanceEntity b) {
        this.montoCompras = b.getMontoCompras();
        this.montoVentas = b.getMontoVentas();
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
}
