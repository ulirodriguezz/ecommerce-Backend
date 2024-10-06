package com.example.backenddesarrollodeapps2ecommerce.model.entities;

import jakarta.persistence.*;


import java.util.Set;

@Entity
@Table(name = "users")
public class UsuarioEntity {
    @Id
    private String username;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<TarjetaCreditoEntity> creditCard;
    @OneToMany(mappedBy = "nombreUsuario",fetch = FetchType.LAZY)
    private Set<CompraEntity> compras;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ProductoEntity> productosFavoritos;

    public UsuarioEntity() {
    }

}
