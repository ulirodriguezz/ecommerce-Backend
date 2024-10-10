package com.example.backenddesarrollodeapps2ecommerce.controller;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.UsuarioEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("")
public class AuthController {
    @Autowired
    UsuariosService usuariosService;
    @GetMapping("healthcheck")
    public ResponseEntity<?> healthcheck() {
        return new ResponseEntity<>(new Mensaje("UP"), HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity credenciales) {
        try{
           UsuarioEntity datosUsuario = usuariosService.validarCredenciales(credenciales);
            return new ResponseEntity<>(datosUsuario, HttpStatus.OK);
        }catch (CredentialException e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("Username incorrecto"), HttpStatus.UNAUTHORIZED);
        }
        catch (Throwable e){
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
