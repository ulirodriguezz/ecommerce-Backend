package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "credit_cards")
public class TarjetaCreditoEntity {
    @Id
    private int number;
    private Date expDate;
    private int ccv;
    private String ownerName;
    @ManyToOne
    private UsuarioEntity user;

    public TarjetaCreditoEntity() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
