package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.ProductoDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoDAO prodDAO;

    public List<ProductoEntity> getAllProducts(int page) {
        try{
            return prodDAO.findAll(page,10);
        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrio un error");
        }
    }
    public List<ProductoEntity> buscarEnAlerta(int page) {
        try{
            return prodDAO.buscarEnAlerta(page,10);
        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrio un error");
        }
    }
    public void update(ProductoEntity producto, long idProducto)
    {
        try{
            this.prodDAO.update(producto,idProducto);
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }

    public void save(ProductoEntity p) {
        try {
            prodDAO.save(p);
        } catch (Throwable e) {
        }
    }

    public ProductoEntity getPorID(long idProducto) {
        ProductoEntity resultado;
        try{
             resultado = this.prodDAO.getPorID(idProducto);
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrio un error");
        }
        if(resultado == null)
            throw new EmptyResultDataAccessException("No se encontró el producto",1);
        return resultado;
    }
    public void deleteProducto(long idProducto)
    {
        try{
            this.prodDAO.deleteProducto(idProducto);
        }catch (EmptyResultDataAccessException e)
        {
            throw e;
        }
        catch(Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }
}
