package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.ProductoDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.VentasDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.EstadoVenta;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentasService {
    @Autowired
    private VentasDAO ventasDAO;
    @Autowired
    private ProductoDAO productoDAO;

    public List<VentaEntity> getAllVentas(int page){
        try {
            return ventasDAO.findAll(page,10);

        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }


    }
    public List<VentaEntity> getVentasByUsername(String username){
        try {
            return ventasDAO.findByUsername(username);

        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }

    }
    public VentaEntity getByID(long idVenta){
        try{
           VentaEntity v = this.ventasDAO.findById(idVenta);
           return v;
        }catch (Throwable e){
            System.out.println("ERROR: No se pudo obtener la venta");
            e.printStackTrace();
            throw e;
        }
    }

    public void save(VentaEntity venta) {
        try{
            venta.setEstado(EstadoVenta.PAGADO);
            ventasDAO.save(venta);
        }catch (Throwable e){

            e.printStackTrace();
            throw new Error(e.getMessage());
        }

    }

    public void update(VentaEntity venta) {
        try {
            ventasDAO.update(venta);
        }catch (Throwable e){
            e.printStackTrace();
            throw e;
        }
    }
    public List<String>getNombresProductos(List<Integer> ids){
        List<String> nombres = new ArrayList<>();
        try{
            for(Integer id : ids){
                ProductoEntity p = productoDAO.getPorID(id);
                if(p == null){
                    nombres.add("Producto Eliminado (ID:"+id+")");
                }
                else {
                    nombres.add(p.getNombre());
                }
            }
        }catch (Throwable e){
            System.out.println("ERROR: No se pudieron converitr los nombres");
            e.printStackTrace();
            throw e;
        }
        return nombres;
    }
}
