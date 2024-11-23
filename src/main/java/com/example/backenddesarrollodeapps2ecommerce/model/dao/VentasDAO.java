package com.example.backenddesarrollodeapps2ecommerce.model.dao;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VentasDAO {
    @PersistenceContext
    EntityManager em;
    @Autowired
    IVentasDAOBase daoBase;
    @Autowired
    ProductoDAO prodDAO;

    @Transactional
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
    public List<VentaEntity> findByUsername(String username)
    {
        List<VentaEntity> ventas;
        Session sesionActual = em.unwrap(Session.class);
        Query query = sesionActual.createQuery("FROM VentaEntity AS v  where v.nombreUsuario ="+ username+ " ORDER BY v.fecha ASC", VentaEntity.class);
        ventas = query.getResultList();
        if(ventas.isEmpty())
            throw new EmptyResultDataAccessException("No hay ventas",1);
        return ventas;
    }
    @Transactional
    public void save(VentaEntity venta) {
        Session sesionActual   = em.unwrap(Session.class);
        boolean exitosa = true;
        Set<Integer> idsProds = new HashSet<>(venta.getProductos());
        Map<Integer,Integer> reps = new HashMap<>();
        Set<ProductoEntity> entidades = new HashSet<>();
        try{

            for(Integer idProd : idsProds){
                reps.put(idProd,0);
                for(Integer id : venta.getProductos()){
                    if(id == idProd)
                        reps.put(idProd,reps.get(idProd) + 1);
                    System.out.println(reps.get(idProd));
                }
                ProductoEntity prod = prodDAO.getPorID(idProd);
                if(prod == null) {
                    throw new EmptyResultDataAccessException("No existe el prod",1);
                }
                if(prod.getStockActual() < reps.get(idProd)) {
                    throw new Error("No hay stock");
                }
                entidades.add(prod);
            }
            for(ProductoEntity p : entidades){
                p.setStockActual(p.getStockActual() - reps.get( (int) (long)p.getIdProducto()));
                em.persist(p);
            }

            /*VentasPorCategoria vpc = sesionActual.createQuery("from VentasPorCategoria where nombreCategoria=:nombreCat",VentasPorCategoria.class)
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
*/
        }catch (EmptyResultDataAccessException e){
           System.out.println("No existe el producto");
           venta.setEstado(EstadoVenta.ERROR_DE_STOCK);
           exitosa = false;

        }catch (Error e){
            System.out.println("ALGUN ERROR CON LOS PRODUCTOS DE LA VENTA");
            venta.setEstado(EstadoVenta.ERROR_DE_STOCK);
            exitosa = false;
        }
        try {
            System.out.println("ACA ESTA LA VENTA:" + venta);
            sesionActual.persist(venta);
            if(exitosa){
                BalanceEntity balance = sesionActual.find(BalanceEntity.class,1);
                System.out.println("BALANCE: " + balance.getMontoVentas());
                System.out.println("NUEVO BALANCE: " + balance.getMontoVentas() + venta.getMontoTotal());
                balance.setMontoVentas(balance.getMontoVentas() + venta.getMontoTotal());
                sesionActual.persist(balance);
            }
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }


}
