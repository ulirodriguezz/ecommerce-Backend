package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.UsuarioDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
public class UsuariosService {
    @Autowired
    UsuarioDAO usuarioDAO;

    public UsuarioEntity validarCredenciales(UsuarioEntity credenciales) throws CredentialException {
        UsuarioEntity credenicalesBD;
        if(credenciales.getNombreUsuario() == null || credenciales.getPassword() == null)
            throw new CredentialException("Debe ingresar usuario y contraseña");
        try{
            credenicalesBD = usuarioDAO.getCredenciales(credenciales.getNombreUsuario());
        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e){
            e.printStackTrace();
            throw new Error("Error al obtener credenciales");
        }
        if(!credenciales.getPassword().contentEquals(credenicalesBD.getPassword()))
            throw new CredentialException("Credenciales invalidas");
        return credenicalesBD;
    }
}
