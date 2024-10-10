package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.ProveedoresDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresService {
    @Autowired
    ProveedoresDAO provDAO;

    public List<ProveedorEntity> getAllProveedores(int page){
        try{
            return provDAO.findAll(page,10);
        }catch (EmptyResultDataAccessException e){
            throw e;
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }
    public ProveedorEntity getPorID(long idProv) {
        ProveedorEntity resultado;
        try{
            resultado = this.provDAO.getPorID(idProv);
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrio un error");
        }
        if(resultado == null)
            throw new EmptyResultDataAccessException("No se encontró el proveedor",1);
        return resultado;
    }
    public void update(ProveedorEntity prov, long idProv)
    {
        try{
            this.provDAO.update(prov,idProv);
        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }
    public void save(ProveedorEntity prov) {
        try {
            provDAO.save(prov);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new Error("Error inerno");
        }
    }
    public void deleteProveedor(long idProv)
    {
        try{
            this.provDAO.delete(idProv);
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
