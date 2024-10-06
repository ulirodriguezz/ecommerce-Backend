package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComprasDAOBase extends JpaRepository<CompraEntity,Long> {
}
