package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.UsuarioEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {
    @PersistenceContext
    EntityManager em;

    public UsuarioEntity getCredenciales(String username){
        Session sesionActual = em.unwrap(Session.class);
        UsuarioEntity credenciales = sesionActual.createQuery("FROM UsuarioEntity AS u WHERE u.nombreUsuario = :username",UsuarioEntity.class).setParameter("username",username).getSingleResult();
        if(credenciales == null)
            throw new EmptyResultDataAccessException("No se encontró el user",1);
        return credenciales;
    }
}
