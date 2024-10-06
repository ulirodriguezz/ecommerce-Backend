package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public class VentasDAO {
    @PersistenceContext
    EntityManager em;
    @Autowired
    IVentasDAOBase daoBase;

    public List<VentaEntity> findAll(int page, int pageSize)
    {
        int PAGESIZE =(int) daoBase.count(); //CAMBIAR PARA PAGINACION
        int startItem = 0;
        if(page != 1)
            startItem = page  * PAGESIZE + 1 ;
        Session sesionActual = em.unwrap(Session.class);
        Query query = sesionActual.createQuery("FROM VentaEntity AS v order by v.idVenta ASC", VentaEntity.class);
        query.setMaxResults(PAGESIZE).setFirstResult(startItem);
        if(query.getResultList().isEmpty())
            throw new EmptyResultDataAccessException("No hay ventas",1);
        return query.getResultList();


    }

}
