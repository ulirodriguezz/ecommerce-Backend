package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.VentasDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasService {
    @Autowired
    private VentasDAO ventasDAO;

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

    public void save(VentaEntity venta) {
        try{
            ventasDAO.save(venta);
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }

    }
}
