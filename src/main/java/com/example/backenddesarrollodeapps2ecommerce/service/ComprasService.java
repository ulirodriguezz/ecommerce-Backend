package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.ComprasDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.CompraEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasService {
    @Autowired
    ComprasDAO comprasDAO;
    public List<CompraEntity> getAllCompras(int page)
    {
        try {
            return comprasDAO.findAll(page,10);
        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw e;
        }
    }
    public void save(CompraEntity compra){
        try{
            comprasDAO.save(compra);

        }catch (Throwable e){
            e.printStackTrace();
            throw e;
        }
    }
}
