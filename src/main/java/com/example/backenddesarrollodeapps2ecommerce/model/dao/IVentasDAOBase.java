package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentasDAOBase extends JpaRepository<VentaEntity,Long> {
}
