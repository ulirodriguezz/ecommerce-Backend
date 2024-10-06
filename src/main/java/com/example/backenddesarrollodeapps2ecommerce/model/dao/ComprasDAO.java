package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.CompraEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComprasDAO {
    @PersistenceContext
    EntityManager em;
    @Autowired
    IComprasDAOBase daoBase;

    @Transactional
    public List<CompraEntity> findAll(int page, int pageSize){
        int PAGESIZE =(int) daoBase.count(); //CAMBIAR PARA PAGINACION
        int startItem = 0;
        if(page != 1)
            startItem = page  * PAGESIZE + 1 ;
        Session sesionActual   = em.unwrap(Session.class);
        Query query = sesionActual.createQuery("FROM CompraEntity AS c ORDER BY c.idCompra ASC", CompraEntity.class);
        query.setMaxResults(PAGESIZE).setFirstResult(startItem);
        if(query.getResultList().isEmpty())
            throw new EmptyResultDataAccessException("No hay compras",1);
        return query.getResultList();
    }
    @Transactional
    public void save(CompraEntity compra){
        try {
            Session sesionActual   = em.unwrap(Session.class);
            sesionActual.persist(compra);
            BalanceEntity balance = sesionActual.find(BalanceEntity.class,1);
            balance.setMontoCompras(balance.getMontoCompras() + compra.getMontoTotal());
            sesionActual.persist(balance);
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }
}
