package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentasPorCategoria;
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
public class VentasDAO {
    @PersistenceContext
    EntityManager em;
    @Autowired
    IVentasDAOBase daoBase;
    @Autowired
    ProductoDAO prodDAO;

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
    @Transactional
    public void save(VentaEntity venta) {
        Session sesionActual   = em.unwrap(Session.class);
        try{
            for(Integer producto : venta.getProductos()){
                double montoProducto;
                ProductoEntity prod = prodDAO.getPorID(producto);
                if(prod == null)
                    throw new EmptyResultDataAccessException("No existe el prod",1);
                if(prod.getStockActual() == 0)
                    throw new Exception("No hay stcok");
                prod.setStockActual(prod.getStockActual() - 1);
                //HACER EL CALCULO DEL PRECIO
                montoProducto = prod.getPrecioVenta();
                VentasPorCategoria vpc = sesionActual.createQuery("from VentasPorCategoria where nombreCategoria=:nombreCat",VentasPorCategoria.class)
                        .setParameter("nombreCat",prod.getCategoria().toString()).getSingleResult();
                if(vpc == null){
                    VentasPorCategoria nuevoVpc = new VentasPorCategoria();
                    nuevoVpc.setNombreCategoria(prod.getCategoria().toString());
                    nuevoVpc.setTotalVendido(montoProducto);
                    em.persist(nuevoVpc);
                }else {
                    vpc.setTotalVendido(vpc.getTotalVendido() + montoProducto);
                    em.persist(vpc);
                }

            }
        }catch (EmptyResultDataAccessException e){
           System.out.println("No existe el producto");
        }catch (Exception e){
            throw new Error("Error");
        }
        try {
            sesionActual.persist(venta);
            BalanceEntity balance = sesionActual.find(BalanceEntity.class,1);
            balance.setMontoCompras(balance.getMontoVentas() + venta.getMontoTotal());

            sesionActual.persist(balance);
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }
}
