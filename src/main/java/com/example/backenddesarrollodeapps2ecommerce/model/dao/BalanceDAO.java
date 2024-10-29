package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceDAO {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public BalanceEntity getBalance(){
        Session sesionActual = em.unwrap(Session.class);
        BalanceEntity balance = sesionActual.find(BalanceEntity.class,1);
        if(balance == null){
            balance = new BalanceEntity();
            balance.setId(1);
            balance.setCantVentas(0);
            balance.setMontoCompras(0);
            balance.setMontoVentas(0);
            sesionActual.persist(balance);
        }


        return balance;
    }
    @Transactional
    public void save (BalanceEntity balance)
    {
        Session sesionActual = em.unwrap(Session.class);
        sesionActual.persist(balance);
    }
}
