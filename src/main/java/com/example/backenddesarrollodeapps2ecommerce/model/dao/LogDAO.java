package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(LogEntity log){
        Session sesionActual = em.unwrap(Session.class);
        sesionActual.persist(log);
    }


}
