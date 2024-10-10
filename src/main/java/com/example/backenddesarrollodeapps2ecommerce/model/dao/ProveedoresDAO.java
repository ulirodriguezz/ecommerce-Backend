package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProveedorEntity;
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
public class ProveedoresDAO {
    @PersistenceContext
    EntityManager em;
    @Autowired
    IProveedoresDAOBase daoBase;
    @Transactional
    public List<ProveedorEntity> findAll(int page, int pageSize){
        int PAGESIZE =(int) daoBase.count(); //CAMBIAR PARA PAGINACION
        int startItem = 0;
        if(page != 1)
            startItem = page  * PAGESIZE + 1 ;
        Session sesionActual = em.unwrap(Session.class);
        Query query = sesionActual.createQuery("FROM ProveedorEntity AS p ORDER BY p.idProveedor");
        query.setMaxResults(PAGESIZE).setFirstResult(startItem);
        if(query.getResultList().isEmpty())
            throw new EmptyResultDataAccessException("No hay proveedores",1);
        return query.getResultList();

    }
    @Transactional
    public void save (ProveedorEntity proveedor){
        daoBase.save(proveedor);
    }
    @Transactional
    public void update(ProveedorEntity nuevoProv, long idProveedor){
        Session sesionActual = em.unwrap(Session.class);
        ProveedorEntity provDB = sesionActual.find(ProveedorEntity.class,idProveedor);
        if(nuevoProv.getNombre() != null)
            provDB.setNombre(nuevoProv.getNombre());
        if(nuevoProv.getCuit() != null)
            provDB.setCuit(nuevoProv.getCuit());
    }
    @Transactional
    public void delete(long idProveedor){
        Session sesionActual = em.unwrap(Session.class);
        ProveedorEntity prov = sesionActual.find(ProveedorEntity.class,idProveedor);
        if(prov == null)
            throw new EmptyResultDataAccessException("No se encontro el proveedor",1);
        sesionActual.remove(prov);
    }

    public ProveedorEntity getPorID(long idProv) {
        Session sesionActual = em.unwrap(Session.class);
        return sesionActual.find(ProveedorEntity.class,idProv);
    }
}
