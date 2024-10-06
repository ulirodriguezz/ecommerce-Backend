package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProveedoresDAOBase extends JpaRepository<ProveedorEntity,Long> {
}
